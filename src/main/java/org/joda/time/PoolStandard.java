package org.joda.time;

import java.util.HashMap;

public abstract class PoolStandard<T> implements PoolRetrieverStandard<T> {

  private HashMap<Byte, T> pool;

  public PoolStandard() {
    this.pool = new HashMap<Byte, T>();
  }

  protected abstract T create(String name, byte ordinal, DurationFieldType unitType,
      DurationFieldType rangeType, ChronologyDateTimeField field);

  public T retrieve(String name, byte ordinal, DurationFieldType unitType,
      DurationFieldType rangeType, ChronologyDateTimeField field) {
    T result = obtain(ordinal);

    if (result == null) {
      result = create(name, ordinal, unitType, rangeType, field);
      add(ordinal, result);
    }

    return result;
  }

  private void add(Byte ordinal, T type) {
    pool.put(new Byte(ordinal), type);
  }

  protected T obtain(Byte ordinal) {
    T instance = pool.get(new Byte(ordinal));

    return instance;
  }
}
