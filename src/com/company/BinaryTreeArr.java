package com.company;

import java.util.Comparator;
import java.util.function.Consumer;

public class BinaryTreeArr<E> extends ArrStruct<E> {

  private Comparator<? extends E> comp;

  public BinaryTreeArr(E[] container, Comparator<? extends E> comp) {
    super(container);
    this.comp = comp;
  }

  public BinaryTreeArr(Class<E> cls, int capacity, Comparator<? extends E> comp) {
    super(cls, capacity);
    this.comp = comp;
  }

  public static <E extends Comparable<E>> BinaryTreeArr<E> withComparable(E[] container) {
    return new BinaryTreeArr<>(container, (x, y) -> x.compareTo(y));
  }

  public static <E extends Comparable<E>> BinaryTreeArr<E> withComparable(Class<E> cls, int capacity) {
    return new BinaryTreeArr<>(cls, capacity, (x, y) -> x.compareTo(y));
  }

  private int getLeftChildIdx(int parent) {
    return 2*parent + 1;
  }

  private int getRightChildIdx(int parent) {
    return 2*parent + 2;
  }

  private int getParentIdx(int child) {
    return (int)Math.floor((child - 1)/2);
  }

  // preorder: NLR
  // in-order: LNR
  // out-order: RNL
  // postorder: LRN

  public void preOrder(int curr, Consumer<E> process) {
    process.accept(container[curr]);
    preOrder(getLeftChildIdx(curr), process);
    preOrder(getRightChildIdx(curr), process);
  }

  public void preOrder(Consumer<E> process) {
    preOrder(0, process);
  }

  public void inOrder(int curr, Consumer<E> process) {
    inOrder(getLeftChildIdx(curr), process);
    process.accept(container[curr]);
    inOrder(getRightChildIdx(curr), process);
  }

  public void inOrder(Consumer<E> process) {
    inOrder(0, process);
  }

  public void outOrder(int curr, Consumer<E> process) {
    outOrder(getRightChildIdx(curr), process);
    process.accept(container[curr]);
    outOrder(getLeftChildIdx(curr), process);
  }

  public void outOrder(Consumer<E> process) {
    outOrder(0, process);
  }

  public void postOrder(int curr, Consumer<E> process) {
    postOrder(getLeftChildIdx(curr), process);
    postOrder(getRightChildIdx(curr), process);
    process.accept(container[curr]);
  }

  public void postOrder(Consumer<E> process) {
    postOrder(0, process);
  }

}