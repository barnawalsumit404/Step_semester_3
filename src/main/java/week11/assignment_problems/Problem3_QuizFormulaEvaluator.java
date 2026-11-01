package week11.assignment_problems;

import java.util.ArrayDeque;
import java.util.Deque;

public class Problem3_QuizFormulaEvaluator {
    static int evaluate(String expr) {
        Deque<Integer> numbers = new ArrayDeque<>();
        Deque<Character> operators = new ArrayDeque<>();
        int i = 0;

        while (i < expr.length()) {
            char character = expr.charAt(i);
            if (character == ' ') {
                i++;
            } else if (Character.isDigit(character)) {
                int number = 0;
                while (i < expr.length() && Character.isDigit(expr.charAt(i))) {
                    number = number * 10 + expr.charAt(i) - '0';
                    i++;
                }
                numbers.push(number);
            } else if (character == '(') {
                operators.push(character);
                i++;
            } else if (character == ')') {
                while (operators.peek() != '(') {
                    apply(numbers, operators.pop());
                }
                operators.pop();
                i++;
            } else {
                while (!operators.isEmpty()
                        && operators.peek() != '('
                        && precedence(operators.peek()) >= precedence(character)) {
                    apply(numbers, operators.pop());
                }
                operators.push(character);
                i++;
            }
        }

        while (!operators.isEmpty()) {
            apply(numbers, operators.pop());
        }
        return numbers.pop();
    }

    private static int precedence(char operator) {
        return operator == '+' || operator == '-' ? 1 : 2;
    }

    private static void apply(Deque<Integer> numbers, char operator) {
        int right = numbers.pop();
        int left = numbers.pop();
        if (operator == '+') {
            numbers.push(left + right);
        } else if (operator == '-') {
            numbers.push(left - right);
        } else if (operator == '*') {
            numbers.push(left * right);
        } else {
            numbers.push(left / right);
        }
    }

    public static void main(String[] args) {
        System.out.println(evaluate("2 * (3 + 4) - 10 / 5"));
        System.out.println(evaluate("(8 - 2) * (1 + 2) / 4"));
    }
}
