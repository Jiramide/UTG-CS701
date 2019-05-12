package com.company;

import java.lang.reflect.Array;

public abstract class ArrStruct<E> {

  private E[] container;

  public ArrStruct(E[] container) {
    this.container = container;
  }

  public ArrStruct(Class<E> cls, int capacity) {
    // refer to https://stackoverflow.com/questions/529085/how-to-create-a-generic-array-in-java
    @SuppressWarnings("unchecked")
    final E[] container = (E[]) Array.newInstance(cls, capacity);
    this.container = container;
  }

}
