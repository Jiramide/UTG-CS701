package com.company;

public class Queue<E> {

  private DoublyLinkedList<E> container;

  public Queue() {
    this.container = new DoublyLinkedList<>();
  }

  public void enqueue(E val) {
    container.consLast(val);
  }

  public E dequeue() {
    return container.uncons();
  }

  public Option<E> safeDequeue() {
    return container.safeUncons();
  }

  public E peek() {
    return container.peek();
  }

  public Option<E> safePeek() {
    return container.safePeek();
  }

  public boolean isEmpty() {
    return container.isEmpty();
  }

  @Override
  public String toString() {
    throw new java.lang.UnsupportedOperationException(
      "Queue.toString is not implemented because I can't decide on how to show a Queue.");
  }

}
