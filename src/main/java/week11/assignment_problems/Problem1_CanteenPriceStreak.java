package week11.assignment_problems;

import java.util.ArrayDeque;
import java.util.Deque;

public class Problem1_CanteenPriceStreak {
    static int[] priceStreak(int[] price) {
        int[] result = new int[price.length];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < price.length; i++) {
            while (!stack.isEmpty() && price[stack.peek()] <= price[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] result = priceStreak(new int[]{100, 80, 60, 70, 60, 75, 85});
        for (int value : result) {
            System.out.print(value + " ");
        }
    }
}
