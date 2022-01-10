package cn.dmego.leetcode.codetop.medium;

/**
 * 岛屿数量
 * @author dmego
 * @date 2022/01/10 15:21
 */
public class LC_200 {
    // 遍历包含 (row, col) 的大陆，并标记
    public void dfs (char[][] grid, int row, int col) {
        if (!inArea(grid, row, col)) return;
        if (grid[row][col] == '0' || grid[row][col] == '2') return;
        grid[row][col] = '2';
        dfs (grid, row - 1, col);
        dfs (grid, row + 1, col);
        dfs (grid, row, col - 1);
        dfs (grid, row, col + 1);
    }

    // 判断是否在边界内
    public boolean inArea(char[][] grid, int row, int col) {
        return 0 <= row && row < grid.length && 0 <= col && col < grid[0].length;
    }

    public int numIslands(char[][] grid) {
        int island = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    island++;
                }
            }
        }
        return island;
    }
}
