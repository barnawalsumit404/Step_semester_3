package week11.assignment_problems;

import java.util.ArrayDeque;
import java.util.Deque;

public class Problem2_HackathonBannerPrinter {
    static String expandBanner(String code) {
        Deque<Integer> counts = new ArrayDeque<>();
        Deque<StringBuilder> builders = new ArrayDeque<>();
        StringBuilder current = new StringBuilder();
        int number = 0;

        for (int i = 0; i < code.length(); i++) {
            char character = code.charAt(i);
            if (Character.isDigit(character)) {
                number = number * 10 + character - '0';
            } else if (character == '[') {
                counts.push(number);
                builders.push(current);
                number = 0;
                current = new StringBuilder();
            } else if (character == ']') {
                int count = counts.pop();
                StringBuilder previous = builders.pop();
                for (int repeat = 0; repeat < count; repeat++) {
                    previous.append(current);
                }
                current = previous;
            } else {
                current.append(character);
            }
        }
        return current.toString();
    }

    public static void main(String[] args) {
        System.out.println(expandBanner("3[ab]2[c]"));
        System.out.println(expandBanner("2[x3[y]]z"));
    }
}
