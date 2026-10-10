package week9.assignment_problems;

/**
 * Counts sorted scores in an inclusive band with two boundary binary searches.
 *
 * <p>A linear scan takes O(n) time and O(1) space. This solution takes
 * O(log n) time and O(1) additional space. The lower-bound search includes
 * duplicate values equal to low, while the upper-bound search moves past all
 * duplicate values equal to high.</p>
 */
public class Problem4_ExamScoreBandCounter {

    public static int countInBand(int[] scores, int low, int high) {
        int firstAtLeastLow = lowerBound(scores, low);
        int firstGreaterThanHigh = upperBound(scores, high);
        return firstGreaterThanHigh - firstAtLeastLow;
    }

    private static int lowerBound(int[] scores, int target) {
        int left = 0;
        int right = scores.length;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (scores[middle] < target) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return left;
    }

    private static int upperBound(int[] scores, int target) {
        int left = 0;
        int right = scores.length;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (scores[middle] <= target) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] scores = {35, 42, 42, 50, 58, 58, 58, 63, 71, 88};
        System.out.println(countInBand(scores, 42, 58));
    }
}
