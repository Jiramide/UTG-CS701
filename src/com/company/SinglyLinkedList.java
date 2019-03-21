package com.company;

import java.util.Iterator;
import java.lang.Iterable;

class SinglyLinkedNode<E> {

  SinglyLinkedNode<E> next;
  E val;

  SinglyLinkedNode(E val) {
    this.val = val;
  }

}

class SinglyLinkedListIterator<E> implements Iterator<E> {

  private SinglyLinkedNode<E> curr;

  SinglyLinkedListIterator(SinglyLinkedList<E> collection) {
    this.curr = collection.indexNode(0);
  }

  @Override
  public boolean hasNext() {
    return curr != null;
  }

  @Override
  public E next() {
    SinglyLinkedNode<E> oldCurr = curr;
    curr = curr.next;

    return oldCurr.val;
  }

}

public class SinglyLinkedList<E> implements Iterable<E> {

  private SinglyLinkedNode<E> head;
  private int len;

  // ! SinglyLinkedList()
  // ? A nullary SinglyLinkedList<E> constructor.
  // ? Sets default length to 0 and head to null.
  // * Expected runtime: O(1)
  public SinglyLinkedList() {
    this.len = 0;
  }

  // ! SinglyLinkedNode<E> indexNode(int idx)
  // ? Gets the `idx`th node in the linked list by
  // ? traversing the list until we get to the desired index.
  // * Expected runtime: O(n)
  protected SinglyLinkedNode<E> indexNode(int idx) {
    SinglyLinkedNode<E> curr = head;

    for (; idx > 0; idx--) {
      curr = curr.next;
    }

    return curr;
  }

  // ! E index(int idx)
  // ? Gets the `idx`th node's value in the linked list.
  // ? Relies and expects that `indexNode` is to return a non null value.
  // * Expected runtime: O(n)
  public E index(int idx) {
    return indexNode(idx).val;
  }

  // ! int length()
  // ? Returns the length of the SinglyLinkedList<E>.
  // * Expected runtime: O(1)
  public int length() {
    return len;
  }

  // ! void addAfter(SinglyLinkedNode<E> node, int idx)
  // ? Adds `node` into the linked list by putting it AFTER the
  // ? `idx`th node.
  // * Expected runtime: O(n)
  private void addNodeAfter(SinglyLinkedNode<E> node, int idx) {
    SinglyLinkedNode<E> before = indexNode(idx);

    node.next = before.next;
    before.next = node;

    len += 1;
  }

  // ! void addBefore(SinglyLinkedNode<E> node, int idx)
  // ? Adds `node` into the linked list by putting it BEFORE the
  // ? `idx`th node.
  // * Expected runtime: O(n)
  private void addNodeBefore(SinglyLinkedNode<E> node, int idx) {
    if (idx == 0) {
      node.next = head;
      head = node;

      len += 1;
      return;
    }

    addNodeAfter(node, idx - 1);
  }

  public void addBefore(E val, int idx) {
    addNodeBefore(new SinglyLinkedNode<>(val), idx);
  }

  public void addAfter(E val, int idx) {
    addNodeAfter(new SinglyLinkedNode<>(val), idx);
  }

  public void cons(E val) {
    addBefore(val, 0);
  }

  public E remove(int idx) {
    if (idx == 0) {
      SinglyLinkedNode<E> oldHead = head;
      head = head.next;
      oldHead.next = null;

      len -= 1;
      return oldHead.val;
    }

    SinglyLinkedNode<E> before = indexNode(idx - 1);
    SinglyLinkedNode<E> toRemove = before.next;

    before.next = toRemove.next;
    toRemove.next = null;

    len -= 1;
    return toRemove.val;
  }

  public E uncons() {
    return remove(0);
  }

  @Override
  public Iterator<E> iterator() {
    return (Iterator<E>)new SinglyLinkedListIterator<E>(this);
  }

  @Override
  public SinglyLinkedList<E> clone() {
    if (len == 0) {
      return new SinglyLinkedList<>();
    }

    SinglyLinkedList<E> listClone = new SinglyLinkedList<>();
    SinglyLinkedNode<E> listLast = new SinglyLinkedNode<>(head.val);

    listClone.head = listLast;

    for (SinglyLinkedNode<E> curr = head.next; curr != null; curr = curr.next) {
      listLast.next = new SinglyLinkedNode<>(curr.val);
      listLast = listLast.next;
    }

    listClone.len = len;
    return listClone;
  }

  public int indexOf(E elem) {
    int currIdx = 0;

    for (SinglyLinkedNode<E> curr = head; curr != null; curr = curr.next) {
      if (curr.val == elem) {
        return currIdx;
      }

      currIdx += 1;
    }

    return -1;
  }

  public boolean contains(E elem) {
    return indexOf(elem) != -1;
  }

  public boolean isEmpty() {
    return len == 0;
  }

  @Override
  public String toString() {
    String readable = "SinglyLinkedList { ";

    for (SinglyLinkedNode<E> curr = head; curr != null; curr = curr.next) {
      readable += curr.val.toString() + "; ";
    }

    return readable + "}";
  }

}
