package com.company.datastructure;

import java.util.Comparator;
import java.util.function.Consumer;

public class BinaryTreeArr<E> extends ArrStruct<E> {

  private Comparator<? super E> comp;
  private int len;

  public BinaryTreeArr(E[] container, Comparator<? super E> comp) {
    super(container);
    this.len = container.length;
    this.comp = comp;
  }

  public BinaryTreeArr(Class<E> cls, int capacity, Comparator<? super E> comp) {
    super(cls, capacity);
    this.len = 0;
    this.comp = comp;
  }

  public static <E extends Comparable<E>> BinaryTreeArr<E> withComparable(E[] container) {
    return new BinaryTreeArr<>(container, Comparable::compareTo);
  }

  public static <E extends Comparable<E>> BinaryTreeArr<E> withComparable(Class<E> cls, int capacity) {
    return new BinaryTreeArr<>(cls, capacity, Comparable::compareTo);
  }

  public int numItems() {
    return len;
  }

  public boolean isEmpty() {
    return len == 0;
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

  private void preOrder(int curr, Consumer<E> process) {
    if (container[curr] == null) {
      return;
    }

    process.accept(container[curr]);
    preOrder(getLeftChildIdx(curr), process);
    preOrder(getRightChildIdx(curr), process);
  }

  public void preOrder(Consumer<E> process) {
    preOrder(0, process);
  }

  private void inOrder(int curr, Consumer<E> process) {
    if (container[curr] == null) {
      return;
    }

    inOrder(getLeftChildIdx(curr), process);
    process.accept(container[curr]);
    inOrder(getRightChildIdx(curr), process);
  }

  public void inOrder(Consumer<E> process) {
    inOrder(0, process);
  }

  private void outOrder(int curr, Consumer<E> process) {
    if (container[curr] == null) {
      return;
    }

    outOrder(getRightChildIdx(curr), process);
    process.accept(container[curr]);
    outOrder(getLeftChildIdx(curr), process);
  }

  public void outOrder(Consumer<E> process) {
    outOrder(0, process);
  }

  private void postOrder(int curr, Consumer<E> process) {
    if (container[curr] == null) {
      return;
    }

    postOrder(getLeftChildIdx(curr), process);
    postOrder(getRightChildIdx(curr), process);
    process.accept(container[curr]);
  }

  public void postOrder(Consumer<E> process) {
    postOrder(0, process);
  }

  public void breadthFirst(Consumer<E> process) {
    for (int idx = 0; idx < getCapacity(); idx++) {
      if (container[idx] != null) {
        process.accept(container[idx]);
      }
    }
  }

  public void insert(E val) {
    len += 1;

    int curr = 0;
    while (container[curr] != null) {
      curr = comp.compare(val, container[curr]) < 0
        ? getLeftChildIdx(curr)
        : getRightChildIdx(curr);
    }

    container[curr] = val;
  }

  public boolean member(E val) {
    int curr = 0;

    while (container[curr] != null) {
      int compRes = comp.compare(val, container[curr]);

      if (compRes == 0) {
        return true;
      }

      curr = compRes < 0
        ? getLeftChildIdx(curr)
        : getRightChildIdx(curr);
    }

    return false;
  }

}
