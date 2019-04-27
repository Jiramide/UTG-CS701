package com.company;

import java.lang.Comparable;

class BinaryTreeNode<E> {

  E val;
  BinaryTreeNode<E> left;
  BinaryTreeNode<E> right;

  BinaryTreeNode(E val) {
    this.val = val;
  }

  boolean isLeaf() {
    return left == null && right == null;
  }

  BinaryTreeNode<E> leftmost() {
    BinaryTreeNode<E> curr = this;

    while (curr.left != null) {
      curr = curr.left;
    }

    return curr;
  }

}

public class BinaryTree<E extends Comparable<E>> {

  BinaryTreeNode<E> root;

  public void insert(E val) {
    if (root == null) {
      root = new BinaryTreeNode<>(val);
      return;
    }

    BinaryTreeNode<E> prev;
    BinaryTreeNode<E> curr = root;

    while (curr != null) {
      prev = curr;
      curr = val.compareTo(curr.val) < 0
        ? curr.left
        : curr.right;
    }

    if (val.compareTo(curr.val) < 0) {
      prev.left = new BinaryTreeNode<>(val);
    } else {
      prev.right = new BinaryTreeNode<>(val);
    }
  }

  private BinaryTreeNode<E> find(E val) {
    BinaryTreeNode<E> curr = root;

    while (curr != null) {
      int compRes = val.compareTo(curr.val);

      if (compRes == 0) {
        return curr;
      }

      curr = compRes < 0
        ? curr.left
        : curr.right;
    }

    return null;
  }

  public boolean member(E val) {
    return find(val) != null;
  }

}
