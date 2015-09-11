package org.joda.time;

import java.io.Serializable;

public class ChronologyDayOfYear implements ChronologyDateTimeField, Serializable {

  public DateTimeField obtain(Chronology chronology) {
    return chronology.dayOfYear();
  }
}
