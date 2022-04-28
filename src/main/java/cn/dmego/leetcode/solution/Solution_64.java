package cn.dmego.leetcode.solution;

/**
 * 最小路径和
 * @author dmego
 * @date 2022/04/28 12:30
 */
public class Solution_64 {

    /*
    dp[i][j] 表示从 (i, j) 到 (m, n) 终点的最小路径和
    我们从下到上，从右往左遍历
    */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = grid[m - 1][n - 1];
        for (int i = m - 2; i >= 0; i--) dp[i][n - 1] = dp[i + 1][n - 1] + grid[i][n - 1];
        for (int j = n - 2; j >= 0; j--) dp[m - 1][j] = dp[m - 1][j + 1] + grid[m - 1][j];
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + grid[i][j];
            }
        }
        return dp[0][0];
    }
}
