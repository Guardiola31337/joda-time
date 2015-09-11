package org.joda.time;

import java.io.Serializable;

public class ChronologyHourOfHalfday implements ChronologyDateTimeField, Serializable {

  public DateTimeField obtain(Chronology chronology) {
    return chronology.hourOfHalfday();
  }
}
