package cn.dmego.newcode.top101.dp;

/**
 * 矩阵中的最小路径和
 * 给定一个 n * m 的矩阵 a，从左上角开始每次只能向右或者向下走，
 * 最后到达右下角的位置，路径上所有的数字累加起来就是路径和，输出所有的路径中最小的路径和。
 * @author dmego
 * @date 2022/04/28 12:31
 */
public class BM68 {

    /*
     - dp 数组的定义：dp[i][j] 表示从 (i, j) 到终点的最小路径和
     - 初始化： 终点的向上方向 dp[m - 1][j] 和向左方向 dp[i][n - 1] 到终点只有一条路径，可以进行初始化
         - dp[i][n - 1] = dp[i + 1][n - 1] + matrix[i][n - 1]
         - dp[m - 1][j] = dp[m - 1][j + 1] + matrix[m - 1][j]
     - 递推公式： dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + matrix[i][j]
     - 遍历方向是 从下到上，从左到右
     - 最后结果返回 dp[0][0]
     */
    public int minPathSum (int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = matrix[m - 1][n - 1];
        for (int i = m - 2; i >= 0; i--) dp[i][n - 1] = dp[i + 1][n - 1] + matrix[i][n - 1];
        for (int j = n - 2; j >= 0; j--) dp[m - 1][j] = dp[m - 1][j + 1] + matrix[m - 1][j];
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + matrix[i][j];
            }
        }
        return dp[0][0];
    }

}
