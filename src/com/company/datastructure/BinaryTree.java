package com.company.datastructure;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Consumer;

class BinaryTreeNode<E> {

  E val;
  BinaryTreeNode<E> left;
  BinaryTreeNode<E> right;

  BinaryTreeNode(E val) {
    this.val = val;
  }

  int height() {
    int leftHeight = left != null
      ? left.height()
      : -1;

    int rightHeight = right != null
      ? right.height()
      : -1;

    return 1 + Math.max(leftHeight, rightHeight);
  }

}

public class BinaryTree<E> {

  private BinaryTreeNode<E> root;
  private Comparator<? super E> comp;
  private int len;

  public BinaryTree(Comparator<? super E> comp) {
    this.comp = comp;
  }

  public BinaryTree(Iterator<? extends E> iter, Comparator<? super E> comp) {
    this(comp);
    iter.forEachRemaining((val) -> insert(val));
  }

  public BinaryTree(Iterable<? extends E> container, Comparator<? super E> comp) {
    this(container.iterator(), comp);
  }

  public static <E extends Comparable<E>> BinaryTree<E> withComparable() {
    return new BinaryTree<>((x, y) -> x.compareTo(y));
  }

  public static <E extends Comparable<E>> BinaryTree<E> withComparable(Iterator<? extends E> iter) {
    return new BinaryTree<>(iter, (x, y) -> x.compareTo(y));
  }

  public static <E extends Comparable<E>> BinaryTree<E> withComparable(Iterable<? extends E> container) {
    return BinaryTree.withComparable(container.iterator());
  }

  public int numItems() {
    return len;
  }

  public boolean isEmpty() {
    return len == 0;
  }

  public int height() {
    return root != null
      ? root.height()
      : 0;
  }

  // preorder: NLR
  // in-order: LNR
  // out-order: RNL
  // postorder: LRN

  private void preOrder(BinaryTreeNode<E> curr, Consumer<E> process) {
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

  private void inOrder(BinaryTreeNode<E> curr, Consumer<E> process) {
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

  private void outOrder(BinaryTreeNode<E> curr, Consumer<E> process) {
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

  private void postOrder(BinaryTreeNode<E> curr, Consumer<E> process) {
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
    Queue<BinaryTreeNode<E>> queue = new Queue<>();
    queue.enqueue(root);

    while (!queue.isEmpty()) {
      BinaryTreeNode<E> curr = queue.dequeue();
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
    BinaryTreeNode<E> curr = root;
    BinaryTreeNode<E> prev = null;

    while (curr != null) {
      prev = curr;
      curr = comp.compare(val, curr.val) < 0
        ? curr.left
        : curr.right;
    }

    if (prev == null) {
      root = new BinaryTreeNode<>(val);
    } else if (comp.compare(val, prev.val) < 0) {
      prev.left = new BinaryTreeNode<>(val);
    } else {
      prev.right = new BinaryTreeNode<>(val);
    }
  }

  public boolean member(E val) {
    BinaryTreeNode<E> curr = root;

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
