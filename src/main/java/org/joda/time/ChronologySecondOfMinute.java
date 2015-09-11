package org.joda.time;

import java.io.Serializable;

public class ChronologySecondOfMinute implements ChronologyDateTimeField, Serializable {

  public DateTimeField obtain(Chronology chronology) {
    return chronology.secondOfMinute();
  }
}
