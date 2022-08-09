package cn.dmego.leetcode.solution.lc301_lc350;

/**
 * 矩阵内的最长路径
 * @author dmego
 * @date 2022/05/05 10:12
 */
public class Solution_329 {

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (m == 0 || n == 0) return 0;
        int[][] dp = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) {
                    res = Math.max(res, dfs(matrix, dp, i, j));
                }
            }
        }
        return res;
    }

    public int dfs(int[][] matrix, int[][] dp, int r, int c) {
        if (!inArea(matrix, r, c)) return 0;
        if (dp[r][c] == 0) {
            int le = 0, ri = 0, up = 0, dw = 0;
            if (0 <= r - 1 && matrix[r - 1][c] > matrix[r][c]) {
                le = dfs(matrix, dp, r - 1, c);
            }
            if (r + 1 < matrix.length && matrix[r + 1][c] > matrix[r][c]) {
                ri = dfs(matrix, dp, r + 1, c);
            }
            if (0 <= c - 1 && matrix[r][c - 1] > matrix[r][c]) {
                up = dfs(matrix, dp, r, c - 1);
            }
            if (c + 1 < matrix[0].length && matrix[r][c + 1] > matrix[r][c]) {
                dw = dfs(matrix, dp, r, c + 1);
            }
            dp[r][c] = Math.max(Math.max(le, ri), Math.max(up, dw)) + 1;
        }
        return dp[r][c];
    }

    public boolean inArea(int[][] matrix, int r, int c) {
        return 0 <= r && r < matrix.length && 0 <= c && c < matrix[0].length;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{9,9,4},{6,6,8},{2,1,1}};
        Solution_329 s = new Solution_329();
        int res = s.longestIncreasingPath(matrix);
        System.out.println(res);
    }

}
