package week11.assignment_problems;

import java.util.ArrayDeque;
import java.util.Queue;

public class Problem5_FireDrillExits {
    static int[][] exitDistance(char[][] plan) {
        int rows = plan.length;
        int columns = plan[0].length;
        int[][] distance = new int[rows][columns];
        Queue<int[]> queue = new ArrayDeque<>();

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                distance[row][column] = -1;
                if (plan[row][column] == 'E') {
                    distance[row][column] = 0;
                    queue.add(new int[]{row, column});
                }
            }
        }

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] cell = queue.remove();
            for (int[] direction : directions) {
                int nextRow = cell[0] + direction[0];
                int nextColumn = cell[1] + direction[1];
                if (nextRow >= 0 && nextRow < rows
                        && nextColumn >= 0 && nextColumn < columns
                        && plan[nextRow][nextColumn] != '#'
                        && distance[nextRow][nextColumn] == -1) {
                    distance[nextRow][nextColumn] = distance[cell[0]][cell[1]] + 1;
                    queue.add(new int[]{nextRow, nextColumn});
                }
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        char[][] plan = {
                {'.', '#', '.', 'E'},
                {'.', '.', '.', '#'},
                {'E', '#', '.', '.'}
        };
        int[][] result = exitDistance(plan);
        for (int[] row : result) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
