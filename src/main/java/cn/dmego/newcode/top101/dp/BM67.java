package cn.dmego.newcode.top101.dp;

/**
 * 不同的路径（1）
 * @author dmego
 * @date 2022/03/22 10:19
 */
public class BM67 {

    /**
     一条路径是从起点到终点所经过所有坐标。机器人只能向下或向右移动。
     1. 确定 dp 数组的定义和下标的含义：dp[i][j] 表示 从起点(0,0) 到终点(i, j)的路径数
     2. dp 数组的递推公式：到达 (i, j) 坐标只能从两个位置过来 (i - 1, j) 或 (i , j - 1),
      因为 dp[i][j] 表示到达 (i, j) 的路径数，所以 dp[i][j] = dp[i - 1][j] + dp[i][j - 1],
     3. dp 数组的初始化：dp[0][0] = 1, dp[0][1], dp[1][0] ... 值都是 1， 因为只有一个方向可以走
     4. 如何遍历：根据上面的条件，我们能求出哪个坐标的路径数，很明显是 (1, 1), 因为 dp[1][1] = dp[0][1] + dp[1][0],
      根据 dp[1][1] 的值我们就能求出 dp[1][2], 所有我们可以从左往右遍历地图
     */
    public int uniquePaths (int m, int n) {
        // write code here
        if (m == 0 && n == 0) return 0;
        if (m == 0 || n == 0) return 1;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int j = 0; j < n; j++) dp[0][j] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

}
