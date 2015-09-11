package org.joda.time;

import java.io.Serializable;

public class ChronologyDayOfMonth implements ChronologyDateTimeField, Serializable {

  public DateTimeField obtain(Chronology chronology) {
    return chronology.dayOfMonth();
  }
}
