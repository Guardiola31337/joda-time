package org.joda.time;

import java.io.Serializable;

public class ChronologyYearOfEra implements ChronologyDateTimeField, Serializable {

  public DateTimeField obtain(Chronology chronology) {
    return chronology.yearOfEra();
  }
}
