package com.company;

import java.lang.Comparable;
import java.util.function.Function;

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

  // preorder: NLR
  // in-order: LNR
  // out-order: RNL
  // postorder: LRN

  public void preOrder(BinaryTreeNode<E> curr, Function<E, ?> process) {
    process(curr.val);
    preOrder(curr.left, process);
    preOrder(curr.right, process);
  }

  public void preOrder(Function<E, ?> process) {
    preOrder(root, process)
  }

  public void inOrder(BinaryTreeNode<E> curr, Function<E, ?> process) {
    inOrder(curr.left, process);
    process(curr.val);
    inOrder(curr.right, process);
  }

  public void inOrder(Function<E, ?> process) {
    inOrder(root, process)
  }

  public void outOrder(BinaryTreeNode<E> curr, Function<E, ?> process) {
    outOrder(curr.right, process);
    process(curr.val);
    outOrder(curr.left, process);
  }

  public void outOrder(Function<E, ?> process) {
    outOrder(root, process)
  }

  public void postOrder(BinaryTreeNode<E> curr, Function<E, ?> process) {
    postOrder(curr.left, process);
    postOrder(curr.right, process);
    process(curr.val);
  }

  public void postOrder(F init, Function<E, ?> process) {
    postOrder(root, process)
  }

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
