package com.company;

import java.util.Scanner;
import java.util.Set;
import com.company.datastructure.*;
import com.company.algo.*;

public class Main {

  public static <E> boolean isPalindrome(E[] arr, int i) {
    if (i > arr.length / 2) {
      return true;
    }

    return arr[i].equals(arr[arr.length - i - 1]) && isPalindrome(arr, i + 1);
  }

  public static <E> boolean isPalindrome(E[] arr) {
    return isPalindrome(arr, 0);
  }

  public static <E> void reverse(E[] arr, int i, int j) {
    if (i > j) {
      return;
    }

    E temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;

    reverse(arr, i + 1, j - 1);
  }

  public static <E> void reverse(E[] arr) {
    reverse(arr, 0, arr.length - 1);
  }

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

  public static <E> void printElem(E str) {
    System.out.print(str.toString());
    System.out.print(" ");
  }

  public static void main(String[] args) {

    /*

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

    QuickSort.sort(arr);
    for (Integer i : arr) {
      System.out.print(i);
      System.out.print(" ");
    }

    Integer[] arr2 = {1, 3, 5, 8, 10, 2, 4, 6, 7, 9};
    System.out.println("");
    MergeSort.merge(arr2, (x, y) -> x - y, 0, 5, 10);
    for (Integer i : arr2) {
      System.out.print(i);
      System.out.print(" ");
    }
    System.out.println("");

    reverse(arr);
    for (Integer i : arr) {
      System.out.print(i);
      System.out.print(" ");
    }

    Integer[] palindrome = {0, 1, 4, 6, 4, 1, 0};
    System.out.println("");
    System.out.println(isPalindrome(palindrome));

    int rows = in.nextInt();
    for (int idx = 0; idx < rows; idx++) {
      for (Integer i : PascalTriangle.getRow(idx)) {
        System.out.print(i);
        System.out.print(" ");
      }
      System.out.println("");
    }

    */

    Integer[] arr = {1, 8, 5, 3, 0, 7, 2, 6, 9, 4};
    System.out.println("Using Comparator(HeapSort)");
    System.out.print("Unsorted: ");

    for (Integer i : arr) {
      Main.printElem(i);
    }

    System.out.print("\nSorted: ");
    HeapSort.sort(arr, (x, y) -> y - x);
    for (Integer i : arr) {
      Main.printElem(i);
    }
    System.out.println("");

    BinaryTree<Integer> tree = BinaryTree.withComparable();
    tree.insert(5);
      tree.insert(2);
        tree.insert(1);
          tree.insert(0);
        tree.insert(3);

          tree.insert(4);
      tree.insert(7);
        tree.insert(6);
        tree.insert(9);
          tree.insert(8);
          tree.insert(10);

    System.out.println("Preorder");
    tree.preOrder(Main::printElem);
    System.out.println("");

    System.out.println("In-order");
    tree.inOrder(Main::printElem);
    System.out.println("");

    System.out.println("Out-order");
    tree.outOrder(Main::printElem);
    System.out.println("");

    System.out.println("Postorder");
    tree.postOrder(Main::printElem);
    System.out.println("");

    System.out.println("Breadth first");
    tree.breadthFirst(Main::printElem);
    System.out.println("");

    System.out.println("StackArr");
    Integer[] stackContainer = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    StackArr<Integer> stack = new StackArr<>(stackContainer);

    while (!stack.isEmpty()) {
      Main.printElem(stack.pop());
    }
    System.out.println("");

    stack.resize(20);
    for (int idx = 0; idx < 20; idx++) {
      stack.push(idx);
    }

    while (!stack.isEmpty()) {
      Main.printElem(stack.pop());
    }
    System.out.println("");

    System.out.println("QueueArr");
    Integer[] queueContainer = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    QueueArr<Integer> queue = new QueueArr<>(queueContainer);

    while (!queue.isEmpty()) {
      Main.printElem(queue.dequeue());
    }
    System.out.println("");

    queue.resize(20);
    for (int idx = 0; idx < 20; idx++) {
      queue.enqueue(idx);
    }

    while (!queue.isEmpty()) {
      Main.printElem(queue.dequeue());
    }
    System.out.println("");

    System.out.println("HeapArr");
    Integer[] heapContainer = {5, 8, 1, 0, 7, 2, 9, 10, 6, 3, 4};
    HeapArr<Integer> heap = new HeapArr<>(heapContainer, (x, y) -> x - y);

    while (!heap.isEmpty()) {
      Main.printElem(heap.pop());
    }
    System.out.println("");

    Integer[] heapContainer2 = {1, 8, 0, 9, 4, 2, 6, 3, 7, 5, 10};
    for (Integer i : heapContainer2) {
      heap.push(i);
    }

    while (!heap.isEmpty()) {
      Main.printElem(heap.pop());
    }
    System.out.println("");

    heap.resize(20);
    for (int idx = 0; idx < 20; idx++) {
      heap.push(idx);
    }

    HeapArr<Integer> reverse = heap.reverse();

    while (!heap.isEmpty()) {
      Main.printElem(heap.pop());
    }
    System.out.println("");

    while (!reverse.isEmpty()) {
      Main.printElem(reverse.pop());
    }
    System.out.println("");

    Result<Integer, String> a = Result.error("Lemma llll");
    Result<Integer, String> b = Result.error("Ligma Script");

    Result<Integer, String> quot = a.bind(x ->
      b.bind(y ->
        y == 0 ? Result.error("DIVBY0!!") : Result.result(x/y)));

    System.out.println(quot);

    Integer[] intArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    List<Integer> intList = new List<>(intArr);
    for (int idx = 0; idx < intList.length(); idx++) {
      Main.printElem(intList.get(idx));
    }
    System.out.println("");

    List<Integer> incrementedIntList = intList.map(x -> x + 2);
    for (int idx = 0; idx < incrementedIntList.length(); idx++) {
      Main.printElem(incrementedIntList.get(idx));
    }
    System.out.println("");

    List<Integer> addList = intList.bind(x ->
      incrementedIntList.bind(y ->
        List.of(x + y)));

    for (int idx = 0; idx < addList.length(); idx++) {
      Main.printElem(addList.get(idx));
    }
    System.out.println("");
  }

}
