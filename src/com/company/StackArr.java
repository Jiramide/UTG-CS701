package com.company;

import java.lang.reflect.Array;
import java.lang.StackOverflowError;
import java.lang.RuntimeException;

public class StackArr<E> {

  private E[] container;
  private int top; // top is the idx of the cell to write to. the most recent push is at top - 1
  private int capacity;

  public StackArr(Class<E> cls, int capacity) {
    // refer to https://stackoverflow.com/questions/529085/how-to-create-a-generic-array-in-java
    @SuppressWarnings("unchecked")
    final E[] container = (E[]) Array.newInstance(cls, capacity);
    this.container = container;

    this.top = 0;
    this.capacity = capacity;
  }

  public void push(E val) {
    if (isFull()) {
      throw new StackOverflowError("StackArr exceeds capacity of " + capacity);
    }

    container[top++] = val;
  }

  public E pop() {
    if (isEmpty()) {
      throw new RuntimeException("StackArr: stack underflow");
    }

    return container[--top];
  }

  public Option<E> safePop() {
    return !isEmpty()
      ? new Option<>(pop())
      : new Option<>();
  }

  public E peek() {
    if (isEmpty()) {
      throw new RuntimeException("StackArr: stack underflow");
    }

    return container[top - 1];
  }

  public Option<E> safePeek() {
    return !isEmpty()
      ? new Option<>(peek())
      : new Option<>();
  }

  public int numItems() {
    return top;
  }

  public boolean isEmpty() {
    return top <= 0;
  }

  public boolean isFull() {
    return top >= capacity;
  }

}
