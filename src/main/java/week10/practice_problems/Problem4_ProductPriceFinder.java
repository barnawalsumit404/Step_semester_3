package week10.practice_problems;

public class Problem4_ProductPriceFinder {
    public static int countInRange(int[] prices, int low, int high) {
        int lowerIndex = lowerBound(prices, low);
        int upperIndex = upperBound(prices, high);
        return upperIndex - lowerIndex;
    }

    public static int lowerBound(int[] prices, int target) {
        int left = 0;
        int right = prices.length;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (prices[middle] < target) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return left;
    }

    public static int upperBound(int[] prices, int target) {
        int left = 0;
        int right = prices.length;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (prices[middle] <= target) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] prices = {100, 150, 150, 200, 300, 450};
        System.out.println("Lower bound index " + lowerBound(prices, 150));
        System.out.println("Upper bound index " + upperBound(prices, 300));
        System.out.println("Count " + countInRange(prices, 150, 300));
    }
}
