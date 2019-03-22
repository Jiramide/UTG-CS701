package com.company;

import java.util.Iterator;

public class Main {

  public static void main(String[] args) {

    SinglyLinkedList<Integer> llist = new SinglyLinkedList<>();

    for (Integer idx = 99; idx >= 0; idx--) {
      llist.cons(idx);
    }

    System.out.println("Original: ");
    System.out.println(llist.toString());

    SinglyLinkedList<Integer> llistc = llist.clone();

    System.out.println("Clone: ");
    System.out.println(llistc.toString());

    System.out.println("Manually using Iterator.");
    for (Iterator<Integer> it = llist.iterator(); it.hasNext(); ) {
      System.out.print(it.next());
      System.out.print(" ");
    }

    System.out.println("");
    System.out.println("Using foreach loop.");

    for (Integer elem : llistc) {
      System.out.print(elem);
      System.out.print(" ");
    }

    System.out.println("\nOriginal length: " + llist.length());
    System.out.println("Clone length: " + llistc.length());

    Stack<Integer> stack = new Stack<>();

    for (Integer idx = 0; idx < 100; idx++) {
      stack.push(idx);
    }

    while (!stack.isEmpty() && stack.peek() > 50) {
      System.out.print(stack.pop());
      System.out.print(" ");
    }

    System.out.println("\n" + stack.toString());

    while (!stack.isEmpty()) {
      System.out.print(stack.safePop());
      System.out.print(" ");
    }

    System.out.println(stack.safePop());
    System.out.println(stack.safePop());
    System.out.println(stack.safePop());

  }

}
