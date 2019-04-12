package com.company;

import java.util.Comparator;
import java.lang.Comparable;

public class QuickSort {

  public static <E> void swap(E[] arr, int i, int j) {
    E temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static <E> int partition(E[] arr, Comparator<E> comp, int i, int j) {
    int pivot = i;
    i += 1;

    while (i < j) {
      boolean did = false;

      if (comp.compare(arr[i], arr[pivot]) < 0) {
        i += 1;
        did = true;
      }

      if (comp.compare(arr[pivot], arr[j]) > 0) {
        j -= 1;
        did = true;
      }

      if (!did) {
        swap(arr, i, j);
      }
    }
    swap(arr, pivot, j);

    return j;
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
