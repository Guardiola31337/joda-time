package org.joda.time;

import java.io.Serializable;

/**
 * Created by repli on 11/03/15.
 */
public class Limits implements Serializable {

    private final DateTimeFieldType lower;
    private final DateTimeFieldType upper;

    public Limits(DateTimeFieldType lowerLimit, DateTimeFieldType upperLimit) {
        lower = lowerLimit;
        upper = upperLimit;
    }

    public DateTimeFieldType lower() {
        return lower;
    }

    public DateTimeFieldType upper() {
        return upper;
    }

    public long apply(Chronology chrono, long millis) {
        millis = useLower(chrono, millis);
        millis = useUpper(chrono, millis);
        return millis;
    }

    public boolean noLimits() {
        return lower == null && upper == null;
    }

    public boolean limitedByDate() {
        return lower == DateTimeFieldType.dayOfYear() && upper == null;
    }

    public boolean limitedByYear() {
        return lower == null && upper == DateTimeFieldType.dayOfYear();
    }

    private long useUpper(Chronology lhsChrono, long millis) {
        if (upper == null) return millis;

        return upper.getField(lhsChrono).remainder(millis);
    }

    private long useLower(Chronology chrono, long millis) {
        if (lower == null) return millis;

        return lower.getField(chrono).roundFloor(millis);
    }

    public boolean hasNoSize() {
        return lower == upper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Limits limits = (Limits) o;

        if (lower != null ? !lower.equals(limits.lower) : limits.lower != null) return false;
        if (upper != null ? !upper.equals(limits.upper) : limits.upper != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lower != null ? lower.hashCode() : 0;
        result = 31 * result + (upper != null ? upper.hashCode() : 0);
        return result;
    }

    public String lowerName() {
        String result = "";
        if (lower != null) {
            result = lower.getName();
        }
        return result;
    }

    public String upperName() {
        String result = "";
        if (upper != null) {
            result = upper.getName();
        }
        return result;
    }
}
