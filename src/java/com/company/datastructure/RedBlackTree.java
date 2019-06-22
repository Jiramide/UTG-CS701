package com.company.datastructure;

enum Colour {
  BLACK,
  RED;

  public Colour toggle() {
    return this == Colour.RED
      ? Colour.BLACK
      : Colour.RED;
  }
}

class RedBlackTreeNode<E> {

  E val;
  Colour col;
  RedBlackTreeNode<E> parent;
  RedBlackTreeNode<E> left;
  RedBlackTreeNode<E> right;

  RedBlackTreeNode(E val) {
    this.val = val;
    this.col = Colour.BLACK;
  }

}

public class RedBlackTree<E> extends BinaryTree<E> {

  RedBlackTreeNode<E> root;

}
