package cn.dmego.newcode.top101.backtracking;

/**
 * 岛屿数量
 * @author dmego
 * @date 2022/03/14 09:46
 */
public class BM57 {

    public int solve (char[][] grid) {
        // write code here
        int result = 0;
        if (grid == null || grid.length == 0) return result;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 如果遍历到一块岛屿
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    result++;
                }
            }
        }
        return result;
    }

    public void dfs(char[][] grid, int c, int r) {
        if (!inArea(grid, c, r)) return;
        if (grid[c][r] == '0' || grid[c][r] == '2') return;
        grid[c][r] = '2';
        dfs(grid, c - 1, r);
        dfs(grid, c + 1, r);
        dfs(grid, c, r + 1);
        dfs(grid, c, r - 1);
    }

    // 判断是否在区域内
    public boolean inArea(char[][] grid, int c, int r) {
        return 0 <= c && c < grid.length && 0 <= r && r < grid[0].length;
    }

}
