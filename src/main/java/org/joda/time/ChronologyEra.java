package org.joda.time;

import java.io.Serializable;

public class ChronologyEra implements ChronologyDateTimeField, Serializable {

  public DateTimeField obtain(Chronology chronology) {
    return chronology.era();
  }
}
