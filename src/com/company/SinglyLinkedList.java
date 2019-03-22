package com.company;

import java.util.Iterator;
import java.lang.Iterable;
import java.lang.StringBuilder;
import java.lang.IndexOutOfBoundsException;

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

  public SinglyLinkedList() {
    this.len = 0;
  }

  protected SinglyLinkedNode<E> indexNode(int idx) {
    if (idx >= len) {
      throw new IndexOutOfBoundsException("Cannot index element #" + idx + " in SinglyLinkedList.");
    }

    SinglyLinkedNode<E> curr = head;

    for (; idx > 0; idx--) {
      curr = curr.next;
    }

    return curr;
  }

  protected Option<SinglyLinkedNode<E>> safeIndexNode(int idx) {
    if (idx >= len) {
      return new Option<>();
    }

    return new Option<>(indexNode(idx));
  }

  public E index(int idx) {
    return indexNode(idx).val;
  }

  public Option<E> safeIndex(int idx) {
    return safeIndexNode(idx).map(n -> n.val);
  }

  public int length() {
    return len;
  }

  private void addNodeAfter(SinglyLinkedNode<E> node, int idx) {
    SinglyLinkedNode<E> before = indexNode(idx);

    node.next = before.next;
    before.next = node;

    len += 1;
  }

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
    if (idx >= len) {
      throw new IndexOutOfBoundsException("Cannot remove element #" + idx + " from SinglyLinkedList.");
    }

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

  public Option<E> safeRemove(int idx) {
    if (idx >= len) {
      return new Option<>();
    }

    return new Option<>(remove(idx));
  }

  public E uncons() {
    return remove(0);
  }

  public Option<E> safeUncons() {
    return safeRemove(0);
  }

  @Override
  public Iterator<E> iterator() {
    return new SinglyLinkedListIterator<E>(this);
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
    StringBuilder readable = new StringBuilder("SinglyLinkedList { ");

    for (SinglyLinkedNode<E> curr = head; curr != null; curr = curr.next) {
      readable.append(curr.val);
      readable.append("; ");
    }

    readable.append("}");
    return readable.toString();
  }

}
