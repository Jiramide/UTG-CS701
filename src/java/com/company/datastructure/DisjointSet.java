package com.company.datastructure;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DisjointSet<E> {

  static class Node<E> {

    private DisjointSet.Node<E> parent;
    private E val;

    Node(E val) {
      this.val = val;
      this.parent = this;
    }

    E getVal() {
      return val;
    }

    void setParent(DisjointSet.Node<E> parent) {
      this.parent = parent;
    }

    DisjointSet.Node<E> getParent() {
      return parent;
    }

    DisjointSet.Node<E> getRoot() {
      while (parent != parent.parent) {
        parent = parent.parent;
      }

      return parent;
    }

    void flatten() {
      if (isRoot()) {
        return;
      }

      parent.flatten();
      parent = parent.parent;
    }

    boolean isRoot() {
      return parent == this;
    }

  }

  private HashMap<E, DisjointSet.Node<E>> nodes;

  public DisjointSet() {
    this.nodes = new HashMap<>();
  }

  public DisjointSet(int initCapacity) {
    this.nodes = new HashMap<>(initCapacity);
  }

  public DisjointSet(int initCapacity, int loadFactor) {
    this.nodes = new HashMap<>(initCapacity, loadFactor);
  }

  public void add(E elem) {
    nodes.put(elem, new DisjointSet.Node<>(elem));
  }

  public boolean inTree(E elem) {
    return nodes.containsKey(elem);
  }

  public void union(E elem0, E elem1) {
    if (inTree(elem0) && inTree(elem1)) {
      nodes.get(elem0).setParent(
        nodes.get(elem1).getParent()
      );
    }
  }

  public boolean inSameSet(E elem0, E elem1) {
    return inTree(elem0) && inTree(elem1)
      && nodes.get(elem0).getRoot() == nodes.get(elem1).getRoot();
  }

  public int numSets() {
    int count = 0;

    for (DisjointSet.Node<E> node : nodes.values()) {
      if (node.isRoot()) {
        count += 1;
      }
    }

    return count;
  }

  public Collection<Set<E>> sets() {
    HashMap<E, Set<E>> setsMap = new HashMap<>(nodes.size());

    for (DisjointSet.Node<E> node : nodes.values()) {
      E root = node.getRoot().getVal();

      if (!setsMap.containsKey(root)) {
        setsMap.put(root, new HashSet<>());
      }

      setsMap.get(root).add(node.getVal());
    }

    return setsMap.values();
  }

}
