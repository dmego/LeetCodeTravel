package cn.dmego.leetcode.solution.lc401_;

import java.util.Arrays;

/**
 * [2373] 矩阵中的局部最大值
 */
public class Solution_2373 {

    public int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        int[][] res = new int[n - 2][n - 2];
        for (int i = 1; i <= n - 2; i++) {
            for (int j = 1; j <= n - 2; j++) {
                res[i - 1][j - 1] = getMax(grid, i, j);
            }
        }
        return res;
    }

    // 从 grid 找 以 (r, c) 坐标为中心点的 3 x 3 矩阵中的最大值
    int[][] loop = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 0}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    public int getMax(int[][] grid, int r, int c) {
        int max = grid[r][c];
        for (int[] dir: loop) {
            max = Math.max(max, grid[r + dir[0]][c + dir[1]]);
        }
        return max;
    }

    public static void main(String[] args) {
        Solution_2373 sol = new Solution_2373();
        int[][] grid = {{1,1,1,1,1},{1,1,1,1,1},{1,1,2,1,1},{1,1,1,1,1},{1,1,1,1,1}};
        int[][] res = sol.largestLocal(grid);
        for (int[] re: res) {
            System.out.println(Arrays.toString(re));
        }
    }
}
