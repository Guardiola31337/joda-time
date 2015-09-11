package org.joda.time;

import java.io.Serializable;

public class ChronologyClockhourOfDay implements ChronologyDateTimeField, Serializable {

  public DateTimeField obtain(Chronology chronology) {
    return chronology.clockhourOfDay();
  }
}
