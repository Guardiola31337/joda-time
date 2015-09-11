package org.joda.time;

import java.io.Serializable;

public class ChronologyHourOfDay implements ChronologyDateTimeField, Serializable {

  public DateTimeField obtain(Chronology chronology) {
    return chronology.hourOfDay();
  }
}
