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

    @Override
    protected Node<E> getLeft() {
      return (Node<E>)super.getLeft();
    }

    @Override
    protected Node<E> getRight() {
      return (Node<E>)super.getRight();
    }

    void setLeftChild(Node<E> child) {
      setLeft(child);

      if (child != null) {
        child.parent = this;
      }
    }

    void setRightChild(Node<E> child) {
      setRight(child);

      if (child != null) {
        child.parent = this;
      }
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
      return parent == grandparent.getLeft()
        ? grandparent.getRight()
        : grandparent.getLeft();
    }

    RedBlackTree.Colour grandparentColour() {
      return Node.getCol(getGrandparent());
    }

    RedBlackTree.Colour uncleColour() {
      return Node.getCol(getUncle());
    }

    void leftRotate() {
      Node<E> oldParent = parent;
      Node<E> oldRight = getRight();
      Node<E> leftGrandchild = oldRight.getLeft();

      oldRight.setLeftChild(this);
      setRightChild(leftGrandchild);

      if (oldParent != null) {
        if (oldParent.getLeft() == this) {
          oldParent.setLeftChild(oldRight);
        } else {
          oldParent.setRightChild(oldRight);
        }
      } else {
        oldRight.parent = null;
      }
    }

    void rightRotate() {
      Node<E> oldParent = parent;
      Node<E> oldLeft = getLeft();
      Node<E> rightGrandchild = oldLeft.getRight();

      oldLeft.setRightChild(this);
      setLeftChild(rightGrandchild);

      if (oldParent != null) {
        if (oldParent.getLeft() == this) {
          oldParent.setLeftChild(oldLeft);
        } else {
          oldParent.setRightChild(oldLeft);
        }
      } else {
        oldLeft.parent = null;
      }
    }

    @Override
    public String toString() {
      return col.toString() + "(" + val.toString() + ")";
    }

  }

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

    RedBlackTree.Node<E> curr = (RedBlackTree.Node<E>)getRoot();
    RedBlackTree.Node<E> prev = null;

    while (curr != null) {
      prev = curr;
      curr = comp.compare(val, curr.val) < 0
        ? curr.getLeft()
        : curr.getRight();
    }

    RedBlackTree.Node<E> newNode = new RedBlackTree.Node<>(val);

    if (prev == null) {
      setRoot(newNode);
      newNode.col = RedBlackTree.Colour.BLACK;
    } else if (comp.compare(val, prev.val) < 0) {
      prev.setLeftChild(newNode);
    } else {
      prev.setRightChild(newNode);
    }

    repair(newNode);
  }

  private boolean continueRepair(RedBlackTree.Node<E> node) {
    return Node.getCol(node.parent) != RedBlackTree.Colour.BLACK
      && node != getRoot();
  }

  private void repair(RedBlackTree.Node<E> node) {
    Node<E> uncle = node.getUncle();

    while (Node.getCol(uncle) == RedBlackTree.Colour.RED
      && continueRepair(node)) {
      uncle.col = RedBlackTree.Colour.BLACK;
      node.parent.col = RedBlackTree.Colour.BLACK;

      Node<E> grandparent = node.getGrandparent();
      grandparent.col = RedBlackTree.Colour.RED;

      node = grandparent;
      uncle = node.getUncle();
    }

    // uncle.col == BLACK => uncle.col == BLACK || uncle == null
    // rotate
    if (continueRepair(node)) {
      Node<E> parent = node.parent;
      Node<E> grandparent = node.getGrandparent();

      if (parent == grandparent.getLeft()) {
        if (node == parent.getRight()) {
          // left right
          parent.leftRotate();
          parent = node;
        }

        grandparent.rightRotate();
      } else {
        if (node == parent.getLeft()) {
          // right left
          parent.rightRotate();
          parent = node;
        }

        grandparent.leftRotate();
      }

      grandparent.col = RedBlackTree.Colour.RED;
      parent.col = RedBlackTree.Colour.BLACK;

      if (getRoot() == grandparent) {
        setRoot(parent);
      }
    }

    // finished repairing, set root to black
    ((RedBlackTree.Node<E>)getRoot()).col = RedBlackTree.Colour.BLACK;
  }

}
