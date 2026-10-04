package week9.practice_problems;

public class practiceTwo {
    
    public static void main(String[] args) {
        int[][] grid = {
            {4, 9, 2},
            {7, 1, 6},
            {3, 12, 5}
        };

        int[] result = warehouseSummary(grid);

        System.out.println("Total = " + result[0]);
        System.out.println("Max Coordinate = (" + result[1] + "," + result[2] + ")");
    }

    public static int[] warehouseSummary(int[][] grid) {
        int total = 0;
        int max = grid[0][0];
        int maxRow = 0;
        int maxCol = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                total += grid[row][col];
                if (grid[row][col] > max) {
                    max = grid[row][col];
                    maxRow = row;
                    maxCol = col;
                }
            }
        }

        // return results as array: [total, maxRow, maxCol]
        return new int[]{total, maxRow, maxCol};
    }
}
