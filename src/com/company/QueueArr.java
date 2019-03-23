package com.company;

public class QueueArr<E> {

  private E[] container;
  private int enqueueIdx;
  private int dequeueIdx;
  private int capacity;

  public QueueArr(Class<E> cls, int capacity) {
    // refer to https://stackoverflow.com/questions/529085/how-to-create-a-generic-array-in-java
    @SuppressWarnings("unchecked");
    this.container = (E[]) Array.newInstance(cls, capacity);

    this.enqueueIdx = 0;
    this.dequeueIdx = 0;
    this.capacity = capacity;

  }

}
