package org.joda.time;

import java.io.Serializable;

public class ChronologyDayOfWeek implements ChronologyDateTimeField, Serializable {

  public DateTimeField obtain(Chronology chronology) {
    return chronology.dayOfWeek();
  }
}
