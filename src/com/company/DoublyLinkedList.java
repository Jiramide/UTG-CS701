package com.company;

import java.util.Iterator;
import java.util.ListIterator;
import java.lang.Iterable;
import java.lang.StringBuilder;
import java.lang.IndexOutOfBoundsException;
import java.lang.UnsupportedOperationException;

class DoublyLinkedNode<E> {

  DoublyLinkedNode<E> next;
  DoublyLinkedNode<E> prev;
  E val;

  DoublyLinkedNode(E val) {
    this.val = val;
  }

  static <E> void makeNeighbours(DoublyLinkedNode<E> n0, DoublyLinkedNode<E> n1, DoublyLinkedNode<E> n2) {
    if (n0 != null) {
      n0.next = n1;
    }

    if (n2 != null) {
      n2.prev = n1;
    }

    if (n1 != null) {
      n1.prev = n0;
      n1.next = n2;
    }
  }

  static <E> void relinkNeighbours(DoublyLinkedNode<E> node) {
    if (node.prev != null) {
      node.prev.next = node.next;
    }

    if (node.next != null) {
      node.next.prev = node.prev;
    }
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

  @Override
  public boolean hasNext() {
    return curr != null;
  }

  @Override
  public boolean hasPrevious() {
    return curr != null;
  }

  @Override
  public E next() {
    DoublyLinkedNode<E> oldCurr = curr;
    curr = curr.next;
    currIdx += 1;

    return oldCurr.val;
  }

  @Override
  public E previous() {
    DoublyLinkedNode<E> oldCurr = curr;
    curr = curr.prev;
    currIdx -= 1;

    return oldCurr.val;
  }

  @Override
  public int nextIndex() {
    return currIdx + 1;
  }

  @Override
  public int previousIndex() {
    return currIdx - 1;
  }

  @Override
  public void add(E elem) {
    throw new UnsupportedOperationException(
      "DoublyLinkedListIterator.add is not supported by this data structure.");
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException(
      "DoublyLinkedListIterator.remove is not supported by this data structure.");
  }

  @Override
  public void set(E elem) {
    throw new UnsupportedOperationException(
      "DoublyLinkedListIterator.set is not supported by this data structure.");
  }

}

public class DoublyLinkedList<E> implements Iterable<E> {

  private DoublyLinkedNode<E> head;
  private DoublyLinkedNode<E> last;
  private int len;

  public DoublyLinkedList() {
    this.len = 0;
  }

  public boolean validIndex(int idx) {
    return idx >= 0 && idx < len;
  }

  protected DoublyLinkedNode<E> indexNode(int idx) {
    if (!validIndex(idx)) {
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
    return validIndex(idx)
      ? new Option<>(indexNode(idx)) // Safe to index.
      : new Option<>(); // Unsafe to index, return empty Option.
  }

  public E index(int idx) {
    return indexNode(idx).val;
  }

  public Option<E> safeIndex(int idx) {
    return validIndex(idx)
      ? new Option<>(index(idx)) // Safe to index.
      : new Option<>(); // Unsafe to index, return empty Option.
  }

  public int length() {
    return len;
  }

  private void addNodeAfter(DoublyLinkedNode<E> node, int idx) {
    if (len == 0) {
      head = node;
      last = node;

      len += 1;
      return;
    }

    DoublyLinkedNode<E> before = indexNode(idx);
    DoublyLinkedNode.makeNeighbours(before, node, before.next);

    if (idx == len - 1) {
      last = node;
    }

    len += 1;
  }

  private void addNodeBefore(DoublyLinkedNode<E> node, int idx) {
    if (len == 0) {
      head = node;
      last = node;

      len += 1;
      return;
    }

    DoublyLinkedNode<E> after = indexNode(idx);
    DoublyLinkedNode.makeNeighbours(after.prev, node, after);

    if (idx == 0) {
      head = node;
    }

    len += 1;
  }

  public void addAfter(E val, int idx) {
    addNodeAfter(new DoublyLinkedNode<>(val), idx);
  }

  public void addBefore(E val, int idx) {
    addNodeBefore(new DoublyLinkedNode<>(val), idx);
  }

  public void cons(E val) {
    addBefore(val, 0);
  }

  public void consLast(E val) {
    addAfter(val, len - 1);
  }

  public E remove(int idx) {
    if (!validIndex(idx)) {
      throw new IndexOutOfBoundsException("Cannot remove element #" + idx + " from DoublyLinkedList.");
    }

    DoublyLinkedNode<E> toRemove = indexNode(idx);

    if (idx == 0) {
      head = toRemove.next;
    } else if (idx == len - 1) {
      last = toRemove.prev;
    }

    DoublyLinkedNode.relinkNeighbours(toRemove);
    toRemove.next = null;
    toRemove.prev = null;

    E val = toRemove.val;
    len -= 1;
    toRemove = null;

    return val;
  }

  public Option<E> safeRemove(int idx) {
    return validIndex(idx)
      ? new Option<>(remove(idx)) // Safe to remove.
      : new Option<>(); // Unsafe to remove, return empty Option.
  }

  public E uncons() {
    return remove(0);
  }

  public Option<E> safeUncons() {
    return safeRemove(0);
  }

  public E unconsLast() {
    return remove(len - 1);
  }

  public Option<E> safeUnconsLast() {
    return safeRemove(len - 1);
  }

  public E peek() {
    return index(0);
  }

  public Option<E> safePeek() {
    return safeIndex(0);
  }

  public E peekLast() {
    return index(len - 1);
  }

  public Option<E> safePeekLast() {
    return safeIndex(len - 1);
  }

  @Override
  public Iterator<E> iterator() {
    return new DoublyLinkedListIterator<>(this);
  }

  @Override
  public DoublyLinkedList<E> clone() {
    DoublyLinkedList<E> listClone = new DoublyLinkedList<>();

    for (DoublyLinkedNode<E> curr = head; curr != null; curr = curr.next) {
      listClone.consLast(curr.val);
    }

    return listClone;
  }

  public int indexOf(E elem) {
    int currIdx = 0;

    for (DoublyLinkedNode<E> curr = head; curr != null; curr = curr.next) {
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
    StringBuilder readable = new StringBuilder("DoublyLinkedList { ");

    for (DoublyLinkedNode<E> curr = head; curr != null; curr = curr.next) {
      readable.append(curr.val);
      readable.append("; ");
    }

    readable.append("}");
    return readable.toString();
  }

}
