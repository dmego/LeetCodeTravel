package cn.dmego.leetcode;

/**
 * @author dmego
 * @date 2021/12/28 15:21
 */
public class Solution_63 {
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        // 初始化
        for (int i = 0; i < m; i++) {
            // 如果(i,0) = 1 是障碍，则 (i,0) 右边的 (i+1。。, 0) 都无法到达
            if (obstacleGrid[i][0] == 1) {
                for (int k = i; k < m; k++) {
                    dp[k][0] = 0;
                }
                break;
            }
            else if (obstacleGrid[i][0] == 0) dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            if (obstacleGrid[0][j] == 1) {
                for (int k = j; k < n; k++) {
                    dp[0][k] = 0;
                }
                break;
            }
            else if (obstacleGrid[0][j] == 0) dp[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 如果 (i,j) == 1 说明这个节点是障碍, 没有路径到达这里
                if (obstacleGrid[i][j] == 1) dp[i][j] = 0;
                else if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
//        int[][] obstacleGrid = new int[][]{{0,0},{1,1},{0,0}};
        int[][] obstacleGrid = new int[][]{{1,0}};
        int paths = uniquePathsWithObstacles(obstacleGrid);
        System.out.println(paths);
    }
}
