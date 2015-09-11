package org.joda.time;

import java.io.Serializable;

public class ChronologyHalfdayOfDay implements ChronologyDateTimeField, Serializable {

  public DateTimeField obtain(Chronology chronology) {
    return chronology.halfdayOfDay();
  }
}
