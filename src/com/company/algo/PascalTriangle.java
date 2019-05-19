package com.company.algo;

public class PascalTriangle {

  private static int index(int[] arr, int idx) {
    return idx >= 0 && idx < arr.length
      ? arr[idx]
      : 0;
  }

  public static int[] getRow(int row) {
    if (row == 0) {
      int[] base = {1};
      return base;
    }

    int[] rowArr = new int[row + 1];
    int[] prevRow = getRow(row - 1);

    for (int idx = -1; idx < prevRow.length; idx++) {
      rowArr[idx + 1] = index(prevRow, idx) + index(prevRow, idx + 1);
    }

    return rowArr;
  }

}
