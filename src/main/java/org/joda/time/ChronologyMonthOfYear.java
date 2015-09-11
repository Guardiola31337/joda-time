package org.joda.time;

import java.io.Serializable;

public class ChronologyMonthOfYear implements ChronologyDateTimeField, Serializable {

  public DateTimeField obtain(Chronology chronology) {
    return chronology.monthOfYear();
  }
}
