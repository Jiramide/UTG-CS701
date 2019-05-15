package com.company;

import java.util.Comparator;
import java.lang.Comparable;

class HeapNode<E> {

  E val;
  HeapNode<E> parent;
  HeapNode<E> left;
  HeapNode<E> right;

  HeapNode(E val) {
    this.val = val;
    this.parent = null;
    this.left = null;
    this.right = null;
  }

  static <E> void setAsLeft(HeapNode<E> parent, HeapNode<E> left) {
    parent.left = left;
    left.parent = parent;
  }

  static <E> void setAsRight(HeapNode<E> parent, HeapNode<E> right) {
    parent.right = right;
    right.parent = parent;
  }

  static <E> void swap(HeapNode<E> node0, HeapNode<E> node1) {
    E temp = node0.val;
    node0.val = node1.val;
    node1.val = temp;
  }

}

public class Heap<E> {

  private Comparator<? super E> comp;
  private int len;
  private HeapNode<E> root;

  private void heapify(HeapNode<E> node) {
    if (node == null) {
      return;
    }

    heapify(node.left);
    heapify(node.right);
    siftDown(node);
  }

  private void siftUp(HeapNode<E> node) {
    HeapNode<E> parent = node.parent;

    while (parent != null && comp.compare(node.val, parent.val) > 0) {
      HeapNode.swap(node, parent);
      node = parent;
      parent = node.parent;
    }
  }

  private HeapNode<E> getBetterChild(HeapNode<E> parent) {
    if (parent.right == null) {
      return parent.left;
    }

    return comp.compare(parent.left.val, parent.right.val) > 0
      ? parent.left
      : parent.right;
  }

  private void siftDown(HeapNode<E> node) {
    HeapNode<E> betterChild = getBetterChild(node);

    while (betterChild != null && comp.compare(betterChild.val, node.val) > 0) {
      HeapNode.swap(node, betterChild);
      node = betterChild;
      betterChild = getBetterChild(node);
    }
  }

  public int length() {
    return len;
  }

  public boolean isEmpty() {
    return len == 0;
  }

  public E pop() {

  }


}
