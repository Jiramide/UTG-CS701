package com.company.datastructure;

import java.util.Comparator;

public class Heap<E> {

  static class Node<E> {

    E val;
    Node<E> parent;
    Node<E> left;
    Node<E> right;

    Node(E val) {
      this.val = val;
      this.parent = null;
      this.left = null;
      this.right = null;
    }

    static <E> void setAsLeft(Node<E> parent, Node<E> left) {
      parent.left = left;
      left.parent = parent;
    }

    static <E> void setAsRight(Node<E> parent, Node<E> right) {
      parent.right = right;
      right.parent = parent;
    }

    static <E> void swap(Node<E> node0, Node<E> node1) {
      E temp = node0.val;
      node0.val = node1.val;
      node1.val = temp;
    }

    static <E> void removeChild(Node<E> parent, Node<E> child) {
      if (parent.left == child) {
        parent.left = null;
      }

      if (parent.right == child) {
        parent.right = null;
      }
    }

  }

  private Comparator<? super E> comp;
  private int len;
  private Heap.Node<E> root;

  public Heap(E[] container) {
    QueueArr<E> values = new QueueArr<>(container);
    Heap.Node<E> root = new Heap.Node<>(values.dequeue());

    @SuppressWarnings("unchecked")
    QueueArr<Heap.Node<E>> nodes = new QueueArr<>((Class<Heap.Node<E>>)root.getClass(), container.length);
    nodes.enqueue(root);

    while (values.numItems() > 1) {
      Heap.Node<E> parent = nodes.dequeue();
      Heap.Node<E> left = new Heap.Node<>(values.dequeue());
      Heap.Node<E> right = new Heap.Node<>(values.dequeue());

      Heap.Node.setAsLeft(parent, left);
      Heap.Node.setAsRight(parent, right);

      nodes.enqueue(left);
      nodes.enqueue(right);
    }

    if (!values.isEmpty()) {
      Heap.Node.setAsLeft(
        nodes.dequeue(),
        new Heap.Node<>(values.dequeue())
      );
    }

    heapify(root);
  }

  private void heapify(Heap.Node<E> node) {
    if (node == null) {
      return;
    }

    heapify(node.left);
    heapify(node.right);
    siftDown(node);
  }

  private void siftUp(Heap.Node<E> node) {
    Heap.Node<E> parent = node.parent;

    while (parent != null && comp.compare(node.val, parent.val) > 0) {
      Heap.Node.swap(node, parent);
      node = parent;
      parent = node.parent;
    }
  }

  private Heap.Node<E> getBetterChild(Heap.Node<E> parent) {
    if (parent.right == null) {
      return parent.left;
    }

    return comp.compare(parent.left.val, parent.right.val) > 0
      ? parent.left
      : parent.right;
  }

  private void siftDown(Heap.Node<E> node) {
    Heap.Node<E> betterChild = getBetterChild(node);

    while (betterChild != null && comp.compare(betterChild.val, node.val) > 0) {
      Heap.Node.swap(node, betterChild);
      node = betterChild;
      betterChild = getBetterChild(node);
    }
  }

  public int numItems() {
    return len;
  }

  public boolean isEmpty() {
    return len == 0;
  }

  public E pop() {
    E toPop = root.val;

    Heap.Node<E> node = root;
    Heap.Node<E> betterChild = getBetterChild(node);

    while (betterChild != null) {
      Heap.Node.swap(node, betterChild);
      node = betterChild;
      betterChild = getBetterChild(node);
    }

    Heap.Node.removeChild(node.parent, node);
    return toPop;
  }


}
