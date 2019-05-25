package com.company.algo;

import java.util.Comparator;
import com.company.datastructure.HeapArr;

public class HeapSort {

  public static <E> void sort(E[] arr, Comparator<? super E> comparator) {
    HeapArr<E> heap = new HeapArr<>(arr.clone(), comparator);

    for (int idx = 0; idx < arr.length; idx++) {
      arr[idx] =  heap.pop();
    }
  }

  public static <E extends Comparable<E>> void sort(E[] arr) {
    sort(arr, Comparable::compareTo);
  }

}
