package cn.dmego.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 */
public class Solution_200 {

    /**
     利用双层循环遍历网格，如果遍历到一块陆地 1 ，就以这块陆地为根坐标，
     找到包含这块陆地的岛屿的全部陆地块，然后都标记上 2
     具体的查找方法就是 DFS 和 BFS
     在树中，DFS 和 BFS 只有两个方向，左右
     在网格结构中 需要遍历到 四个方向 上下左右
     */
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rn = grid.length;
        int cn = grid[0].length;
        int islands = 0;

        for (int i = 0; i < rn; i++) {
            for (int j = 0; j < cn; j++) {
                if (grid[i][j] == '1') {
                    islands += 1;
                    //dfs(grid, i, j);
                    bfs(grid, i, j);
                }
            }
        }
        return islands;
    }


    /**
     深度优先遍历
     */
    public static void dfs(char[][] grid, int r, int c) {
        if (!inArea(grid, r, c)) return;
        if (grid[r][c] != '1') return;
        grid[r][c] = '2';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    /**
     广度优先遍历
     */
    public static void bfs(char[][] grid, int r, int c) {
        Deque<Point> deque = new ArrayDeque<>();
        deque.addLast(new Point(r, c));
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                Point p = deque.pollFirst();
                if (!inArea(grid, p.r, p.c)) continue;
                if (grid[p.r][p.c] == '0' || grid[p.r][p.c] == '2') continue;
                grid[p.r][p.c] = '2';
                deque.addLast(new Point(p.r - 1, p.c));
                deque.addLast(new Point(p.r + 1, p.c));
                deque.addLast(new Point(p.r, p.c - 1));
                deque.addLast(new Point(p.r, p.c + 1));
            }
        }
    }

    static class Point {
        public int r;
        public int c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }


    // 判断坐标 (r, c) 是否在网格 grid 中
    public static boolean inArea(char[][] grid, int r, int c) {
        return 0 <= r && r < grid.length && 0 <= c && c < grid[0].length;
    }


    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        int numIslands = numIslands(grid);
        System.out.println(numIslands);

    }

}
