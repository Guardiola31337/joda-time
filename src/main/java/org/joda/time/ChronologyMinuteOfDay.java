package org.joda.time;

import java.io.Serializable;

public class ChronologyMinuteOfDay implements ChronologyDateTimeField, Serializable {

  public DateTimeField obtain(Chronology chronology) {
    return chronology.minuteOfDay();
  }
}
