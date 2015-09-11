package org.joda.time;

import java.io.Serializable;

public class ChronologyCenturyOfEra implements ChronologyDateTimeField, Serializable {

  public DateTimeField obtain(Chronology chronology) {
    return chronology.centuryOfEra();
  }
}
