/*
 * Joda Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-03 Stephen Colebourne.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Joda project (http://www.joda.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The name "Joda" must not be used to endorse or promote products
 *    derived from this software without prior written permission. For
 *    written permission, please contact licence@joda.org.
 *
 * 5. Products derived from this software may not be called "Joda",
 *    nor may "Joda" appear in their name, without prior written
 *    permission of the Joda project.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE JODA AUTHORS OR THE PROJECT
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Joda project and was originally
 * created by Stephen Colebourne <scolebourne@joda.org>. For more
 * information on the Joda project, please see <http://www.joda.org/>.
 */
package org.joda.time;

import org.joda.time.chrono.iso.ISOChronology;
import org.joda.time.format.DateTimePrinter;
import org.joda.time.format.ISODateTimeFormat;

/**
 * AbstractInterval provides the common behaviour for time intervals.
 * <p>
 * This class should generally not be used directly by API users. The 
 * {@link ReadableInterval} interface should be used when different 
 * kinds of intervals are to be referenced.
 *
 * @author Brian S O'Neill
 * @author Stephen Colebourne
 * @since 1.0
 */
public abstract class AbstractInterval implements ReadableInterval {
    
    /** The start of the period */
    private long iStartMillis;
    /** The end of the period */
    private long iEndMillis;

    /** Cache the start instant */
    private transient Instant iStartInstant;
    /** Cache the end instant */
    private transient Instant iEndInstant;
    /** Cache the duration */
    private transient Duration iDuration;
    
    /**
     * Constructs a time interval as a copy of another.
     * 
     * @param interval the time interval to copy
     * @throws IllegalArgumentException if the interval is null
     */
    public AbstractInterval(ReadableInterval interval) {
        super();
        if (interval == null) {
            throw new IllegalArgumentException("The interval must not be null");
        }
        iStartMillis = interval.getStartMillis();
        iEndMillis = interval.getEndMillis();
    }
    
    /**
     * Constructs an interval from a start and end instant.
     * 
     * @param startInstant  start of this interval, as milliseconds from
     *  1970-01-01T00:00:00Z.
     * @param endInstant  end of this interval, as milliseconds from
     *  1970-01-01T00:00:00Z.
     */
    public AbstractInterval(long startInstant, long endInstant) {
        super();
        iStartMillis = startInstant;
        iEndMillis = endInstant;
    }
    
    /**
     * Constructs an interval from a start and end instant.
     * 
     * @param start  start of this interval
     * @param end  end of this interval
     * @throws IllegalArgumentException if either instant is null
     */
    public AbstractInterval(ReadableInstant start, ReadableInstant end) {
        super();
        if (start == null) {
            throw new IllegalArgumentException("The start instant must not be null");
        }
        if (end == null) {
            throw new IllegalArgumentException("The end instant must not be null");
        }
        iStartMillis = start.getMillis();
        if (start instanceof Instant) {
            iStartInstant = (Instant) start;
        }
        iEndMillis = end.getMillis();
        if (end instanceof Instant) {
            iEndInstant = (Instant) end;
        }
    }
    
    /**
     * Constructs an interval from a start instant and a duration.
     * 
     * @param start  start of this interval
     * @param duration  duration of this interval
     */
    public AbstractInterval(ReadableInstant start, ReadableDuration duration) {
        super();
        if (start == null) {
            throw new IllegalArgumentException("The start instant must not be null");
        }
        if (duration == null) {
            throw new IllegalArgumentException("The duration must not be null");
        }
        iStartMillis = start.getMillis();
        if (start instanceof Instant) {
            iStartInstant = (Instant) start;
        }
        iEndMillis = duration.addTo((ReadableInstant) start, 1).getMillis();
    }
    
    /**
     * Constructs an interval from a duration and an end instant.
     * 
     * @param duration duration of this interval
     * @param end end of this interval
     */
    public AbstractInterval(ReadableDuration duration, ReadableInstant end) {
        super();
        if (duration == null) {
            throw new IllegalArgumentException("The duration must not be null");
        }
        if (end == null) {
            throw new IllegalArgumentException("The end instant must not be null");
        }
        iEndMillis = end.getMillis();
        if (end instanceof Instant) {
            iEndInstant = (Instant) end;
        }
        iStartMillis = duration.addTo((ReadableInstant) end, -1).getMillis();
    }

    //-----------------------------------------------------------------------
    /**
     * Gets the start of this interval as the number of milliseconds elapsed
     * since 1970-01-01T00:00:00Z.
     *
     * @return the start of the interval
     */
    public long getStartMillis() {
        return iStartMillis;
    }

    /**
     * Gets the start of this time interval as an Instant.
     *
     * @return the start of the time interval
     */
    public Instant getStartInstant() {
        if (iStartInstant == null) {
            iStartInstant = new Instant(getStartMillis());
        }
        return iStartInstant;
    }

    /**
     * Gets the end of this interval as the number of milliseconds elapsed
     * since 1970-01-01T00:00:00Z.
     *
     * @return the start of the interval
     */
    public long getEndMillis() {
        return iEndMillis;
    }

    /**
     * Gets the end of this time interval as an Instant.
     *
     * @return the end of the time interval
     */
    public Instant getEndInstant() {
        if (iEndInstant == null) {
            iEndInstant = new Instant(getEndMillis());
        }
        return iEndInstant;
    }

    /**
     * Gets the duration of this time interval in milliseconds.
     * <p>
     * The duration returned will always be precise because it is relative to
     * a known date.
     *
     * @return the duration of the time interval in milliseconds
     */
    public long getDurationMillis() {
        return (getEndMillis() - getStartMillis());
    }

    /**
     * Gets the duration of this time interval.
     * <p>
     * The duration returned will always be precise because it is relative to
     * a known date.
     *
     * @return the duration of the time interval
     */
    public Duration getDuration() {
        if (iDuration == null) {
            iDuration = new Duration(DurationType.getDayHourType(),
                                     getEndMillis() - getStartMillis());
        }
        return iDuration;
    }

    //-----------------------------------------------------------------------
    /**
     * Does this time interval contain the specified millisecond instant.
     * 
     * @param millisInstant  the instant to compare to,
     *  millisecond instant from 1970-01-01T00:00:00Z
     * @return true if this time interval contains the millisecond
     */
    public boolean contains(long millisInstant) {
        return (millisInstant >= getStartMillis() && millisInstant <= getEndMillis());
    }
    
    /**
     * Does this time interval contain the specified instant.
     * 
     * @param instant  the instant
     * @return true if this time interval contains the instant
     * @throws IllegalArgumentException if the instant is null
     */
    public boolean contains(ReadableInstant instant) {
        if (instant == null) {
            throw new IllegalArgumentException("The instant must not be null");
        }
        return contains(instant.getMillis());
    }
    
    /**
     * Does this time interval contain the specified time interval completely.
     * 
     * @param interval  the time interval to compare to
     * @return true if this interval contains the time interval
     * @throws IllegalArgumentException if the interval is null
     */
    public boolean contains(ReadableInterval interval) {
        if (interval == null) {
            throw new IllegalArgumentException("The time interval must not be null");
        }
        long otherStart = interval.getStartMillis();
        long otherEnd = interval.getEndMillis();
        return 
            (otherStart >= getStartMillis() && otherStart <= getEndMillis())
            && (otherEnd >= getStartMillis() && otherEnd <= getEndMillis());
    }
    
    /**
     * Does this time interval overlap the specified time interval.
     * <p>
     * The intervals overlap if at least some of the time interval is in common.
     * 
     * @param interval  the time interval to compare to
     * @return true if the time intervals overlap
     * @throws IllegalArgumentException if the interval is null
     */
    public boolean overlaps(ReadableInterval interval) {
        if (interval == null) {
            throw new IllegalArgumentException("The time interval must not be null");
        }
        long otherStart = interval.getStartMillis();
        long otherEnd = interval.getEndMillis();
        return 
            (otherStart >= getStartMillis() && otherStart <= getEndMillis())
            || (otherEnd >= getStartMillis() && otherEnd <= getEndMillis());
    }
    
    //-----------------------------------------------------------------------
    /**
     * Is this time interval before the specified millisecond instant.
     * 
     * @param millisInstant  the instant to compare to,
     *  millisecond instant from 1970-01-01T00:00:00Z
     * @return true if this time interval is before the instant
     */
    public boolean isBefore(long millisInstant) {
        return (getStartMillis() < millisInstant && getEndMillis() < millisInstant);
    }
    
    /**
     * Is this time interval before the specified instant.
     * 
     * @param instant  the instant to compare to
     * @return true if this time interval is before the instant
     * @throws IllegalArgumentException if the instant is null
     */
    public boolean isBefore(ReadableInstant instant) {
        if (instant == null) {
            throw new IllegalArgumentException("The instant must not be null");
        }
        return isBefore(instant.getMillis());
    }
    
    /**
     * Is this time interval after the specified millisecond instant.
     * 
     * @param millisInstant  the instant to compare to,
     *  millisecond instant from 1970-01-01T00:00:00Z
     * @return true if this time interval is after the instant
     */
    public boolean isAfter(long millisInstant) {
        return (getStartMillis() > millisInstant && getEndMillis() > millisInstant);
    }
    
    /**
     * Is this time interval after the specified instant.
     * 
     * @param instant  the instant to compare to
     * @return true if this time interval is after the instant
     * @throws IllegalArgumentException if the instant is null
     */
    public boolean isAfter(ReadableInstant instant) {
        if (instant == null) {
            throw new IllegalArgumentException("The instant must not be null");
        }
        return isAfter(instant.getMillis());
    }
    
    //-----------------------------------------------------------------------
    /**
     * Get the object as an Interval.
     * 
     * @return an immutable interval object
     */
    public Interval toInterval() {
        if (this instanceof Interval) {
            return (Interval) this;
        }
        return new Interval(this);
    }

    /**
     * Get the object as a MutableInterval.
     * 
     * @return a mutable interval object
     */
    public MutableInterval toMutableInterval() {
        return new MutableInterval(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Compares this object with the specified object for equality based
     * on start and end millis. All ReadableInterval instances are accepted.
     * <p>
     * To compare the duration of two time intervals, use {@link #getDuration()}
     * to get the durations and compare those.
     *
     * @param readableInterval  a readable interval to check against
     * @return true if the start and end millis are equal
     */
    public boolean equals(Object readableInterval) {
        if (this == readableInterval) {
            return true;
        }
        if (readableInterval instanceof ReadableInterval == false) {
            return false;
        }
        ReadableInterval other = (ReadableInterval) readableInterval;
        return 
            (getStartMillis() == other.getStartMillis() 
            && getEndMillis() == other.getEndMillis());
    }

    /**
     * Hashcode compatable with equals method.
     *
     * @return suitable hashcode
     */
    public int hashCode() {
        int result = 97;
        result = 31 * result + ((int) (getStartMillis() ^ (getStartMillis() >>> 32)));
        result = 31 * result + ((int) (getEndMillis() ^ (getEndMillis() >>> 32)));
        return result;
    }

    /**
     * Output a string in ISO8601 interval format.
     *
     * @return re-parsable string
     */
    public String toString() {
        DateTimePrinter printer =
            ISODateTimeFormat.getInstance(ISOChronology.getInstanceUTC())
            .dateHourMinuteSecondFraction();
        StringBuffer buf = new StringBuffer(48);
        printer.printTo(buf, getStartMillis());
        buf.append('/');
        printer.printTo(buf, getEndMillis());
        return buf.toString();
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the start of this time interval.
     * <p>
     * Subclasses that wish to be immutable should override this method with an
     * empty implementation that is protected and final. This also ensures that
     * all lower subclasses are also immutable.
     *
     * @param millisInstant  the start of the time interval,
     *  millisecond instant from 1970-01-01T00:00:00Z
     */
    protected void setStartMillis(long millisInstant) {
        iStartMillis = millisInstant;
        iStartInstant = null;
        iDuration = null;
    }

    /** 
     * Sets the end of this time interval.
     * <p>
     * Subclasses that wish to be immutable should override this method with an
     * empty implementation that is protected and final. This also ensures that
     * all lower subclasses are also immutable.
     *
     * @param millisInstant  the end of the time interval,
     *  millisecond instant from 1970-01-01T00:00:00Z
     */
    protected void setEndMillis(long millisInstant) {
        iEndMillis = millisInstant;
        iEndInstant = null;
        iDuration = null;
    }

}
