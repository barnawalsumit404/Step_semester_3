package week9.practice_problems;

public class problemOne {
    public static void main(String[] args) {
        int[] nums1 = {-4, -1, 0, 3, 5, 9};
        int target1 = 4;
        System.out.println(pairSumSorted(nums1, target1)); // Expected: (-1, 5)

        int[] nums2 = {1, 2, 3};
        int target2 = 100;
        System.out.println(pairSumSorted(nums2, target2)); // Expected: Not Found
    }

    public static String pairSumSorted(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];

            if (sum == target) {
                return "(" + nums[left] + ", " + nums[right] + ")";
            } else if (sum < target) {
                left++; // need a bigger sum → move left pointer forward
            } else {
                right--; // need a smaller sum → move right pointer backward
            }
        }

        return "Not Found";
    }
}
