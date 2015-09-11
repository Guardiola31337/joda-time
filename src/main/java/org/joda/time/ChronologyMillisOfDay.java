package org.joda.time;

import java.io.Serializable;

public class ChronologyMillisOfDay implements ChronologyDateTimeField, Serializable {

  public DateTimeField obtain(Chronology chronology) {
    return chronology.millisOfDay();
  }
}
