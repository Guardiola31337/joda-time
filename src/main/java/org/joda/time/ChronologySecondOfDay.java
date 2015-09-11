package org.joda.time;

import java.io.Serializable;

public class ChronologySecondOfDay implements ChronologyDateTimeField, Serializable {

  public DateTimeField obtain(Chronology chronology) {
    return chronology.secondOfDay();
  }
}
