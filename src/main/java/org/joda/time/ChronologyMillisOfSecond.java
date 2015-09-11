package org.joda.time;

import java.io.Serializable;

public class ChronologyMillisOfSecond implements ChronologyDateTimeField, Serializable {

  public DateTimeField obtain(Chronology chronology) {
    return chronology.millisOfSecond();
  }
}
