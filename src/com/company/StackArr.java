package com.company;

import java.lang.reflect.Array;
import java.lang.StackOverflowError;
import java.lang.RuntimeException;

public class StackArr<E> {

  private E[] container;
  private int top; // top is the idx of the next empty slot. the top most item is at idx top - 1
  private int capacity;

  public StackArr(Class<E> cls, int capacity) {
    this.container = (E[]) Array.newInstance(cls, capacity);
    this.top = 0;
    this.capacity = capacity;
  }

  public void push(E val) {
    if (top >= capacity) {
      throw new StackOverflowError("StackArr exceeds capacity of " + capacity);
    }

    container[top++] = val;
  }

  public E pop() {
    if (top < 0) {
      throw new RuntimeException("StackArr: stack underflow");
    }

    return container[--top];
  }

  public Option<E> safePop() {
    return top >= 0
      ? new Option<>(pop())
      : new Option<>();
  }

  public E peek() {
    if (top < 0) {
      throw new RuntimeException("StackArr: stack underflow");
    }

    return container[top - 1];
  }

  public Option<E> safePeek() {
    return top >= 0
      ? new Option<>(peek())
      : new Option<>();
  }

}
