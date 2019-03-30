package com.company;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

      Scanner in = new Scanner(System.in);
      int iter = in.nextInt();

      for (; iter > 0; iter--) {
        System.out.println(
          ParenthesizedString.IsProper(in.next())
            ? "Yes"
            : "No"
        );
      }

      in.close();

  }

}
