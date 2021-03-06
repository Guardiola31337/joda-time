package org.joda.time;

import java.io.Serializable;

public class ChronologyYear implements ChronologyDateTimeField, Serializable {

  public DateTimeField obtain(Chronology chronology) {
    return chronology.year();
  }
}
