/*
 *  Copyright 2001-2005 Stephen Colebourne
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.joda.time;

import java.io.Serializable;

/**
 * Identifies a field, such as year or minuteOfHour, in a chronology-neutral way.
 * <p>
 * A field type defines the type of the field, such as hourOfDay.
 * If does not directly enable any calculations, however it does provide a
 * {@link #getField(Chronology)} method that returns the actual calculation engine
 * for a particular chronology.
 * It also provides access to the related {@link DurationFieldType}s.
 * <p>
 * Instances of <code>DateTimeFieldType</code> are singletons.
 * They can be compared using <code>==</code>.
 * <p>
 * If required, you can create your own field, for example a quarterOfYear.
 * You must create a subclass of <code>DateTimeFieldType</code> that defines the field type.
 * This class returns the actual calculation engine from {@link #getField(Chronology)}.
 * The subclass should implement equals and hashCode.
 *
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @since 1.0
 */
public abstract class DateTimeFieldType implements Serializable {

    /** Serialization version */
    private static final long serialVersionUID = -42615285973990L;

    /** Ordinal values for standard field types. */
    static final byte
        ERA = 1,
        YEAR_OF_ERA = 2,
        CENTURY_OF_ERA = 3,
        YEAR_OF_CENTURY = 4,
        YEAR = 5,
        DAY_OF_YEAR = 6,
        MONTH_OF_YEAR = 7,
        DAY_OF_MONTH = 8,
        WEEKYEAR_OF_CENTURY = 9,
        WEEKYEAR = 10,
        WEEK_OF_WEEKYEAR = 11,
        DAY_OF_WEEK = 12,
        HALFDAY_OF_DAY = 13,
        HOUR_OF_HALFDAY = 14,
        CLOCKHOUR_OF_HALFDAY = 15,
        CLOCKHOUR_OF_DAY = 16,
        HOUR_OF_DAY = 17,
        MINUTE_OF_DAY = 18,
        MINUTE_OF_HOUR = 19,
        SECOND_OF_DAY = 20,
        SECOND_OF_MINUTE = 21,
        MILLIS_OF_DAY = 22,
        MILLIS_OF_SECOND = 23;

    /** The era field type. */
    private static final DateTimeFieldType ERA_TYPE = create("era", ERA, DurationFieldType.eras(),
        null, new ChronologyEra());
    /** The yearOfEra field type. */
    private static final DateTimeFieldType YEAR_OF_ERA_TYPE = create("yearOfEra", YEAR_OF_ERA,
        DurationFieldType.years(), DurationFieldType.eras(), new ChronologyYearOfEra());
    /** The centuryOfEra field type. */
    private static final DateTimeFieldType CENTURY_OF_ERA_TYPE = create("centuryOfEra",
        CENTURY_OF_ERA, DurationFieldType.centuries(), DurationFieldType.eras(),
        new ChronologyCenturyOfEra());
    /** The yearOfCentury field type. */
    private static final DateTimeFieldType YEAR_OF_CENTURY_TYPE = create("yearOfCentury",
        YEAR_OF_CENTURY, DurationFieldType.years(), DurationFieldType.centuries(),
        new ChronologyYearOfCentury());
    /** The year field type. */
    private static final DateTimeFieldType YEAR_TYPE = create("year", YEAR,
        DurationFieldType.years(), null, new ChronologyYear());
    /** The dayOfYear field type. */
    private static final DateTimeFieldType DAY_OF_YEAR_TYPE = create("dayOfYear", DAY_OF_YEAR,
        DurationFieldType.days(), DurationFieldType.years(), new ChronologyDayOfYear());
    /** The monthOfYear field type. */
    private static final DateTimeFieldType MONTH_OF_YEAR_TYPE = create("monthOfYear",
        MONTH_OF_YEAR, DurationFieldType.months(), DurationFieldType.years(),
        new ChronologyMonthOfYear());
    /** The dayOfMonth field type. */
    private static final DateTimeFieldType DAY_OF_MONTH_TYPE = create("dayOfMonth", DAY_OF_MONTH,
        DurationFieldType.days(), DurationFieldType.months(), new ChronologyDayOfMonth());
    /** The weekyearOfCentury field type. */
    private static final DateTimeFieldType WEEKYEAR_OF_CENTURY_TYPE = create("weekyearOfCentury",
        WEEKYEAR_OF_CENTURY, DurationFieldType.weekyears(), DurationFieldType.centuries(),
        new ChronologyWeekyearOfCentury());
    /** The weekyear field type. */
    private static final DateTimeFieldType WEEKYEAR_TYPE = create("weekyear", WEEKYEAR,
        DurationFieldType.weekyears(), null, new ChronologyWeekyear());
    /** The weekOfWeekyear field type. */
    private static final DateTimeFieldType WEEK_OF_WEEKYEAR_TYPE = create("weekOfWeekyear",
        WEEK_OF_WEEKYEAR, DurationFieldType.weeks(), DurationFieldType.weekyears(),
        new ChronologyWeekOfWeekyear());
    /** The dayOfWeek field type. */
    private static final DateTimeFieldType DAY_OF_WEEK_TYPE = create("dayOfWeek", DAY_OF_WEEK,
        DurationFieldType.days(), DurationFieldType.weeks(), new ChronologyDayOfWeek());

    /** The halfday field type. */
    private static final DateTimeFieldType HALFDAY_OF_DAY_TYPE = create("halfdayOfDay",
        HALFDAY_OF_DAY, DurationFieldType.halfdays(), DurationFieldType.days(),
        new ChronologyHalfdayOfDay());
    /** The hourOfHalfday field type. */
    private static final DateTimeFieldType HOUR_OF_HALFDAY_TYPE = create("hourOfHalfday",
        HOUR_OF_HALFDAY, DurationFieldType.hours(), DurationFieldType.halfdays(),
        new ChronologyHourOfHalfday());
    /** The clockhourOfHalfday field type. */
    private static final DateTimeFieldType CLOCKHOUR_OF_HALFDAY_TYPE = create("clockhourOfHalfday",
        CLOCKHOUR_OF_HALFDAY, DurationFieldType.hours(), DurationFieldType.halfdays(),
        new ChronologyClockhourOfHalfday());
    /** The clockhourOfDay field type. */
    private static final DateTimeFieldType CLOCKHOUR_OF_DAY_TYPE = create("clockhourOfDay",
        CLOCKHOUR_OF_DAY, DurationFieldType.hours(), DurationFieldType.days(),
        new ChronologyClockhourOfDay());
    /** The hourOfDay field type. */
    private static final DateTimeFieldType HOUR_OF_DAY_TYPE = create("hourOfDay", HOUR_OF_DAY,
        DurationFieldType.hours(), DurationFieldType.days(), new ChronologyHourOfDay());
    /** The minuteOfDay field type. */
    private static final DateTimeFieldType MINUTE_OF_DAY_TYPE = create("minuteOfDay",
        MINUTE_OF_DAY, DurationFieldType.minutes(), DurationFieldType.days(),
        new ChronologyMinuteOfDay());
    /** The minuteOfHour field type. */
    private static final DateTimeFieldType MINUTE_OF_HOUR_TYPE = create("minuteOfHour",
        MINUTE_OF_HOUR, DurationFieldType.minutes(), DurationFieldType.hours(),
        new ChronologyMinuteOfHour());
    /** The secondOfDay field type. */
    private static final DateTimeFieldType SECOND_OF_DAY_TYPE = create("secondOfDay",
        SECOND_OF_DAY, DurationFieldType.seconds(), DurationFieldType.days(),
        new ChronologySecondOfDay());
    /** The secondOfMinute field type. */
    private static final DateTimeFieldType SECOND_OF_MINUTE_TYPE = create("secondOfMinute",
        SECOND_OF_MINUTE, DurationFieldType.seconds(), DurationFieldType.minutes(),
        new ChronologySecondOfMinute());
    /** The millisOfDay field type. */
    private static final DateTimeFieldType MILLIS_OF_DAY_TYPE = create("millisOfDay",
        MILLIS_OF_DAY, DurationFieldType.millis(), DurationFieldType.days(),
        new ChronologyMillisOfDay());
    /** The millisOfSecond field type. */
    private static final DateTimeFieldType MILLIS_OF_SECOND_TYPE = create("millisOfSecond",
        MILLIS_OF_SECOND, DurationFieldType.millis(), DurationFieldType.seconds(),
        new ChronologyMillisOfSecond());

    /** The name of the field. */
    private final String iName;

    //-----------------------------------------------------------------------
    /**
     * Constructor.
     *
     * @param name  the name to use
     */
    protected DateTimeFieldType(String name) {
        super();
        iName = name;
    }

    //-----------------------------------------------------------------------
    /**
     * Get the millis of second field type.
     *
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType millisOfSecond() {
        return MILLIS_OF_SECOND_TYPE;
    }

    /**
     * Get the millis of day field type.
     *
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType millisOfDay() {
        return MILLIS_OF_DAY_TYPE;
    }

    /**
     * Get the second of minute field type.
     *
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType secondOfMinute() {
        return SECOND_OF_MINUTE_TYPE;
    }

    /**
     * Get the second of day field type.
     *
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType secondOfDay() {
        return SECOND_OF_DAY_TYPE;
    }

    /**
     * Get the minute of hour field type.
     *
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType minuteOfHour() {
        return MINUTE_OF_HOUR_TYPE;
    }

    /**
     * Get the minute of day field type.
     *
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType minuteOfDay() {
        return MINUTE_OF_DAY_TYPE;
    }

    /**
     * Get the hour of day (0-23) field type.
     *
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType hourOfDay() {
        return HOUR_OF_DAY_TYPE;
    }

    /**
     * Get the hour of day (offset to 1-24) field type.
     *
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType clockhourOfDay() {
        return CLOCKHOUR_OF_DAY_TYPE;
    }

    /**
     * Get the hour of am/pm (0-11) field type.
     *
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType hourOfHalfday() {
        return HOUR_OF_HALFDAY_TYPE;
    }

    /**
     * Get the hour of am/pm (offset to 1-12) field type.
     *
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType clockhourOfHalfday() {
        return CLOCKHOUR_OF_HALFDAY_TYPE;
    }

    /**
     * Get the AM(0) PM(1) field type.
     *
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType halfdayOfDay() {
        return HALFDAY_OF_DAY_TYPE;
    }

    //-----------------------------------------------------------------------
    /**
     * Get the day of week field type.
     *
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType dayOfWeek() {
        return DAY_OF_WEEK_TYPE;
    }

    /**
     * Get the day of month field type.
     *
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType dayOfMonth() {
        return DAY_OF_MONTH_TYPE;
    }

    /**
     * Get the day of year field type.
     *
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType dayOfYear() {
        return DAY_OF_YEAR_TYPE;
    }

    /**
     * Get the week of a week based year field type.
     *
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType weekOfWeekyear() {
        return WEEK_OF_WEEKYEAR_TYPE;
    }

    /**
     * Get the year of a week based year field type.
     *
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType weekyear() {
        return WEEKYEAR_TYPE;
    }

    /**
     * Get the year of a week based year within a century field type.
     *
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType weekyearOfCentury() {
        return WEEKYEAR_OF_CENTURY_TYPE;
    }

    /**
     * Get the month of year field type.
     *
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType monthOfYear() {
        return MONTH_OF_YEAR_TYPE;
    }

    /**
     * Get the year field type.
     *
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType year() {
        return YEAR_TYPE;
    }

    /**
     * Get the year of era field type.
     *
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType yearOfEra() {
        return YEAR_OF_ERA_TYPE;
    }

    /**
     * Get the year of century field type.
     *
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType yearOfCentury() {
        return YEAR_OF_CENTURY_TYPE;
    }

    /**
     * Get the century of era field type.
     *
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType centuryOfEra() {
        return CENTURY_OF_ERA_TYPE;
    }

    /**
     * Get the era field type.
     *
     * @return the DateTimeFieldType constant
     */
    public static DateTimeFieldType era() {
        return ERA_TYPE;
    }

    //-----------------------------------------------------------------------
    /**
     * Get the name of the field.
     * <p>
     * By convention, names follow a pattern of "dddOfRrr", where "ddd" represents
     * the (singular) duration unit field name and "Rrr" represents the (singular)
     * duration range field name. If the range field is not applicable, then
     * the name of the field is simply the (singular) duration field name.
     *
     * @return field name
     */
    public String getName() {
        return iName;
    }

    /**
     * Get the duration unit of the field.
     *
     * @return duration unit of the field, never null
     */
    public abstract DurationFieldType getDurationType();

    /**
     * Get the duration range of the field.
     *
     * @return duration range of the field, null if unbounded
     */
    public abstract DurationFieldType getRangeDurationType();

    /**
     * Gets a suitable field for this type from the given Chronology.
     *
     * @param chronology  the chronology to use, null means ISOChronology in default zone
     * @return a suitable field
     */
    public abstract DateTimeField getField(Chronology chronology);

    /**
     * Checks whether this field supported in the given Chronology.
     *
     * @param chronology  the chronology to use, null means ISOChronology in default zone
     * @return true if supported
     */
    public boolean isSupported(Chronology chronology) {
        return getField(chronology).isSupported();
    }

    /**
     * Get a suitable debug string.
     *
     * @return debug string
     */
    public String toString() {
        return getName();
    }

    public static StandardDateTimeFieldType create(String name, byte ordinal,
        DurationFieldType unitType, DurationFieldType rangeType, ChronologyDateTimeField field) {
        StandardDateTimeFieldTypePool pool = StandardDateTimeFieldTypePool.getInstance();
        return pool.retrieve(name, ordinal, unitType, rangeType, field);
    }

    private static class StandardDateTimeFieldType extends DateTimeFieldType {
        /** Serialization version */
        private static final long serialVersionUID = -9937958251642L;

        /** The ordinal of the standard field type, for switch statements */
        private final byte iOrdinal;

        /** The unit duration of the field. */
        private final transient DurationFieldType iUnitType;
        /** The range duration of the field. */
        private final transient DurationFieldType iRangeType;
        private final transient ChronologyDateTimeField iChronology;

        /**
         * Constructor.
         *
         * @param name  the name to use
         * @param ordinal  the byte value for the oridinal index
         * @param unitType  the unit duration type
         * @param rangeType  the range duration type
         */
        StandardDateTimeFieldType(String name, byte ordinal,
            DurationFieldType unitType, DurationFieldType rangeType,
            ChronologyDateTimeField chronology) {
            super(name);
            iOrdinal = ordinal;
            iUnitType = unitType;
            iRangeType = rangeType;
            iChronology = chronology;
        }

        /** @inheritdoc */
        public DurationFieldType getDurationType() {
            return iUnitType;
        }

        /** @inheritdoc */
        public DurationFieldType getRangeDurationType() {
            return iRangeType;
        }

        public ChronologyDateTimeField getChronology() {
            return iChronology;
        }

        /** @inheritdoc */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof StandardDateTimeFieldType) {
                return iOrdinal == ((StandardDateTimeFieldType) obj).iOrdinal;
            }
            return false;
        }

        /** @inheritdoc */
        @Override
        public int hashCode() {
            return (1 << iOrdinal);
        }

        /** @inheritdoc */
        public DateTimeField getField(Chronology chronology) {
            chronology = DateTimeUtils.getChronology(chronology);
            StandardDateTimeFieldTypePool pool = StandardDateTimeFieldTypePool.getInstance();
            StandardDateTimeFieldType standardDateTimeFieldType = pool.obtain(iOrdinal);

            if (standardDateTimeFieldType == null) throw new InternalError();

            ChronologyDateTimeField field = standardDateTimeFieldType.getChronology();
            return field.obtain(chronology);
        }

        /**
         * Ensure a singleton is returned.
         *
         * @return the singleton type
         */
        private Object readResolve() {
            StandardDateTimeFieldTypePool pool = StandardDateTimeFieldTypePool.getInstance();
            Object dateTimeFieldType = pool.obtain(iOrdinal);

            if (dateTimeFieldType == null) return this;

            return pool.obtain(iOrdinal);
        }
    }

    private static class StandardDateTimeFieldTypePool
        extends PoolStandard<StandardDateTimeFieldType> {

        private static StandardDateTimeFieldTypePool myInstance;

        private StandardDateTimeFieldTypePool() {
        }

        public static StandardDateTimeFieldTypePool getInstance() {
            if (myInstance == null) {
                myInstance = new StandardDateTimeFieldTypePool();
            }

            return myInstance;
        }

        @Override
        protected StandardDateTimeFieldType create(String name, byte ordinal,
            DurationFieldType unitType, DurationFieldType rangeType,
            ChronologyDateTimeField field) {
            return new StandardDateTimeFieldType(name, ordinal, unitType, rangeType, field);
        }
    }
}
