package cn.dmego.leetcode.solution.lc301_lc350;

/**
 * 矩阵中的最长递增路径
 * 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
 * @author dmego
 * @date 2022/05/05 10:12
 */
public class Solution_329 {

    /*
      记忆化搜索 + DFS
      什么是记忆化搜索：我们在递归或者遍历计算时，会存在重复计算的情况，我们将之前计算的结果保存到一个数组中，下次在递归或遍历计算到该情况后，直接使用即可，无需重复计算。

      1. 定义记忆化数组 dp，存储矩阵中以坐标(i, j) 开始的最长递增路径
      2. 双层for循环遍历矩阵，如果dp[i][j] = 0, 说明从坐标(i, j)开始的最长递增路径还未计算
      3. dfs 遍历矩阵，计算出坐标 (i, j) 的最长递增路径
        3.1 如果坐标(i, j) 上下左右四个方向上的邻节点在矩阵中，并且节点值大于(i, j) 则向那个方向递归求最长递增路径
        3.2 最后坐标(i, j) 的最长递增路径 = 四个方向上的最长递增路径 + 1
        3.3 每次求出坐标 (i, j) 的最长递增路径后，同步更新dp[i][j] 的值
     */
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (m == 0 || n == 0)  return 0;
        // 结果
        int res = 0;
        // 记忆化搜索的结果存储数组
        // 存储从坐标(r, c)开始的最长递增路径
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果 (i, j) 坐标还没求出最长递增路径，递归求出
                if (dp[i][j] == 0) {
                    // 更新出整个矩阵中的最长递增路径值 res
                    res = Math.max(res, dfs(matrix, dp, i, j));
                }
            }
        }
        return res;
    }

    // dfs 深度优先遍历求 (r, c) 坐标开始的最长递增路径
    public int dfs(int[][] matrix, int[][] dp, int r, int c) {
        // 如果坐标 (r, c) 不在矩阵中，返回 0
        if (!inArea(matrix, r, c)) return 0;
        // 如果坐标 (r, c) 的最长递增路径已经求出，直接返回
        if (dp[r][c] != 0) return dp[r][c];
        // 定义 4 个方向上的最长递增路径值
        int le = 0, ri = 0, up = 0, dw = 0;
        // 如果 (r - 1, c) 坐标的值大于 (r, c) 坐标值，说明递增路径可以向左
        if (0 <= r - 1 && matrix[r - 1][c] > matrix[r][c]) {
            // 递归求出向左的最长递增路径
            le = dfs(matrix, dp, r - 1, c);
        }
        // 如果 (r + 1, c) 坐标的值大于 (r, c) 坐标值，说明递增路径可以向右
        if (r + 1 < matrix.length && matrix[r + 1][c] > matrix[r][c]) {
            // 递归求出向右的最长递增路径
            ri = dfs(matrix, dp, r + 1, c);
        }
        // 如果 (r, c + 1) 坐标的值大于 (r, c) 坐标值，说明递增路径可以向上
        if (c + 1 < matrix[0].length && matrix[r][c + 1] > matrix[r][c]) {
            // 递归求出向上的最长递增路径
            up = dfs(matrix, dp, r, c + 1);
        }
        // 如果 (r, c - 1) 坐标的值大于 (r, c) 坐标值，说明递增路径可以向下
        if (0 <= c - 1 && matrix[r][c - 1] > matrix[r][c]) {
            // 递归求出向下的最长递增路径
            dw = dfs(matrix, dp, r, c - 1);
        }
        // 更新 dp[r][c] 也就是以坐标(r, c) 为起点的最长递增路径：
        // 也就是 (r, c) 4 个方向上的最长的递增路径 + 1
        dp[r][c] = Math.max(Math.max(le, ri), Math.max(up, dw)) + 1;
        return dp[r][c];
    }

    // 上下左右四个方向的方向数组
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    // 使用方向数组来递归求四个方向上的最长递增路径
    public int dfs2(int[][] matrix, int[][] dp, int r, int c) {
        // 如果坐标 (r, c) 不在矩阵中，返回 0
        if (!inArea(matrix, r, c)) return 0;
        // 如果坐标 (r, c) 的最长递增路径已经求出，直接返回
        if (dp[r][c] != 0) return dp[r][c];
        int maxLen = 0;
        for (int[] dir : dirs) {
            int nextI = dir[0]; // (1, -1, 0, 0)
            int nextJ = dir[1]; // (0, 0, 1, -1)
            if (0 <= r + nextI && r + nextI < matrix.length
                    && 0 <= c + nextJ && c + nextJ < matrix[0].length
                    && matrix[r + nextI][c + nextJ] > matrix[r][c]) {
                maxLen = Math.max(maxLen, dfs2(matrix, dp, r + nextI, c + nextJ));
            }
        }
        dp[r][c] = maxLen + 1;
        return dp[r][c];
    }


    // 判断坐标(r, c) 是否在矩阵中
    public boolean inArea(int[][] matrix, int r, int c) {
        return 0 <= r && r < matrix.length && 0 <= c && c <= matrix[0].length;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{9,9,4},{6,6,8},{2,1,1}};
        Solution_329 s = new Solution_329();
        int res = s.longestIncreasingPath(matrix);
        System.out.println(res);
    }

}
