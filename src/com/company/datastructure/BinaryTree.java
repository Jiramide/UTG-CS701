package com.company.datastructure;

import java.util.function.Consumer;

class BinaryTreeNode<E> {

  private E val;
  protected BinaryTreeNode<E> left;
  protected BinaryTreeNode<E> right;

  BinaryTreeNode(E val) {
    this.val = val;
  }

  public E getVal() {
    return val;
  }

  public BinaryTreeNode<E> getLeft() {
    return left;
  }

  public Option<BinaryTreeNode<E>> safeGetLeft() {
    return left != null
      ? new Option<>(left)
      : new Option<>();
  }

  public BinaryTreeNode<E> getRight() {
    return right;
  }

  public Option<BinaryTreeNode<E>> safeGetRight() {
    return right != null
      ? new Option<>(right)
      : new Option<>();
  }

  public boolean isLeaf() {
    return left == null && right == null;
  }

  BinaryTreeNode<E> leftmost() {
    BinaryTreeNode<E> curr = this;

    while (curr.left != null) {
      curr = curr.left;
    }

    return curr;
  }

  @Override
  public String toString() {
    return "Node(" + val.toString() + ")";
  }

}

public class BinaryTree<E extends Comparable<E>> {

  BinaryTreeNode<E> root;

  // preorder: NLR
  // in-order: LNR
  // out-order: RNL
  // postorder: LRN

  public void preOrder(BinaryTreeNode<E> curr, Consumer<E> process) {
    if (curr == null) {
      return;
    }

    process.accept(curr.getVal());
    preOrder(curr.left, process);
    preOrder(curr.right, process);
  }

  public void preOrder(Consumer<E> process) {
    preOrder(root, process);
  }

  public void inOrder(BinaryTreeNode<E> curr, Consumer<E> process) {
    if (curr == null) {
      return;
    }

    inOrder(curr.left, process);
    process.accept(curr.getVal());
    inOrder(curr.right, process);
  }

  public void inOrder(Consumer<E> process) {
    inOrder(root, process);
  }

  public void outOrder(BinaryTreeNode<E> curr, Consumer<E> process) {
    if (curr == null) {
      return;
    }

    outOrder(curr.right, process);
    process.accept(curr.getVal());
    outOrder(curr.left, process);
  }

  public void outOrder(Consumer<E> process) {
    outOrder(root, process);
  }

  public void postOrder(BinaryTreeNode<E> curr, Consumer<E> process) {
    if (curr == null) {
      return;
    }

    postOrder(curr.left, process);
    postOrder(curr.right, process);
    process.accept(curr.getVal());
  }

  public void postOrder(Consumer<E> process) {
    postOrder(root, process);
  }

  public void breadthFirst(Consumer<E> process) {
    Queue<BinaryTreeNode<E>> queue = new Queue<>();
    queue.enqueue(root);

    while (!queue.isEmpty()) {
      BinaryTreeNode<E> curr = queue.dequeue();
      process.accept(curr.getVal());

      if (curr.left != null) {
        queue.enqueue(curr.left);
      }

      if (curr.right != null) {
        queue.enqueue(curr.right);
      }
    }
  }

  public void insert(E val) {
    if (root == null) {
      root = new BinaryTreeNode<>(val);
      return;
    }

    BinaryTreeNode<E> prev = null;
    BinaryTreeNode<E> curr = root;

    while (curr != null) {
      prev = curr;
      curr = val.compareTo(curr.getVal()) < 0
        ? curr.left
        : curr.right;
    }

    if (prev != null) {
      if (val.compareTo(prev.getVal()) < 0) {
        prev.left = new BinaryTreeNode<>(val);
      } else {
        prev.right = new BinaryTreeNode<>(val);
      }
    } else {
      root = new BinaryTreeNode<>(val);
    }
  }

  private BinaryTreeNode<E> find(E val) {
    BinaryTreeNode<E> curr = root;

    while (curr != null) {
      int compRes = val.compareTo(curr.getVal());

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