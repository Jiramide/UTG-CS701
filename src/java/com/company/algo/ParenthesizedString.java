package com.company.algo;

import com.company.datastructure.Stack;

public class ParenthesizedString {

  private static char GetClosingParen(char op) {
    return op == '(' ? ')' : ']';
  }

  private static boolean Opening(char paren) {
    return paren == '(' || paren == '[';
  }

  private static boolean Closing(char paren) {
    return paren == ')' || paren == ']';
  }

  private static boolean ParenMatch(char opening, char closing) {
    return GetClosingParen(opening) == closing;
  }

  public static boolean IsProper(String str) {
    Stack<Character> parenStack = new Stack<>();

    for (int idx = 0; idx < str.length(); idx++) {
      char curr = str.charAt(idx);

      if (Opening(curr)) {
        parenStack.push(curr);
      } else if (parenStack.isEmpty() || !ParenMatch(parenStack.pop(), curr)) {
        return false;
      }
    }

    return parenStack.isEmpty();
  }

}
