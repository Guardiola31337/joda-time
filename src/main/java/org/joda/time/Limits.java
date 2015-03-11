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

    public boolean equals(Object other) {
        Limits casted = (Limits) other;

        if(!(casted instanceof Limits) || casted == null) return false;
        if (lower == null || upper == null) return false;
        System.out.print(upper.equals(casted.upper()));

        boolean lowerMatches = lower == casted.lower() || lower.equals(casted.lower());
        boolean upperMatches = upper == casted.upper() || upper.equals(casted.upper());

        return lowerMatches && upperMatches;
    }

}
