package week9.assignment_problems;

/**
 * Finds the longest contiguous run whose total cost is within a budget.
 *
 * <p>Checking every possible run takes O(n^2) time if prefix sums are used
 * for each candidate. The sliding-window solution takes O(n) time and O(1)
 * additional space. Non-negative costs are essential: moving the left
 * boundary forward can never increase the window sum, so each boundary only
 * moves forward.</p>
 */
public class Problem2_LongestBudgetFriendlyStreak {

    /**
     * @return an array containing {longest length, earliest start index}
     */
    public static int[] longestStreak(int[] costs, long budget) {
        long windowSum = 0;
        int left = 0;
        int bestLength = 0;
        int bestStart = -1;

        for (int right = 0; right < costs.length; right++) {
            windowSum += costs[right];

            while (left <= right && windowSum > budget) {
                windowSum -= costs[left++];
            }

            int currentLength = right - left + 1;
            if (currentLength > bestLength) {
                bestLength = currentLength;
                bestStart = left;
            }
        }

        return new int[]{bestLength, bestStart};
    }

    public static void main(String[] args) {
        int[] costs = {4, 2, 1, 7, 3, 1, 2, 1, 5};
        int[] result = longestStreak(costs, 8);
        System.out.println("(" + result[0] + ", " + result[1] + ")");
    }
}
