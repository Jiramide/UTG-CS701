package com.company;

import java.lang.StringBuilder;

public class Stack<E> {

  private SinglyLinkedList<E> container;

  public Stack() {
    this.container = new SinglyLinkedList<>();
  }

  public void push(E val) {
    container.cons(val);
  }

  public E pop() {
    return container.uncons();
  }

  public E peek() {
    return container.index(0);
  }

  public boolean isEmpty() {
    return container.isEmpty();
  }

  @Override
  public String toString() {
    return container.length() + ": " + peek().toString();
  }

}
