package cn.dmego.jzoffer;

import java.util.Arrays;

/**
 * 剑指 Offer 29. 顺时针打印矩阵
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 */
public class Offer_29 {

    // 同 LC 54
    public int[] spiralOrder(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return new int[]{};
        int n = matrix[0].length;
        if (n == 0) return new int[]{};
        int[] res = new int[m * n];
        int up = 0, down = m - 1;
        int left = 0, right = n - 1;
        int size = 0;
        while (size < m * n) {
            if (up <= down) {
                for (int i = left; i <= right; i++) {
                    res[size++] = matrix[up][i];
                }
                up++;
            }
            if (left <= right) {
                for (int i = up; i <= down; i++) {
                    res[size++] = matrix[i][right];
                }
                right--;
            }
            if(up <= down) {
                for (int i = right; i >= left; i--) {
                    res[size++] = matrix[down][i];
                }
                down--;
            }
            if (left <= right) {
                for (int i = down; i >= up; i--) {
                    res[size++] = matrix[i][left];
                }
                left++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Offer_29 off = new Offer_29();
        int[][] grid = {{1,2,3},{4,5,6},{7,8,9}};
        int[] res = off.spiralOrder(grid);
        System.out.println(Arrays.toString(res));
    }
}
