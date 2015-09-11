package org.joda.time;

import java.io.Serializable;

public class ChronologyYearOfCentury implements ChronologyDateTimeField, Serializable {

  public DateTimeField obtain(Chronology chronology) {
    return chronology.yearOfCentury();
  }
}
