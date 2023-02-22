package cn.dmego.leetcode.solution.lc51_lc100;

/**
 * [79] 单词搜索
 */
public class Solution_79 {

    /**
     递归 + 回溯
     */
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        // 标记数组，用来标记 board 某个坐标的字符是否使用过
        boolean[][] visited = new boolean[m][n];
        char[] words = word.toCharArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, i, j, words, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 递归 + 回溯找到判断以 (r, c) 坐标开头的字符能否找到满足 word 的单词
    public boolean dfs(char[][] board, int r, int c, char[] words, int index, boolean[][] visited) {
        // 如果遍历到了 word 的尾部，说明找到符合要求的单词了
        if (index == words.length) return true;
        // 如果坐标 (r, c) 不在 board 中，返回 false
        if (!inArea(board, r, c)) return false;
        // 如果坐标 (r, c) 已经遍历过了，返回 false
        if (visited[r][c]) return false;
        // 如果坐标 (r, c) 字符正好等于 word 的 index 下标字符，则继续递归变量下个是否也相等
        if (board[r][c] == words[index]) {
            // 首先先将 (r, c) 坐标设置为已经遍历过
            visited[r][c] = true;
            // 递归遍历 4 个方向
            boolean up = dfs(board, r - 1, c, words, index + 1, visited);
            boolean down = dfs(board, r + 1, c, words, index + 1, visited);
            boolean left = dfs(board, r, c - 1, words, index + 1, visited);
            boolean right = dfs(board, r, c + 1, words, index + 1, visited);
            // 回溯，将状态重置
            visited[r][c] = false;
            return up || down || left || right;
        }
        return false;
    }

    public boolean inArea(char[][] board, int r, int c) {
        return 0 <= r && r < board.length && 0 <= c && c < board[0].length;
    }

    public static void main(String[] args) {
        Solution_79 sol = new Solution_79();
        char[][] board = {{'C','A','A'},{'A','A','A'},{'B','C','D'}};
        String word = "AAB";
        boolean exist = sol.exist(board, word);
        System.out.println(exist);
    }



}
