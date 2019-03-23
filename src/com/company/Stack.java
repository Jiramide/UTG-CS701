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

  public Option<E> safePop() {
    return container.safeUncons();
  }

  public E peek() {
    return container.index(0);
  }

  public Option<E> safePeek() {
    return container.safeIndex(0);
  }

  public int numItems() {
    return container.length();
  }

  public boolean isEmpty() {
    return container.isEmpty();
  }

  @Override
  public String toString() {
    return isEmpty()
      ? "[~]: Null"
      : "[" + container.length() + "]: " + peek().toString();
  }

}
