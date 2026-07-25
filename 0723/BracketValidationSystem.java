import java.util.ArrayDeque;
import java.util.Deque;

public class BracketValidationSystem {
    public static void main(String[] args) {
        String[] tests = {
            "([]{})",
            "([)]",
            "((a + b) * [c - d])",
            "func(a, [b, c) )",
            "if (x > 0) { arr[i] = {1, 2, 3}; }",
            "(((",
            ")))",
            "no brackets here"
        };

        for (String test : tests) {
            System.out.println(test + " -> " + (isValid(test) ? "合法" : "不合法"));
        }
    }

    public static boolean isValid(String text) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (isLeftBracket(ch)) {
                stack.push(ch);
            } else if (isRightBracket(ch)) {
                if (stack.isEmpty() || !isMatchedPair(stack.pop(), ch)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean isLeftBracket(char ch) {
        return ch == '(' || ch == '[' || ch == '{';
    }

    private static boolean isRightBracket(char ch) {
        return ch == ')' || ch == ']' || ch == '}';
    }

    private static boolean isMatchedPair(char left, char right) {
        return (left == '(' && right == ')')
            || (left == '[' && right == ']')
            || (left == '{' && right == '}');
    }
}
