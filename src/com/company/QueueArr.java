package com.company;

import java.lang.reflect.Array;
import java.lang.RuntimeException;

public class QueueArr<E> extends ArrStruct<E> {

  private int enqueueIdx; // points to the idx to write
  private int dequeueIdx; // points to the idx to read
  private int capacity;

  public QueueArr(E[] container) {
    super(container);
    this.enqueueIdx = 0;
    this.dequeueIdx = 0;
    this.capacity = container.length;
  }

  public QueueArr(Class<E> cls, int capacity) {
    super(cls, capacity);
    this.enqueueIdx = 0;
    this.dequeueIdx = 0;
    this.capacity = capacity;
  }

  public void enqueue(E val) throws RuntimeException {
    if (isFull()) {
      throw new RuntimeException("QueueArr: queue overflow");
    }

    container[enqueueIdx++ % capacity] = val;
  }

  public E dequeue() throws RuntimeException {
    if (isEmpty()) {
      throw new RuntimeException("QueueArr: queue underflow");
    }

    E ret = container[dequeueIdx++ % capacity];
    container[dequeueIdx % capacity] = null;

    return ret;
  }

  public Option<E> safeDequeue() {
    return !isEmpty()
      ? new Option<>(dequeue())
      : new Option<>();
  }

  public E peek() throws RuntimeException {
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
