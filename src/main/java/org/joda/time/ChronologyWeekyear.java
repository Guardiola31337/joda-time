package org.joda.time;

import java.io.Serializable;

public class ChronologyWeekyear implements ChronologyDateTimeField, Serializable {

  public DateTimeField obtain(Chronology chronology) {
    return chronology.weekyear();
  }
}
