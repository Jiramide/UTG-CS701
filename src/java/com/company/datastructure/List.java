package com.company.datastructure;

import java.util.function.Function;

public class List<E> extends ArrStruct<E> {

  public int len;
  public double loadFactor;

  public List(E[] container) {
    this(container, 0.75);
  }

  public List(Class<E> cls, int capacity) {
    this(cls, capacity, 0.75);
  }

  public List(E[] container, double loadFactor) {
    super(container);
    this.len = container.length;
    this.loadFactor = loadFactor;

    resizeWithLoadFactor();
  }

  public List(Class<E> cls, int capacity, double loadFactor) {
    super(cls, capacity);
    this.len = 0;
    this.loadFactor = loadFactor;
  }

  private boolean needResize() {
    return ((double)len)/getCapacity() >= loadFactor;
  }

  private void resizeWithLoadFactor() {
    resize((int)Math.floor(len/loadFactor));
  }

  public boolean validIndex(int idx) {
    return idx >= 0 && idx < len;
  }

  public int length() {
    return len;
  }

  public boolean isEmpty() {
    return len == 0;
  }

  public void setLoadFactor(double loadFactor) {
    this.loadFactor = loadFactor;
    resizeWithLoadFactor();
  }

  public E get(int idx) throws IndexOutOfBoundsException {
    if (!validIndex(idx)) {
      throw new IndexOutOfBoundsException("Cannot index element #" + idx + " in List.");
    }

    return container[idx];
  }

  public void set(int idx, E elem) throws IndexOutOfBoundsException {
    if (!validIndex(idx)) {
      throw new IndexOutOfBoundsException("Cannot index element #" + idx + " in List.");
    }

    container[idx] = elem;
  }

  public void insert(int idx, E elem) throws IndexOutOfBoundsException {
    if (!validIndex(idx)) {
      throw new IndexOutOfBoundsException("Cannot index element #" + idx + " in List.");
    }

    // [0] [1] [2] [3] [4] [5]
    // insert(2, A)
    // [0] [1] [A] [2] [3] [4] [5]
    len += 1;

    if (needResize()) {
      resizeWithLoadFactor();
    }

    // shift right
    for (int moveIdx = len - 1; moveIdx >= idx; moveIdx--) {
      container[moveIdx + 1] = container[moveIdx];
    }

    container[idx] = elem;
  }

  public void cons(E elem) {
    insert(0, elem);
  }

  public void consLast(E elem) {
    len += 1;

    if (needResize()) {
      resizeWithLoadFactor();
    }

    container[len - 1] = elem;
  }

  public E remove(int idx) throws IndexOutOfBoundsException {
    if (!validIndex(idx)) {
      throw new IndexOutOfBoundsException("Cannot index element #" + idx + " in List.");
    }

    // [0] [1] [A] [2] [3] [4] [5]
    // A = remove(2)
    // [0] [1] [2] [3] [4] [5]

    len -= 1;
    E toRemove = container[idx];

    // shift left
    for (int moveIdx = idx; idx < len; idx++) {
      container[idx] = container[idx + 1];
    }

    container[len] = null;
    return toRemove;
  }

  public E uncons() {
    return remove(0);
  }

  public E unconsLast() {
    return remove(len - 1);
  }

  public E peek() {
    return container[0];
  }

  public E peekLast() {
    return container[len - 1];
  }

  public void concat(List<E> toConcat) {
    for (int idx = 0; idx < toConcat.length(); idx++) {
      set(len, toConcat.get(idx));
    }
  }

  public List<E> immutableConcat(List<E> toConcat) {
    List<E> clone = clone();
    clone.concat(toConcat);

    return clone;
  }

  public <F> List<F> map(Function<E, F> f) {
    @SuppressWarnings("unchecked")
    // type unsafe
    List<F> mapped = new List<F>((F[])new Object[getCapacity()], loadFactor);

    for (int idx = 0; idx < len; idx++) {
      mapped.set(idx, f.apply(get(idx)));
    }

    return mapped;
  }

  public <F> List<F> bind(Function<E, List<F>> f) {
    if (isEmpty()) {
      // hack to create an empty List<F>
      return map(x -> null);
    }

    List<F> front = f.apply(get(0));

    for (int idx = 1; idx < len; idx++) {
      front.concat(f.apply(get(idx)));
    }

    return front;
  }

  @Override
  public List<E> clone() {
    return new List<>(container.clone(), loadFactor);
  }

}
