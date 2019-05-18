package com.company;

import java.lang.reflect.Array;

public class QueueArr<E> extends ArrStruct<E> {

  private int enqueueIdx; // points to the idx to write
  private int dequeueIdx; // points to the idx to read

  public QueueArr(E[] container) {
    super(container);
    this.enqueueIdx = container.length;
    this.dequeueIdx = 0;
  }

  public QueueArr(Class<E> cls, int capacity) {
    super(cls, capacity);
    this.enqueueIdx = 0;
    this.dequeueIdx = 0;
  }

  public void enqueue(E val) throws RuntimeException {
    if (isFull()) {
      throw new RuntimeException("QueueArr: queue overflow");
    }

    container[enqueueIdx++ % getCapacity()] = val;
  }

  public E dequeue() throws RuntimeException {
    if (isEmpty()) {
      throw new RuntimeException("QueueArr: queue underflow");
    }

    int capacity = getCapacity();
    E ret = container[dequeueIdx++ % capacity];
    container[(dequeueIdx - 1) % capacity] = null;

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

    return container[dequeueIdx % getCapacity()];
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
    return numItems() >= getCapacity();
  }

}
