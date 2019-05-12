package com.company;

import java.lang.reflect.Array;
import java.util.Arrays;

public abstract class ArrStruct<E> {

  E[] container;

  public ArrStruct(E[] container) {
    this.container = container;
  }

  public ArrStruct(Class<E> cls, int capacity) {
    // refer to https://stackoverflow.com/questions/529085/how-to-create-a-generic-array-in-java
    @SuppressWarnings("unchecked")
    final E[] container = (E[]) Array.newInstance(cls, capacity);
    this.container = container;
  }

  private int getCapacity() {
    return container.length;
  }

  public void resize(int newCapacity) {
    container = Arrays.copyOf(container, newCapacity);
  }

}
