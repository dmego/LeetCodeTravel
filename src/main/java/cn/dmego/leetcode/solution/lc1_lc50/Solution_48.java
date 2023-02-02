package cn.dmego.leetcode.solution.lc1_lc50;

/**
 * [48] 旋转图像
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 */
public class Solution_48 {

    /**
      - 先按 45度角 这条对角线将二维矩阵进行对称反转
      - 然后对于每一行的一维矩阵再进行反转
      - 这样得到的二维矩阵就是顺时针旋转 90 度的结果
      - 更多：如果逆时针旋转 90 度，那么则按 135 度角这条对角线先对称反转，然后再反转每一行
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 按"左上角"-"右下角" 这条对角线对称反转
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                // 交换 matrix[i][j] 和 matrix[j][i]
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        // 反转每一行
        for(int i = 0; i < n; i++) {
            // 首尾指针
            int l = 0, r = n - 1;
            while (l < r) {
                int tmp = matrix[i][l];
                matrix[i][l] = matrix[i][r];
                matrix[i][r] = tmp;
                l++;
                r--;
            }
        }
    }
}
