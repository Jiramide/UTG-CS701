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
    Node<E> left;
    Node<E> right;

    Node(E val) {
      super(val);
      this.col = Colour.RED;
    }

    void setLeftChild(Node<E> child) {
      left = child;
      child.parent = this;
    }

    void setRightChild(Node<E> child) {
      right = child;
      child.parent = this;
    }

    static RedBlackTree.Colour getCol(Node<?> node) {
      return node != null
        ? node.col
        : RedBlackTree.Colour.BLACK;
    }

    Node<E> getGrandparent() {
      return parent != null
        ? parent.parent
        : null;
    }

    Node<E> getUncle() {
      if (parent == null || parent.parent == null) {
        return null;
      }

      Node<E> grandparent = getGrandparent();
      return parent == grandparent.left
        ? grandparent.right
        : grandparent.left;
    }

    RedBlackTree.Colour grandparentColour() {
      return Node.getCol(getGrandparent());
    }

    RedBlackTree.Colour uncleColour() {
      return Node.getCol(getUncle());
    }

    void leftRotate() {
      Node<E> oldRight = right;

      right = right.left;
      oldRight.parent = parent;
      parent = oldRight;
      oldRight.left = this;
    }

    void rightRotate() {
      Node<E> oldLeft = left;

      left = left.right;
      oldLeft.parent = parent;
      parent = oldLeft;
      oldLeft.right = this;
    }

  }

  protected RedBlackTree.Node<E> root;

  public RedBlackTree(Comparator<? super E> comp) {
    super(comp);
  }

  public RedBlackTree(Iterator<? extends E> iter, Comparator<? super E> comp) {
    super(iter, comp);
  }

  public RedBlackTree(Iterable<? extends E> container, Comparator<? super E> comp) {
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

    RedBlackTree.Node<E> newNode = new RedBlackTree.Node<>(val);

    if (prev == null) {
      root = newNode;
    } else if (comp.compare(val, prev.val) < 0) {
      prev.setLeftChild(newNode);
    } else {
      prev.setRightChild(newNode);
    }
  }

  private boolean continueRepair(RedBlackTree.Node<E> node) {
    return Node.getCol(node.parent) != RedBlackTree.Colour.BLACK
      && node != root;
  }

  private void repair(RedBlackTree.Node<E> node) {
    Node<E> uncle = node.getUncle();
    while (Node.getCol(uncle) == RedBlackTree.Colour.RED
      && continueRepair(node)) {
      uncle.col = uncle.col.toggle();
      node.parent.col = node.parent.col.toggle();

      Node<E> grandparent = node.getGrandparent();
      grandparent.col = RedBlackTree.Colour.RED;

      node = grandparent;
    }

    // uncle.col == BLACK => uncle.col == BLACK || uncle == null
    // rotate
    if (continueRepair(node)) {
      Node<E> parent = node.parent;
      Node<E> grandparent = node.getGrandparent();

      if (parent == grandparent.left) {
        if (node == parent.right) {
          // left right
          parent.leftRotate();
        }

        grandparent.rightRotate();
      } else {
        if (node == parent.left) {
          // right left
          parent.rightRotate();
        }

        grandparent.leftRotate();
      }

      RedBlackTree.Colour gCol = grandparent.col;
      grandparent.col = parent.col;
      parent.col = gCol;
    }

    // finished repairing, set root to black
    root.col = RedBlackTree.Colour.BLACK;
  }

}
