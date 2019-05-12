package com.company;

import java.util.Comparator;

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

}
