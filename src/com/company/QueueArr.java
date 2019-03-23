package com.company;

import java.lang.RuntimeException;

public class QueueArr<E> {

  private E[] container;
  private int enqueueIdx; // points to the idx to write
  private int dequeueIdx; // points to the idx to read
  private int capacity;

  public QueueArr(Class<E> cls, int capacity) {
    // refer to https://stackoverflow.com/questions/529085/how-to-create-a-generic-array-in-java
    @SuppressWarnings("unchecked")
    this.container = (E[]) Array.newInstance(cls, capacity);

    this.enqueueIdx = 0;
    this.dequeueIdx = 0;
    this.capacity = capacity;
  }

  public void enqueue(E val) {
    if (isFull()) {
      throw new RuntimeException("QueueArr: queue overflow");
    }

    container[enqueueIdx++ % capacity] = val;
  }

  public E dequeue() {
    if (isEmpty()) {
      throw new RuntimeException("QueueArr: queue underflow");
    }

    return container[dequeueIdx++ % capacity];
  }

  public Option<E> safeDequeue() {
    return !isEmpty()
      ? new Option<>(dequeue())
      : new Option<>();
  }

  public E peek() {
    if (isEmpty()) {
      throw new RuntimeException("QueueArr: queue underflow");
    }

    return container[dequeueIdx % capacity];
  }

  public Option<E> safePeek() {
    return !isEmpty()
      ? new Option<>(peek())
      : new Option<>();
  }

  public int numItems() {
    return enqueueIdx - dequeueIdx;
  }

  public boolean isEmpty() {
    return numItems() <= 0;
  }

  public boolean isFull() {
    return numItems() >= capacity;
  }

}
