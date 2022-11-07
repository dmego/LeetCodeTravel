package cn.dmego.leetcode.solution.lc51_lc100;

/**
 * 最小路径和
 * @author dmego
 * @date 2022/04/28 12:30
 */
public class Solution_64 {

    /*
    dp 数组定义：dp[i][j] 表示从 (i, j) 到 (m, n) 终点的最小路径和
    dp 数组初始化：第一行与最左列的最小路径和可以直接求出来，因为只有一条路径到达
    递推公式：dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];

    */
    public int minPathSum(int[][] grid) {
        // dp[i][j] 表示坐标 (0, 0) 到坐标(i, j) 最小路径和
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        // 初始化(0, 0) 到 (m - 1, 0) 也就是最上一行的最小路径值，只有一条路径，就是往右走
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        // 初始化(0, 0) 到 (0, n - 1) 也就是最左一列的最小路径值，也只有一条路径，就是往下走
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}
