package com.company;

import java.util.Comparator;

public class SelectionSort {

  public static <E> void sort(E[] arr, Comparator<E> comparator) {
    for (int idx = 0; idx < arr.length; idx++) {
      E min = arr[idx];
      int minIdx = idx;

      for (int curMinIdx = idx + 1; curMinIdx < arr.length; curMinIdx++) {
        if (comparator.compare(min, arr[curMinIdx]) > 0) {
          min = arr[curMinIdx];
          minIdx = curMinIdx;
        }
      }

      arr[minIdx] = arr[idx];
      arr[idx] = min;

    }
  }

  public static <E extends Comparable<E>> void sort(E[] arr) {
    for (int idx = 0; idx < arr.length; idx++) {
      E min = arr[idx];
      int minIdx = idx;

      for (int curMinIdx = idx + 1; curMinIdx < arr.length; curMinIdx++) {
        if (min.compareTo(arr[curMinIdx]) > 0) {
          min = arr[curMinIdx];
          minIdx = curMinIdx;
        }
      }

      arr[minIdx] = arr[idx];
      arr[idx] = min;

    }
  }

}
