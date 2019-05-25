package com.company.datastructure;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class DisjointSetNode<E> {

  private DisjointSetNode<E> parent;
  private E val;

  DisjointSetNode(E val) {
    this.val = val;
    this.parent = this;
  }

  E getVal() {
    return val;
  }

  void setParent(DisjointSetNode<E> parent) {
    this.parent = parent;
  }

  DisjointSetNode<E> getParent() {
    return parent;
  }

  DisjointSetNode<E> getRoot() {
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

public class DisjointSet<E> {

  private HashMap<E, DisjointSetNode<E>> nodes;

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
    nodes.put(elem, new DisjointSetNode<>(elem));
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

    for (DisjointSetNode<E> node : nodes.values()) {
      if (node.isRoot()) {
        count += 1;
      }
    }

    return count;
  }

  public Collection<Set<E>> sets() {
    HashMap<E, Set<E>> setsMap = new HashMap<>(nodes.size());

    for (DisjointSetNode<E> node : nodes.values()) {
      E root = node.getRoot().getVal();

      if (!setsMap.containsKey(root)) {
        setsMap.put(root, new HashSet<>());
      }

      setsMap.get(root).add(node.getVal());
    }

    return setsMap.values();
  }

}
