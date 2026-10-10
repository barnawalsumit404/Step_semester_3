package week9.assignment_problems;

import java.util.HashMap;
import java.util.Map;

/**
 * Counts subarrays with a target sum using frequencies of prefix sums.
 *
 * <p>A brute-force solution that sums every subarray takes O(n^2) time.
 * The prefix-sum frequency map reduces this to O(n) time and O(n)
 * additional space. Negative transactions are supported because the method
 * does not rely on a monotonic sliding window.</p>
 */
public class Problem3_NetBalancePeriodCounter {

    public static long countPeriods(int[] transactions, long k) {
        Map<Long, Integer> prefixFrequencies = new HashMap<>();
        prefixFrequencies.put(0L, 1);

        long prefixSum = 0;
        long matchingPeriods = 0;
        for (int transaction : transactions) {
            prefixSum += transaction;
            matchingPeriods += prefixFrequencies.getOrDefault(prefixSum - k, 0);
            prefixFrequencies.put(
                    prefixSum,
                    prefixFrequencies.getOrDefault(prefixSum, 0) + 1
            );
        }
        return matchingPeriods;
    }

    public static void main(String[] args) {
        int[] transactions = {3, 4, -7, 1, 3, 3, 1, -4};
        System.out.println(countPeriods(transactions, 7));
    }
}
