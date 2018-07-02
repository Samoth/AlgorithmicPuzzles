package pl.florsoft.codility;

import java.util.Stack;

/**
 * Task: https://app.codility.com/programmers/lessons/7-stacks_and_queues/brackets/
 */
public class L07Brackets {

    public int solution(String S) {
        Stack<Character> openBrackets = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            char bracket = S.charAt(i);
            if (bracket == '(' || bracket == '{' || bracket == '[') {
                openBrackets.push(bracket);
            } else if (openBrackets.isEmpty() || getCloseBracket(openBrackets.pop()) != bracket) {
                return 0;
            }
        }
        return openBrackets.isEmpty() ? 1 : 0;
    }

    private char getCloseBracket(char openBracket) {
        if (openBracket == '(') {
            return ')';
        } else if (openBracket == '[') {
            return ']';
        } else {
            return '}';
        }
    }

}
