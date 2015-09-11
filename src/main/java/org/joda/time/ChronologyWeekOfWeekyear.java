package org.joda.time;

import java.io.Serializable;

public class ChronologyWeekOfWeekyear implements ChronologyDateTimeField, Serializable {

  public DateTimeField obtain(Chronology chronology) {
    return chronology.weekOfWeekyear();
  }
}
