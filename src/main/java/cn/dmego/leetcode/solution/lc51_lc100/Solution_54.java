package cn.dmego.leetcode.solution.lc51_lc100;

import java.util.ArrayList;
import java.util.List;

/**
 * [54] 螺旋矩阵
 * @author dmego
 * @date 2022/01/11 10:31
 */
public class Solution_54 {

    /**
     - 按照顺时针的方向：从顶部，右侧，底部，左侧 顺序四个方向来遍历矩阵
     - 定义一个 list, 用来记录遍历结果集
     - 定义四个方向的界限
        - upBound: 上边界，从 0 开始，往下移动
        - downBound: 下边界，从 m - 1 开始，往上移动
        - leftBound: 左边界，从 0 开始，往右移动
        - rightBound: 右边界，从 n - 1 开始，往左移动
     - while 循环，循环条件为 list.size < m * n
        - 从左往右遍历，遍历上边界所在一维矩阵
        - 从上往下遍历，遍历右边界所在一维矩阵
        - 从右往左遍历，遍历下边界所在一维矩阵
        - 从下往上遍历，遍历左边界所在一维矩阵
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int upBound = 0; // 上边界下标
        int downBound = m - 1; // 下边界下标
        int leftBound = 0; // 左边界下标
        int rightBound = n - 1; // 右边界下标

        // 循环条件，result 大小是小于矩阵坐标数个数
        while (result.size() < m * n) {
            // 遍历上边界所在一维矩阵(顶部)，从左往右遍历
            if (upBound <= downBound) {
                // 从 左边界 遍历到 右边界
                for (int i = leftBound; i <= rightBound; i++) {
                    result.add(matrix[upBound][i]);
                }
                // 上边界往下移动一位
                upBound++;
            }
            // 遍历右边界所在一维矩阵(右侧)，从上往下遍历
            if (leftBound <= rightBound) {
                for (int i = upBound; i <= downBound; i++) {
                    result.add(matrix[i][rightBound]);
                }
                // 右边界往左移动一位
                rightBound--;
            }
            // 遍历下边界所在一维矩阵(底部)，从右往左遍历
            if (upBound <= downBound) {
                for (int i = rightBound; i >= leftBound; i--) {
                    result.add(matrix[downBound][i]);
                }
                // 下边界往上移动一位
                downBound--;
            }
            // 遍历左边界所在一维矩阵(左侧)，从下往上遍历
            if (leftBound <= rightBound) {
                for (int i = downBound; i >= upBound; i--) {
                    result.add(matrix[i][leftBound]);
                }
                // 左边界往右移动一位
                leftBound++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        Solution_54 s = new Solution_54();
        List<Integer> integers = s.spiralOrder(matrix);
        System.out.println(integers);
    }
}
