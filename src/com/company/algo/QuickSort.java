package com.company.algo;

import java.util.Comparator;

public class QuickSort {

  public static <E> void swap(E[] arr, int i, int j) {
    E temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static <E> int partition(E[] arr, Comparator<? super E> comp, int lo, int hi) {
    E pivot = arr[hi];
    int i = lo;

    for (int j = lo; j < hi; j++) {
      if (comp.compare(arr[j], pivot) < 0) {
        swap(arr, i, j);
        i += 1;
      }
    }

    swap(arr, i, hi);

    return i;
  }

  public static <E extends Comparable<E>> int partition(E[] arr, int lo, int hi) {
    return partition(arr, (x, y) -> x.compareTo(y), lo, hi);
  }

  public static <E> void sort(E[] arr, Comparator<? super E> comparator, int i, int j) {
    if (i < j) {
      int p = partition(arr, comparator, i, j);
      sort(arr, comparator, i, p - 1);
      sort(arr, comparator, p + 1, j);
    }
  }

  public static <E> void sort(E[] arr, Comparator<? super E> comparator) {
    sort(arr, comparator, 0, arr.length - 1);
  }

  public static <E extends Comparable<E>> void sort(E[] arr, int i, int j) {
    if (i < j) {
      int p = partition(arr, i, j);
      sort(arr, i, p - 1);
      sort(arr, p + 1, j);
    }
  }

  public static <E extends Comparable<E>> void sort(E[] arr) {
    sort(arr, 0, arr.length - 1);
  }

}
