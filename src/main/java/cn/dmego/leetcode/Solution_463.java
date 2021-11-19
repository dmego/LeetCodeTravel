package cn.dmego.leetcode;

/**
 * @author dmego
 * @date 2021/11/15 10:18
 */
public class Solution_463 {
    /**
     计算岛屿周长
     1. 如何找到岛屿，双层 for 循环找到一块陆地，对这块陆地进行 DFS 深度遍历
     2. 如何统计岛屿的周长，找到一块陆地，遍历它的四面
     2.1 如果一面后面是水，周长 + 1
     2.2 如果一面后面是网格边缘，周长 + 1
     */
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rn = grid.length;
        int cn = grid[0].length;
        for (int i = 0; i < rn; i++) {
            for (int j = 0; j < cn; j++) {
                if (grid[i][j] == 1) {
                    return dfs(grid, i, j);
                }
            }
        }
        return 0;
    }

    /**
     深度优先遍历计算岛屿周长
     */
    public int dfs(int[][] grid, int r, int c) {
        if (!inArea(grid, r, c) || grid[r][c] == 0) return 1;
        if (grid[r][c] == 2) return 0;
        grid[r][c] = 2;
        int up = dfs(grid, r - 1, c);
        int down = dfs(grid, r + 1, c);
        int left = dfs(grid, r, c - 1);
        int right = dfs(grid, r, c + 1);
        return up + down + left + right;
    }

    // 判断坐标 （r, c） 是否在 gird 网格范围内
    public boolean inArea(int[][] grid, int r, int c) {
        return 0 <= r && r < grid.length && 0 <= c && c < grid[0].length;
    }
}
