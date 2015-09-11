package org.joda.time;

import java.io.Serializable;

public class ChronologyMinuteOfHour implements ChronologyDateTimeField, Serializable {

  public DateTimeField obtain(Chronology chronology) {
    return chronology.minuteOfHour();
  }
}
