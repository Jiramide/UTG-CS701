package com.company.algo;

import java.util.Comparator;

public class BubbleSort {

  public static <E> void sort(E[] arr, Comparator<? super E> comparator) {
    for (int lastIdx = arr.length - 1; lastIdx != 0; lastIdx--) {
      for (int idx = 0; idx < lastIdx; idx++) {
        E fst = arr[idx];
        E snd = arr[idx + 1];

        // compare(fst, snd) > 0 => fst > snd
        // compare(fst, snd) < 0 => fst < snd
        // compare(fst, snd) == 0 => fst == snd
        if (comparator.compare(fst, snd) > 0) {
          arr[idx + 1] = fst;
          arr[idx] = snd;
        }
      }
    }
  }

  public static <E extends Comparable<E>> void sort (E[] arr) {
    sort(arr, Comparable::compareTo);
  }

}
