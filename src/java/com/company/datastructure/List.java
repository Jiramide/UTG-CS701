package com.company.datastructure;

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
    return idx >= len;
  }

  public E get(int idx) throws IndexOutOfBoundsException {
    if (!validIndex(idx)) {
      throw new IndexOutOfBoundsException("Cannot index element #" + idx + " in List.");
    }

    return container[idx];
  }

  public int length() {
    return len;
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

}
