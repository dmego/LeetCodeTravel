package cn.dmego.leetcode.solution.lc401_;

/**
 * [885] 螺旋矩阵 III
 */
public class Solution_885 {

    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int[][] result = new int[rows * cols][2];
        // (rStart, cStart) 对应在 n * n 矩阵内坐标是 (s, s)
        int s = Math.max(Math.max(rows - 1 - rStart, rStart), Math.max(cols - 1 - cStart, cStart));
        // n 是 螺旋矩阵的边长
        int n = s * 2 + 1;
        // diff 表示 入参矩阵与螺旋矩阵坐标之间的差值
        int rDiff = s - rStart, cDiff = s - cStart;
        int up = s, down = s, left = s, right = s;
        int size = 1, index = 0;
        // 从 (s, s) 开始，顺时针螺旋遍历
        while (size <= n * n) {
            if (up >= 0 && down < n) {
                for (int i = left; i <= right; i++) {
                    boolean boo = addToResult(up, i, rDiff, cDiff, rows, cols, result, index);
                    if (boo) {
                        index++;
                    }
                    size++;
                }
            }
            right++;
            if (left >= 0 && right < n) {
                for (int i = up; i <= down; i++) {
                    boolean boo = addToResult(i, right, rDiff, cDiff, rows, cols, result, index);
                    if (boo) {
                        index++;
                    }
                    size++;
                }
            }
            down++;
            if (up >= 0 && down < n) {
                for (int i = right; i >= left; i--) {
                    boolean boo = addToResult(down, i, rDiff, cDiff, rows, cols, result, index);
                    if (boo) {
                        index++;
                    }
                    size++;
                }
            }
            left--;
            if (left >= 0 && right < n) {
                for (int i = down; i >= up; i--) {
                    boolean boo = addToResult(i, left, rDiff, cDiff, rows, cols, result, index);
                    if (boo) {
                        index++;
                    }
                    size++;
                }
            }
            up--;
        }
        return result;
    }

    // 判断(r, c) 是否在入参矩阵中，是则加到结果数组里
    public boolean addToResult(int r, int c, int rDiff, int cDiff, int rows, int cols, int[][] result, int index) {
        int tr = r - rDiff;
        int tc = c - cDiff;
        if (0 <= tr && tr < rows && 0 <= tc && tc < cols) {
            result[index] = new int[] { tr, tc };
            return true;
        }
        return false;
    }
}
