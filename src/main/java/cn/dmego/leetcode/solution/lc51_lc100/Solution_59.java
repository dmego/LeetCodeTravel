package cn.dmego.leetcode.solution.lc51_lc100;

/**
 * [59] 螺旋矩阵 II
 */
public class Solution_59 {

    /**
      类似于 lc 54 题
     */
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int upBound = 0, downBound = n - 1;
        int leftBound = 0, rightBound = n - 1;
        // 从 1 开始，表示要加入到矩阵的值
        int curr = 1;
        // 循环结束条件为：curr <= n * n，也就是最后一个加入矩阵的值为 n * n
        while (curr <= n * n) {
            if (upBound <= downBound) {
                for (int i = leftBound; i<= rightBound; i++) {
                    result[upBound][i] = curr;
                    curr++;
                }
                upBound++;
            }
            if (leftBound <= rightBound) {
                for (int i = upBound; i <= downBound; i++) {
                    result[i][rightBound] = curr;
                    curr++;
                }
                rightBound--;
            }
            if (upBound <= downBound) {
                for (int i = rightBound; i >= leftBound; i--) {
                    result[downBound][i] = curr;
                    curr++;
                }
                downBound--;
            }
            if (leftBound <= rightBound) {
                for (int i = downBound; i >= upBound; i--) {
                    result[i][leftBound] = curr;
                    curr++;
                }
                leftBound++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution_59 solution = new Solution_59();
        int n = 3;
        int[][] ints = solution.generateMatrix(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(ints[i][j]);
            }
            System.out.println();
        }
    }
}
