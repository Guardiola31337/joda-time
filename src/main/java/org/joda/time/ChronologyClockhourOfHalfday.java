package org.joda.time;

import java.io.Serializable;

public class ChronologyClockhourOfHalfday implements ChronologyDateTimeField, Serializable {

  public DateTimeField obtain(Chronology chronology) {
    return chronology.clockhourOfHalfday();
  }
}
