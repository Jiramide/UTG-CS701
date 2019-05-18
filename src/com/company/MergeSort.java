package com.company;

import java.util.ArrayList;
import java.util.Comparator;

public class MergeSort {

  public static <E> void merge(E[] arr, Comparator<E> comp, int i, int j, int k) {
    ArrayList<E> left = new ArrayList<E>(j - i + 1);
    ArrayList<E> right = new ArrayList<E>(k - j + 1);

    for (int idx = i; idx < j; idx++) {
      left.add(arr[idx]);
    }

    for (int idx = j; idx < k; idx++) {
      right.add(arr[idx]);
    }

    int idx = i;
    while (!left.isEmpty() && !right.isEmpty()) {
      int compRes = comp.compare(left.get(0), right.get(0));

      if (compRes < 0) {
        arr[idx] = left.remove(0);
      } else {
        arr[idx] = right.remove(0);
      }

      idx += 1;
    }

    while (!left.isEmpty()) {
      arr[idx++] = left.remove(0);
    }

    while (!right.isEmpty()) {
      arr[idx++] = right.remove(0);
    }

  }

}
