package com.company;

import java.util.Scanner;
import java.util.Set;
import java.lang.Math;

public class Main {

  public static int fact(int n) {
    if (n == 0) {
      return 1;
    }

    return n*fact(n - 1);
  }

  public static int fib(int n) {
    if (n == 0 || n == 1) {
      return n;
    }

    return fib(n - 1) + fib(n - 2);
  }

  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);

    System.out.println("Factorial");
    System.out.println(fact(in.nextInt()));

    System.out.println("Fibonnaci");
    System.out.println(fib(in.nextInt()));

    System.out.println("Proper String");
    int iter = in.nextInt();

    for (; iter > 0; iter--) {
      System.out.println(
        ParenthesizedString.IsProper(in.next())
          ? "Yes"
          : "No"
      );
    }

    System.out.println("Longest Alimentary Chain");
    int creatures;
    int relations;

    while ((creatures = in.nextInt()) != 0 & (relations = in.nextInt()) != 0) {
      DisjointSet<String> creatureChain = new DisjointSet<>(creatures);

      for (; creatures > 0; creatures--) {
        creatureChain.add(in.next());
      }

      for (; relations > 0 ; relations--) {
        creatureChain.union(in.next(), in.next());
      }

      int biggestChain = 0;

      for (Set<String> chain : creatureChain.sets()) {
        biggestChain = Math.max(biggestChain, chain.size());
      }

      System.out.println(biggestChain);
    }

    System.out.println("Maximal Connected Subgraph");
    int cases = in.nextInt();

    for (; cases > 0; cases--) {
      DisjointSet<Character> graph = new DisjointSet<>();
      char max = in.next().charAt(0);

      for (char curr = 'A'; curr <= max; curr++) {
        graph.add(curr);
      }

      in.nextLine();

      String rel = in.nextLine();
      while (!rel.trim().isEmpty()) {
        graph.union(rel.charAt(0), rel.charAt(1));
        rel = in.nextLine();
      }

      System.out.println(graph.numSets());
    }

    Integer[] arr = {1, 8, 5, 3, 0, 7, 2, 6, 9, 4};
    System.out.println("Using Comparable(QuickSort)");
    System.out.print("Unsorted: ");
    for (Integer i : arr) {
      System.out.print(i);
      System.out.print(" ");
    }

    System.out.print("\nSorted:");

    QuickSort.sort(arr, (x, y) -> x - y);
    for (Integer i : arr) {
      System.out.print(i);
      System.out.print(" ");
    }

  }

}
