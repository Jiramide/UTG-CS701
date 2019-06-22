package com.company.datastructure;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Consumer;

public class RedBlackTree<E> extends BinaryTree<E> {

  static enum Colour {
    BLACK,
    RED;

    public Colour toggle() {
      return this == Colour.RED
        ? Colour.BLACK
        : Colour.RED;
    }
  }

  static class Node<E> extends BinaryTree.Node<E> {

    RedBlackTree.Colour col;
    Node<E> parent;

    Node(E val) {
      super(val);
      this.col = Colour.RED;
    }

    void setLeftChild(RedBlackTree<E> child) {
      left = child;
      child.parent = this;
    }

    void setRightChild(RedBlackTree<E> child) {
      right = child;
      child.parent = this;
    }

  }

  RedBlackTree.Node<E> root;

  public RedBlackTree(Comparator<? super E> comp) {
    super(comp);
  }

  public RedBlackTree(Iterator<? extends E> iter, Comparator<? super E> comp) {
    super(iter, comp);
  }

  public BinaryTree(Iterable<? extends E> container, Comparator<? super E> comp) {
    this(container.iterator(), comp);
  }

  public static <E extends Comparable<E>> RedBlackTree<E> withComparable() {
    return new RedBlackTree<>(Comparable::compareTo);
  }

  public static <E extends Comparable<E>> RedBlackTree<E> withComparable(Iterator<? extends E> iter) {
    return new RedBlackTree<>(iter, Comparable::compareTo);
  }

  public static <E extends Comparable<E>> RedBlackTree<E> withComparable(Iterable<? extends E> container) {
    return RedBlackTree.withComparable(container.iterator());
  }

  @Override
  public void insert(E val) {
    len += 1;

    RedBlackTree.Node<E> curr = root;
    RedBlackTree.Node<E> prev = null;

    while (curr != null) {
      prev = curr;
      curr = comp.compare(val, curr.val) < 0
        ? curr.left
        : curr.right;
    }

    if (prev == null) {
      root = new RedBlackTree.Node<>(val);
    } else if (comp.compare(val, prev.val) < 0) {
      prev.left = new RedBlackTree.Node<>(val);
    } else {
      prev.right = new RedBlackTree.Node<>(val);
    }
  }

}
