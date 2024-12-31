package cn.dmego.newcode.top101.backtracking;

import java.util.Arrays;

/**
 * n 皇后问题
 * @author dmego
 * @date 2022/05/05 10:38
 */
public class BM59 {

    // 最后的结果
    int result = 0;
    public int Nqueen (int n) {
        // 定义棋盘
        char[][] board = new char[n][n];
        // 初始化棋盘，'.' 表示空
        for(char[] c : board) Arrays.fill(c, '.');
        backtracking(board, 0, n);
        return result;
    }
    // 回溯
    public void backtracking(char[][] board, int row, int n) {
        // 结束条件，递归到了棋盘的最后一列
        if (row == n) {
            // row == n 说明找到一种放置方法，结果+1
            result++;
            return;
        }
        // 横向遍历棋盘的行
        for (int i = 0; i < n; i++) {
            // 判断位置 (row, i) 是否可以放置皇后
            if (isValid(board, row, i, n)) {
                // 放置皇后
                board[row][i] = 'Q';
                //纵向递归到下一列 row+1
                backtracking(board, row + 1, n);
                // 撤销放置
                board[row][i] = '.';
            }
        }
    }
    // 判断位置 (r, c) 是否可以放置皇后
    public boolean isValid(char[][] board, int r, int c, int n) {
        // 判断同一列
        for (int i = 0; i < r; i++) {
            if (board[i][c] == 'Q') return false;
        }
        // 不用判断同一行，因为回溯中，同一行的都撤销了

        // 判断45度对角线，只需要判断上半部分
        for (int i = r - 1, j = c + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        // 判断135度对角线，只需要判断上半部分
        for (int i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }

    public static void main(String[] args) {
        BM59 bm = new BM59();
        int nqueen = bm.Nqueen(3);
        System.out.println(nqueen);
    }
}
