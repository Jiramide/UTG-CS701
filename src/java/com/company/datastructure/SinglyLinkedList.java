package com.company.datastructure;

import java.util.Iterator;

/**
 * <p>
 * A SinglyLinkedList is a collection of nodes, each which have two fields: a
 * value and a pointer to the next node. SinglyLinkedLists are dynamically sized
 * collections which have pros and cons over arrays or ArrayList.
 *
 * SinglyLinkedList has O(1) insertion in any index of the collection and O(1) removal in any index.
 * It also does not have to resize as the SinglyLinkedList is only limited
 * by your device's memory.
 *
 * SinglyLinkedList, however, doesn't give the ability to randomly access memory as
 * a SinglyLinkedList is not sequential in memory. Indexing a specific value in the collection
 * takes O(n) time complexity. A SinglyLinkedList also takes more memory than an array and extra overhead
 * when it comes to nodes.
 *
 * A SinglyLinkedList should be used if you don't plan to index the collection much, only
 * planning to iterate over the collection.
 * </p>
 *
 * @author Jade Piramide <jadepiramidepogi@gmail.com>
 * @param <E> The type of the elements contained within the SinglyLinkedList.
 */
public class SinglyLinkedList<E> implements Iterable<E> {

  static class Node<E> {

    Node<E> next;
    E val;

    Node(E val) {
      this.val = val;
    }

  }

  static class SinglyLinkedIterator<E> implements Iterator<E> {

    private SinglyLinkedList.Node<E> curr;

    SinglyLinkedIterator(SinglyLinkedList<E> collection) {
      this.curr = collection.indexNode(0);
    }

    @Override
    public boolean hasNext() {
      return curr != null;
    }

    @Override
    public E next() {
      SinglyLinkedList.Node<E> oldCurr = curr;
      curr = curr.next;

      return oldCurr.val;
    }

  }

  private SinglyLinkedList.Node<E> head;
  private int len;

  /**
   * Creates an empty SinglyLinkedList.
   */
  public SinglyLinkedList() {
    this.len = 0;
  }

  /**
   * A predicate function that checks whether the index will throw when passed to index.
   * @param idx The index to validate.
   * @return A boolean which dictates whether the index passed is valid.
   */
  public boolean validIndex(int idx) {
    return idx >= 0 && idx < len;
  }

  /**
   * Gets the node located at some specific index. This should never be used by the end user.
   * @param idx The position to find the node.
   * @return The node located at the index specified.
   * @throws IndexOutOfBoundsException If idx is not a valid index.
   * @see SinglyLinkedList.safeIndexNode
   */
  protected SinglyLinkedList.Node<E> indexNode(int idx) throws IndexOutOfBoundsException {
    if (!validIndex(idx)) {
      throw new IndexOutOfBoundsException("Cannot index element #" + idx + " in SinglyLinkedList.");
    }

    SinglyLinkedList.Node<E> curr = head;

    for (; idx > 0; idx--) {
      curr = curr.next;
    }

    return curr;
  }

  /**
   * Gets the node located at some specific index. This should never be used by the end user.
   * @param idx The position to find the node.
   * @return The node located at the index specified.
   * @see SinglyLinkedList.indexNode
   */
  protected Option<SinglyLinkedList.Node<E>> safeIndexNode(int idx) {
    return validIndex(idx)
      ? new Option<>(indexNode(idx)) // Safe to index.
      : new Option<>(); // Unsafe to index, return empty Option.
  }

  /**
   * Gets the value located at some specific index.
   * @param idx The position to find the value.
   * @return The value located at the index specified.
   * @throws IndexOutOfBoundsException If idx is not a valid index.
   * @see SinglyLinkedList.safeIndex
   */
  public E index(int idx) throws IndexOutOfBoundsException {
    return indexNode(idx).val;
  }

  /**
   * Gets the value located at some specific index. Unlike index, this will
   * never throw but instead return an Option.
   * @param idx The position to find the value.
   * @return An Option which contains the value located at the index, if it exists.
   * @see SinglyLinkedList.index
   */
  public Option<E> safeIndex(int idx) {
    return validIndex(idx)
      ? new Option<>(index(idx)) // Safe to index.
      : new Option<>(); // Unsafe to index, return empty Option.
  }

  /**
   * @return The length of the collection.
   */
  public int length() {
    return len;
  }

  /**
   * Take a node and an index. Places the node before the node located at the
   * index specified.
   * @param node The node to insert into the collection.
   * @param idx The index to node located after the newly placed node.
   * @throws IndexOutOfBoundsException If idx is not a valid index.
   * @see SinglyLinkedList.addNodeAfter
   */
  private void addNodeBefore(SinglyLinkedList.Node<E> node, int idx) throws IndexOutOfBoundsException {
    if (idx == 0) {
      node.next = head;
      head = node;

      len += 1;
      return;
    }

    addNodeAfter(node, idx - 1);
  }

  /**
   * Takes a node and an index. Places the node after the node located at the
   * index specified.
   * @param node The node to insert into the collection.
   * @param idx The index of the node located before the newly placed node.
   * @throws IndexOutOfBoundsException If idx is not a valid index.
   * @see SinglyLinkedList.addNodeBefore
   */
  private void addNodeAfter(SinglyLinkedList.Node<E> node, int idx) throws IndexOutOfBoundsException {
    SinglyLinkedList.Node<E> before = indexNode(idx);

    node.next = before.next;
    before.next = node;

    len += 1;
  }

  /**
   * Takes a value to insert before the index specified.
   * @param val The value to insert.
   * @param idx The index located after the newly placed value.
   * @throws IndexOutOfBoundsException If idx is not a valid index.
   * @see SinglyLinkedList.addAfter
   */
  public void addBefore(E val, int idx) throws IndexOutOfBoundsException {
    addNodeBefore(new SinglyLinkedList.Node<>(val), idx);
  }

  /**
   * Takes a value to insert after the index specified.
   * @param val The value to insert.
   * @param idx The index located before the newly placed value.
   * @throws IndexOutOfBoundsException If idx is not a valid index.
   * @see SinglyLinkedList.addBefore
   */
  public void addAfter(E val, int idx) throws IndexOutOfBoundsException {
    addNodeAfter(new SinglyLinkedList.Node<>(val), idx);
  }

  /**
   * Inserts the value specified at the very front of the list.
   * @param val The value to insert into the front.
   * @see SinglyLinkedList.addBefore
   * @see SinglyLinkedList.addAfter
   * @see SinglyLinkedList.uncons
   */
  public void cons(E val) {
    addBefore(val, 0);
  }

  /**
   * Removes the value located at the index specified and returns
   * it.
   * @param idx The index of the value to remove.
   * @return The removed value.
   * @throws IndexOutOfBoundsException If idx is not a valid index.
   * @see SinglyLinkedList.safeRemove
   */
  public E remove(int idx) throws IndexOutOfBoundsException {
    if (!validIndex(idx)) {
      throw new IndexOutOfBoundsException("Cannot remove element #" + idx + " from SinglyLinkedList.");
    }

    if (idx == 0) {
      SinglyLinkedList.Node<E> oldHead = head;
      head = head.next;
      oldHead.next = null;

      len -= 1;
      return oldHead.val;
    }

    SinglyLinkedList.Node<E> before = indexNode(idx - 1);
    SinglyLinkedList.Node<E> toRemove = before.next;

    before.next = toRemove.next;
    toRemove.next = null;

    len -= 1;
    return toRemove.val;
  }

  /**
   * Removes the value located at the index specified and returns
   * an Option containing it if it succeeded. Otherwise, the Option will
   * be empty.
   * @param idx The index of the value to remove.
   * @return The removed value, wrapped in an Option if it exists.
   * @see SinglyLinkedList.remove
   */
  public Option<E> safeRemove(int idx) {
    return validIndex(idx)
      ? new Option<>(remove(idx)) // Safe to remove.
      : new Option<>(); // Unsafe to remove, return empty Option.
  }

  /**
   * Removes the very front most element in the collection
   * and returns it.
   * @return The very first value of the collection.
   * @throws IndexOutBoundsException If the collection is empty.
   * @see SinglyLinkedList.safeUncons
   */
  public E uncons() throws IndexOutOfBoundsException {
    return remove(0);
  }

  /**
   * Removes the very front most element in the collection
   * and returns an Option containing it if it exists.
   * @return The very first value of the collection, wrapped in an Option.
   * @see SinglyLinkedList.uncons
   */
  public Option<E> safeUncons() {
    return safeRemove(0);
  }

  /**
   * Returns the very front most element in the collection
   * without removing it.
   * @return The very first value of the collection.
   * @throws IndexOutOfBoundsException If the collection is empty.
   * @see SinglyLinkedList.safePeek
   */
  public E peek() throws IndexOutOfBoundsException {
    return index(0);
  }

  /**
   * Returns the very front most element (in an Option) in the collection
   * without removing it, if it exists.
   * @return The very first value of the collection, wrapped in an Option.
   * @see SinglyLinkedList.peek
   */
  public Option<E> safePeek() {
    return safeIndex(0);
  }

  @Override
  public Iterator<E> iterator() {
    return new SinglyLinkedList.SinglyLinkedIterator<E>(this);
  }

  @Override
  public SinglyLinkedList<E> clone() {
    if (len == 0) {
      return new SinglyLinkedList<>();
    }

    SinglyLinkedList<E> listClone = new SinglyLinkedList<>();
    SinglyLinkedList.Node<E> listLast = new SinglyLinkedList.Node<>(head.val);

    listClone.head = listLast;

    for (SinglyLinkedList.Node<E> curr = head.next; curr != null; curr = curr.next) {
      listLast.next = new SinglyLinkedList.Node<>(curr.val);
      listLast = listLast.next;
    }

    listClone.len = len;
    return listClone;
  }

  /**
   * Returns the index of a specific element in the collection. If the element
   * is not found, it will return -1, otherwise, the returned value will be in
   * the range of [0, len). This method uses the equals method as opposed to the
   * naive equality comparison operator.
   * @param elem The element to find.
   * @return The index of the element in the collection.
   */
  public int indexOf(E elem) {
    int currIdx = 0;

    for (SinglyLinkedList.Node<E> curr = head; curr != null; curr = curr.next) {
      if (curr.val.equals(elem)) {
        return currIdx;
      }

      currIdx += 1;
    }

    return -1;
  }

  /**
   * Checks if an element is contained in the container. This method uses the equals method.
   * @param elem The element to check for.
   * @return A boolean dictating whether the elem is in the container or not.
   * @see SinglyLinkedList.indexOf
   */
  public boolean contains(E elem) {
    return indexOf(elem) != -1;
  }

  /**
   * A predicate function that checks for if the collection is empty.
   * @return A boolean dictating whether the collection is empty.
   */
  public boolean isEmpty() {
    return len == 0;
  }

  @Override
  public String toString() {
    StringBuilder readable = new StringBuilder("SinglyLinkedList { ");

    for (SinglyLinkedList.Node<E> curr = head; curr != null; curr = curr.next) {
      readable.append(curr.val);
      readable.append("; ");
    }

    readable.append("}");
    return readable.toString();
  }

}
