package com.company;

import java.util.Comparator;
import java.lang.Comparable;

public class BubbleSort {

  public static <E> void sort(E[] arr, Comparator<E> comparator) {
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
    for (int lastIdx = arr.length - 1; lastIdx != 0; lastIdx--) {
      for (int idx = 0; idx < lastIdx; idx++) {
        E fst = arr[idx];

        if (fst.compareTo(arr[idx + 1]) > 0) {
          arr[idx] = arr[idx + 1];
          arr[idx + 1] = fst;
        }
      }
    }
  }

}
