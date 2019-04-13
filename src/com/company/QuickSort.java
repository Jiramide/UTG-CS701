package com.company;

import java.util.Comparator;
import java.lang.Comparable;

public class QuickSort {

  public static <E> void swap(E[] arr, int i, int j) {
    E temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static <E> int partition(E[] arr, Comparator<E> comp, int lo, int hi) {
    E pivot = arr[hi];
    int i = lo;

    for (int j = lo; j < hi; j++) {
      if (comp.compare(arr[j], pivot) < 0) {
        swap(arr, i, j);
        i += 1;
      }
    }

    return i;
  }

  public static <E> void sort(E[] arr, Comparator<E> comparator, int i, int j) {
    if (i < j) {
      int p = partition(arr, comparator, i, j);
      sort(arr, comparator, i, p - 1);
      sort(arr, comparator, p + 1, j);
    }
  }

  public static <E> void sort(E[] arr, Comparator<E> comparator) {
    sort(arr, comparator, 0, arr.length - 1);
  }

  public static <E extends Comparable<E>> void sort(E[] arr) {

  }

}
