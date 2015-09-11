package org.joda.time;

public interface PoolRetrieverStandard<T> {

  T retrieve(String name, byte ordinal, DurationFieldType unitType, DurationFieldType rangeType,
      ChronologyDateTimeField field);
}
