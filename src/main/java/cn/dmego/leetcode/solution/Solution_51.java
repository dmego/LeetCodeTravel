package cn.dmego.leetcode.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * n 皇后问题（I）
 * @author dmego
 * @date 2022/05/05 12:39
 */
public class Solution_51 {

    // 最后的结果
    List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        // 定义棋盘
        char[][] board = new char[n][n];
        // 初始化棋盘
        for (char[] c : board) {
            Arrays.fill(c, '.');
        }
        // 回溯， row 表示当前皇后要放置的列
        backtracking(board, 0, n);
        return result;
    }

    // 回溯
    public void backtracking(char[][] board, int row, int n) {
        // 递归最后了: last row = (n - 1)
        if (row == n) {
            // row == n 说明找到一种放置方法，将棋盘转为结果List,添加到结果集中
            result.add(arrayToList(board, n));
            return;
        }
        for (int i = 0; i < n; i++) {
            // 判断位置 (row, i) 放在皇后是否满足条件
            if (isValid(board, row, i, n)) {
                // 放置皇后
                board[row][i] = 'Q';
                // 递归到下一列
                backtracking(board, row + 1, n);
                // 撤销放置
                board[row][i] = '.';
            }
        }
    }

    /*
    判断 (r, c) 位置上放置皇后是否可行
    不能同行，不能同列，不能对角线 45度角和 135 度角
    */
    public boolean isValid(char[][] board, int r, int c, int n) {
        // 检查同列
        for (int i = 0; i <= r; i++) {
            if (board[i][c] == 'Q') return false;
        }
        // 不用检查同行，因为递归是往下的，同行的遍历，会被回溯撤销

        // 检查 135 度对角线 上半部分
        for (int i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        // 检查 45 度角对象线 上半部分
        for (int i = r - 1, j = c + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }

    public List<String> arrayToList(char[][] board, int n) {
        List<String> result = new ArrayList<>();
        StringBuilder buff = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                buff.append(board[i][j]);
            }
            result.add(buff.toString());
            buff.delete(0, n);
        }
        return result;
    }

}
