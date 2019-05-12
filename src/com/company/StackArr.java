package com.company;

import java.lang.reflect.Array;

import javax.management.RuntimeErrorException;

import java.lang.StackOverflowError;
import java.lang.RuntimeException;

public class StackArr<E> extends ArrStruct<E> {

  private int top; // top is the idx of the cell to write to. the most recent push is at top - 1

  public StackArr(E[] container) {
    super(container);
    this.top = 0;
  }

  public StackArr(Class<E> cls, int capacity) {
    super(cls, capacity);
    this.top = 0;
  }

  public void push(E val) throws StackOverflowError {
    if (isFull()) {
      throw new StackOverflowError("StackArr exceeds capacity of " + getCapacity());
    }

    container[top++] = val;
  }

  public E pop() throws RuntimeErrorException {
    if (isEmpty()) {
      throw new RuntimeException("StackArr: stack underflow");
    }

    E ret = container[--top];
    container[top] = null;

    return ret;
  }

  public Option<E> safePop() {
    return !isEmpty()
      ? new Option<>(pop())
      : new Option<>();
  }

  public E peek() throws RuntimeErrorException {
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
    return top >= getCapacity();
  }

}
