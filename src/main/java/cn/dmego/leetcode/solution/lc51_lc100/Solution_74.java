package cn.dmego.leetcode.solution.lc51_lc100;

/**
 * 搜索二维矩阵
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 */
public class Solution_74 {
    /*
      二分法
      根据题目的条件，每行的第一个整数大于前一行的最后一个整数，可以把二维数组看成一个一维数组（将下一行连接到前一行的末尾）
      时间复杂度 O(logmn)
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        // 一维矩阵的总长度
        int sumLen = m * n;
        // 定义二分的两个指针
        int l = 0, r = sumLen - 1;
        while (l <= r) {
            // 计算中间节点时，将一维坐标转换为二维坐标
            int mid = (l + r) >> 1;
            int row = mid / n;
            int col = mid - (row * n);
            if (matrix[row][col] < target) {
                l = mid + 1;
            } else if (matrix[row][col] > target) {
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
      时间复杂度 O(m + n)
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


    public static void main(String[] args) {
        int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        Solution_74 s = new Solution_74();
        boolean res = s.searchMatrix(matrix, 3);
        System.out.println(res);
    }

}
