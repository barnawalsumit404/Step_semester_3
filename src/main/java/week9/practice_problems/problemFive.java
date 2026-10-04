package week9.practice_problems;

public class problemFive {
    public static void main(String[] args) {
        int[] sales = {2, 1, 5, 1, 3, 2};
        int k = 3;

        int result = maxSumSubarray(sales, k);
        System.out.println(result); // Expected: 9
    }

    public static int maxSumSubarray(int[] sales, int k) {
        int windowSum = 0;

        // sum of first k elements
        for (int i = 0; i < k; i++) {
            windowSum += sales[i];
        }

        int maxSum = windowSum;

        // slide the window
        for (int i = k; i < sales.length; i++) {
            windowSum += sales[i] - sales[i - k]; // add new, remove old
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }
}
