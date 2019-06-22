package com.company.datastructure;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Consumer;

public class BinaryTree<E> {

  static class Node<E> {

    E val;
    Node<E> left;
    Node<E> right;

    Node(E val) {
      this.val = val;
    }

  }

  protected BinaryTree.Node<E> root;
  protected Comparator<? super E> comp;
  protected int len;

  public BinaryTree(Comparator<? super E> comp) {
    this.root = null;
    this.comp = comp;
    this.len = 0;
  }

  public BinaryTree(Iterator<? extends E> iter, Comparator<? super E> comp) {
    this(comp);
    iter.forEachRemaining(this::insert);
  }

  public BinaryTree(Iterable<? extends E> container, Comparator<? super E> comp) {
    this(container.iterator(), comp);
  }

  public static <E extends Comparable<E>> BinaryTree<E> withComparable() {
    return new BinaryTree<>(Comparable::compareTo);
  }

  public static <E extends Comparable<E>> BinaryTree<E> withComparable(Iterator<? extends E> iter) {
    return new BinaryTree<>(iter, Comparable::compareTo);
  }

  public static <E extends Comparable<E>> BinaryTree<E> withComparable(Iterable<? extends E> container) {
    return BinaryTree.withComparable(container.iterator());
  }

  protected void setRoot(BinaryTree.Node<E> node) {
    root = node;
  }

  protected BinaryTree.Node<E> getRoot() {
    return root;
  }

  public int numItems() {
    return len;
  }

  public boolean isEmpty() {
    return len == 0;
  }

  private int height(BinaryTree.Node<E> node) {
    if (node == null) {
      return -1;
    }

    return 1 + Math.max(height(node.left), height(node.right));
  }

  public int height() {
    return height(root);
  }

  private int minHeight(BinaryTree.Node<E> node) {
    if (node == null) {
      return -1;
    }

    return 1 + Math.min(height(node.left), height(node.right));
  }

  public int minHeight() {
    return minHeight(root);
  }

  public boolean isBalanced() {
    return height() - minHeight() <= 1;
  }

  // preorder: NLR
  // in-order: LNR
  // out-order: RNL
  // postorder: LRN

  private void preOrder(BinaryTree.Node<E> curr, Consumer<E> process) {
    if (curr == null) {
      return;
    }

    process.accept(curr.val);
    preOrder(curr.left, process);
    preOrder(curr.right, process);
  }

  public void preOrder(Consumer<E> process) {
    preOrder(root, process);
  }

  private void inOrder(BinaryTree.Node<E> curr, Consumer<E> process) {
    if (curr == null) {
      return;
    }

    inOrder(curr.left, process);
    process.accept(curr.val);
    inOrder(curr.right, process);
  }

  public void inOrder(Consumer<E> process) {
    inOrder(root, process);
  }

  private void outOrder(BinaryTree.Node<E> curr, Consumer<E> process) {
    if (curr == null) {
      return;
    }

    outOrder(curr.right, process);
    process.accept(curr.val);
    outOrder(curr.left, process);
  }

  public void outOrder(Consumer<E> process) {
    outOrder(root, process);
  }

  private void postOrder(BinaryTree.Node<E> curr, Consumer<E> process) {
    if (curr == null) {
      return;
    }

    postOrder(curr.left, process);
    postOrder(curr.right, process);
    process.accept(curr.val);
  }

  public void postOrder(Consumer<E> process) {
    postOrder(root, process);
  }

  public void breadthFirst(Consumer<E> process) {
    Queue<BinaryTree.Node<E>> queue = new Queue<>();

    if (root != null) {
      queue.enqueue(root);
    }

    while (!queue.isEmpty()) {
      BinaryTree.Node<E> curr = queue.dequeue();
      process.accept(curr.val);

      if (curr.left != null) {
        queue.enqueue(curr.left);
      }

      if (curr.right != null) {
        queue.enqueue(curr.right);
      }
    }
  }

  public void insert(E val) {
    len += 1;

    BinaryTree.Node<E> curr = root;
    BinaryTree.Node<E> prev = null;

    while (curr != null) {
      prev = curr;
      curr = comp.compare(val, curr.val) < 0
        ? curr.left
        : curr.right;
    }

    if (prev == null) {
      root = new BinaryTree.Node<>(val);
    } else if (comp.compare(val, prev.val) < 0) {
      prev.left = new BinaryTree.Node<>(val);
    } else {
      prev.right = new BinaryTree.Node<>(val);
    }
  }

  public boolean member(E val) {
    BinaryTree.Node<E> curr = root;

    while (curr != null) {
      int compRes = comp.compare(val, curr.val);

      if (compRes == 0) {
        return true;
      }

      curr = compRes < 0
        ? curr.left
        : curr.right;
    }

    return false;
  }

}
