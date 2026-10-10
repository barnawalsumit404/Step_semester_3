package week9.assignment_problems;

import java.util.ArrayList;
import java.util.List;

/**
 * Visits every grid cell once in clockwise spiral order.
 *
 * <p>For an m by n grid, the route takes O(mn) time. Apart from the returned
 * output list, the algorithm uses O(1) additional space.</p>
 */
public class Problem5_SpiralStockAuditRoute {

    public static List<Integer> auditRoute(int[][] grid) {
        List<Integer> route = new ArrayList<>();
        int top = 0;
        int bottom = grid.length - 1;
        int left = 0;
        int right = grid[0].length - 1;

        while (top <= bottom && left <= right) {
            for (int column = left; column <= right; column++) {
                route.add(grid[top][column]);
            }
            top++;

            for (int row = top; row <= bottom; row++) {
                route.add(grid[row][right]);
            }
            right--;

            if (top <= bottom) {
                for (int column = right; column >= left; column--) {
                    route.add(grid[bottom][column]);
                }
                bottom--;
            }

            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    route.add(grid[row][left]);
                }
                left++;
            }
        }
        return route;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        System.out.println(auditRoute(grid));
    }
}
