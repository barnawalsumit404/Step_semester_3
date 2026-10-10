package week9.assignment_problems;

/**
 * Answers inclusive range-sum queries using one prefix-sum preprocessing pass.
 *
 * <p>A naive solution takes O(qn) time in the worst case when each of q
 * queries scans as many as n visitor counts. This solution takes O(n + q)
 * time and O(n) additional space. Preprocessing is worthwhile when the same
 * visitor data is queried repeatedly, especially for many queries.</p>
 */
public class Problem1_MallFootfallRangeReport {

    public static long[] footfallReport(int[] visitors, int[][] queries) {
        long[] prefix = new long[visitors.length + 1];
        for (int i = 0; i < visitors.length; i++) {
            prefix[i + 1] = prefix[i] + visitors[i];
        }

        long[] report = new long[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0];
            int end = queries[i][1];
            report[i] = prefix[end + 1] - prefix[start];
        }
        return report;
    }

    public static void main(String[] args) {
        int[] visitors = {12, 7, 3, 9, 15, 4, 8};
        int[][] queries = {{0, 2}, {2, 5}, {4, 6}, {3, 3}};

        long[] report = footfallReport(visitors, queries);
        for (long total : report) {
            System.out.print(total + " ");
        }
    }
}
