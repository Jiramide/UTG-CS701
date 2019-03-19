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

    for (Iterator<Integer> it = llist.iterator(); it.hasNext(); ) {
      System.out.print(it.next());
      System.out.print(" ");
    }

    System.out.println("");

    for (Integer elem : llistc) {
      System.out.print(elem);
      System.out.print(" ");
    }

  }

}
