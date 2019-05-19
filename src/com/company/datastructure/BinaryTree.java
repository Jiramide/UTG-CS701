package com.company.datastructure;

import java.util.Comparator;
import java.util.function.Consumer;

class BinaryTreeNode<E> {

  E val;
  BinaryTreeNode<E> left;
  BinaryTreeNode<E> right;

  BinaryTreeNode(E val) {
    this.val = val;
  }

}

public class BinaryTree<E> {

  private BinaryTreeNode<E> root;
  private Comparator<? super E> comp;

  public BinaryTree(Comparator<? super E> comp) {
    this.comp = comp;
  }

  public BinaryTree(Iterable<? extends E> container, Comparator<? super E> comp) {
    this.comp = comp;

    for (E elem : container) {
      insert(elem);
    }
  }

  public static <E extends Comparable<E>> BinaryTree<E> withComparable() {
    return new BinaryTree<>((x, y) -> x.compareTo(y));
  }

  public static <E extends Comparable<E>> BinaryTree<E> withComparable(Iterable<? extends E> container) {
    return new BinaryTree<>(container, (x, y) -> x.compareTo(y));
  }

}
