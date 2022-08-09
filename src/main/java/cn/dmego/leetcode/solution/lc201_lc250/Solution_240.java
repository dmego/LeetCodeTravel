package cn.dmego.leetcode.solution.lc201_lc250;

/**
 * 搜索二维矩阵 II
 */
public class Solution_240 {


    /*
       方法一：二分查找
       遍历每一行，对每一行的元素进行二分查找
       时间复杂度O(m * logn)
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        for (int[] array : matrix) {
            if (search(array, target)) {
                return true;
            }
        }
        return false;
    }
    // 二分查找一维数组
    public boolean search(int[] array, int target) {
        int l = 0, r = array.length - 1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (array[mid] < target) {
                l = mid + 1;
            } else if (array[mid] > target) {
                r = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }


    /*
    方法二：Z 字型查找
      - 从第一行的最后一个元素 curr(row, col)开始查找
      - 如果 curr < target, 需要找一个更大的元素，可以向下遍历，判断 (row, col+1) 与 target 大小
      - 如果 curr > target, 需要找一个更小的元素，可以往前遍历，判断 (row + 1, col) 与 target 大小
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        // 从第一行的最后一个元素开始查找
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] < target) {
                row++;
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                return true;
            }
        }
        return false;
    }

}
