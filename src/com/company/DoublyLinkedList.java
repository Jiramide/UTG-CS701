package com.company;

import java.util.Iterator;
import java.util.ListIterator;
import java.lang.Iterable;
import java.lang.StringBuilder;
import java.lang.IndexOutOfBoundsException;

class DoublyLinkedNode<E> {

  DoublyLinkedNode<E> next;
  DoublyLinkedNode<E> prev;
  E val;

  DoublyLinkedNode(E val) {
    this.val = val;
  }

}

class DoublyLinkedListIterator<E> implements ListIterator<E> {

  private DoublyLinkedNode<E> curr;
  private int currIdx;

  DoublyLinkedListIterator(DoublyLinkedList<E> collection) {
    this.curr = collection.indexNode(0);
    this.currIdx = 0;
  }

  DoublyLinkedListIterator(DoublyLinkedList<E> collection, int idx) {
    this.curr = collection.indexNode(idx);
    this.currIdx = idx;
  }

  public boolean hasNext() {
    return curr.next != null;
  }

  public boolean hasPrevious() {
    return curr.prev != null;
  }

  public E next() {
    DoublyLinkedNode<E> oldCurr = curr;
    curr = curr.next;
    currIdx += 1;

    return oldCurr.val;
  }

  public E previous() {
    DoublyLinkedNode<E> oldCurr = curr;
    curr = curr.prev;
    currIdx -= 1;

    return oldCurr.val;
  }

  public int nextIndex() {
    return currIdx + 1;
  }

  public int previousIndex() {
    return currIdx - 1;
  }

}

public class DoublyLinkedList<E> implements Iterable<E> {

  private DoublyLinkedNode<E> head;
  private DoublyLinkedNode<E> last;
  private int len;

  public DoublyLinkedList() {
    this.len = 0;
  }

  protected DoublyLinkedNode<E> indexNode(int idx) {
    if (idx >= len) {
      throw new IndexOutOfBoundsException("Cannot index element #" + idx + " in DoublyLinkedList.");
    }

    if (idx < len / 2) {

      DoublyLinkedNode<E> curr = head;

      for (; idx > 0; idx--) {
        curr = curr.next;
      }

      return curr;
    } else {

      DoublyLinkedNode<E> curr = last;

      for (idx = len - idx - 1; idx > 0; idx--) {
        curr = curr.prev;
      }

      return curr;
    }
  }

  protected Option<DoublyLinkedNode<E>> safeIndexNode(int idx) {
    return idx < len
      ? new Option<>(indexNode(idx))
      : new Option<>();
  }

  public E index(int idx) {
    return indexNode(idx).val;
  }

  public Option<E> safeIndex(int idx) {
    return safeIndexNode(idx).map(x -> x.val);
  }

  public int length() {
    return len;
  }

  private void addNodeAfter(DoublyLinkedNode<E> node, int idx) {
    if (idx == len - 1) {
      last.next = node;
      node.prev = last;
      last = node;

      len += 1;
      return;
    }


  }

  private void addNodeBefore(DoublyLinkedNode<E> node, int idx) {
    if (idx == 0) {
      node.next = head;
      head.prev = node;
      head = node;

      len += 1;
      return;
    }
  }

  public void addAfter(E val, int idx) {
    addNodeAfter(new DoublyLinkedNode<>(val), idx);
  }

  public void addBefore(E val, int idx) {
    addNodeBefore(new DoublyLinkedNode<>(val), idx);
  }

}
