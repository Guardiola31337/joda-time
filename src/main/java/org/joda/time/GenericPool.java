package org.joda.time;

import java.util.HashMap;

public class GenericPool<T> {

  private HashMap<Integer, T> pool;

  public GenericPool() {
    this.pool = new HashMap<Integer, T>();
  }

  protected void add(int numeral, T type) {
    pool.put(new Integer(numeral), type);
  }

  protected T obtain(int numeral){
    T instance = pool.get(new Integer(numeral));

    return instance;
  }
}
