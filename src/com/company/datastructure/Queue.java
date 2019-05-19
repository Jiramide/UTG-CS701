package com.company.datastructure;

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

  public int numItems() {
    return container.length();
  }

  public boolean isEmpty() {
    return container.isEmpty();
  }

  @Override
  public String toString() {
    return container.toString();
  }

}
