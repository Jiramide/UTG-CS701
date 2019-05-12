package com.company;

import java.lang.reflect.Array;
import java.util.Comparator;

public class HeapArr<E> {

  private E[] container;
  private Comparator<E> comp;
  private int len;

  //     0
  //    / \
  //   1   2
  //  /\   /\
  // 3  4 5  6

  /*
  * This HeapArr class generalizes the notion of a heap
  * and allows for custom ordering via the use of Comparator.
  *
  * This class satisfies the heap property in such a way such that
  * forall parent.
  *   comparator.compare(parent, leftChild(parent)) > 0 and
  *   comparator.compare(parent, rightChild(parent)) > 0
  *
  * If the comparator is thought of as a map from E^2 -> R, then this
  * heap class acts like a MaxHeap on the comparator results.
  * */

  public HeapArr(Class<E> cls, int capacity, Comparator<E> comp) {
    // refer to https://stackoverflow.com/questions/529085/how-to-create-a-generic-array-in-java
    @SuppressWarnings("unchecked")
    final E[] container = (E[]) Array.newInstance(cls, capacity);
    this.container = container;

    this.comp = comp;
    this.len = 0;
  }

  public HeapArr(E[] container, Comparator<E> comp) {
    this.container = container;
    this.comp = comp;
    this.len = container.length;

    heapify();
  }

  private void heapify() {
    // starts from the lowermost node with a child and sifts down
    for (int idx = getParentIdx(len - 1); idx >= 0; idx--) {
      siftDown(idx);
    }
  }

  private static <E> void swap(E[] arr, int idx0, int idx1) {
    E temp = arr[idx0];
    arr[idx0] = arr[idx1];
    arr[idx1] = temp;
  }

  private void siftUp(int idx) {
    int parentIdx = getParentIdx(idx);

    while (comp.compare(container[idx], container[parentIdx]) > 0) {
      swap(container, idx, parentIdx);
      idx = parentIdx;
      parentIdx = getParentIdx(idx);
    }
  }

  private int getBetterChildIdx(int idx) {
    int leftIdx = getLeftChildIdx(idx);
    int rightIdx = getRightChildIdx(idx);

    if (rightIdx >= len) {
      return leftIdx;
    }

    return comp.compare(container[leftIdx], container[rightIdx]) > 0
      ? leftIdx
      : rightIdx;
  }

  private void siftDown(int idx) {
    int betterChildIdx = getBetterChildIdx(idx);

    while (betterChildIdx < len && comp.compare(container[betterChildIdx], container[idx]) > 0) {
      swap(container, betterChildIdx, idx);
      idx = betterChildIdx;
      betterChildIdx = getBetterChildIdx(idx);
    }
  }

  private int getLeftChildIdx(int parent) {
    return 2*parent + 1;
  }

  private E getLeftChild(int parent) {
    return container[getLeftChildIdx(parent)];
  }

  private int getRightChildIdx(int parent) {
    return 2*parent + 2;
  }

  private E getRightChild(int parent) {
    return container[getRightChildIdx(parent)];
  }

  private int getParentIdx(int child) {
    return (int)Math.floor((child - 1)/2);
  }

  private E getParent(int child) {
    return container[getParentIdx(child)];
  }

  public int length() {
    return len;
  }

  public boolean isEmpty() {
    return len == 0;
  }

  public E pop() {
    E toPop = container[0];
    swap(container, 0, len - 1);
    len -= 1;
    siftDown(0);

    return toPop;
  }

  public void push(E val) {

  }

}
