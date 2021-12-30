package cn.dmego.leetcode.solution;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 在给定的网格中，每个单元格可以有以下三个值之一：
 *
 *
 * 值0代表空单元格；
 * 值1代表新鲜橘子；
 * 值2代表腐烂的橘子。
 *
 *
 * 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。
 *
 * 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回-1。
 */
public class Solution_994 {
    /**
     要求：求单元格中没有新鲜橘子为止，经过的最小分钟数. 如果最后会存在新鲜橘子 则返回 -1
     思考的问题：
        1.新鲜橘子如何变成腐烂橘子
        2.最小分钟数如何统计和理解
        3.怎么确定网格中没有新鲜橘子了

     1. 腐烂橘子 -->  感染上下左右 4 个方向的新鲜橘子(感染时间 1 分钟) ---> 新鲜橘子变 腐烂橘子
     2. 初始化时，腐烂橘子数量不一定是 1 个，所以不能将每个腐烂橘子分开来：进行一次感染时，需要把全部能感染的新鲜橘子都感染了
     3. 先统计出所有的新鲜橘子数量，执行第 2 步 感染新鲜橘子时，新鲜橘子数--， 如果最后新鲜橘子数 > 0 return -1

     思路：
        1. 采用 BFS 层序遍历，定义一个队列，初始化放入所有的已感染橘子，每次感染同一层(队列中的)的能感染的(上下左右)新鲜橘子，
            然后再把这一层被感染的橘子加到队列，作为下一次的感染源
        2. 初始化时， 双层 for 循环计算出新鲜橘子数，如果是腐烂橘子，加入到队列
        3. 每次感染新鲜橘子时，新鲜橘子数 -1，如果BFS最后还剩下新鲜橘子，返回 -1
        4. 感染时间就是 BFS 的层数
        注意：感染时还要注意，不能超过网格边界
     */

    public int orangesRotting(int[][] grid) {
        int count = 0; // 新鲜橘子数量
        Deque<int[]> deque = new ArrayDeque<>(); // int[] 表示腐烂橘子的坐标(r, c)
        int m = grid.length;
        int n = grid[0].length;
        int bound = 0; // 最小分钟数（BFS层数）
        int[][] direction = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};

        // 初始化求新鲜橘子数；腐烂橘子入队列
        for (int i = 0;i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    count++;
                } else if (grid[i][j] == 2) {
                    deque.addLast(new int[]{i, j});
                }
            }
        }

        // BFS 遍历---> 为什么需要 count > 0 的条件：当新鲜橘子都感染完之后，如果继续遍历(因为队列中还会存在值)，就会重新计数
        while (count > 0 && !deque.isEmpty()) {
            bound++; // 分钟数 + 1
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                int[] node = deque.pollFirst();
                for (int[] direct : direction) {
                    // 需要感染的橘子坐标 （x,y）
                    int x = node[0] + direct[0];
                    int y = node[1] + direct[1];
                    // 如果在网格内
                    if (inArea(grid, x, y)) {
                        // 如果是新鲜橘子
                        if (grid[x][y] == 1) {
                            // 感染他
                            grid[x][y] = 2;
                            // 新鲜橘子 -1
                            count--;
                            // 将感染橘子 add 队列，作为下一轮感染的感染源
                            deque.addLast(new int[]{x, y});
                        }
                    }
                }
            }
        }
        // 如果还剩下新鲜橘子(count > 0) 返回 -1， 否则返回分钟数 bound
        return count > 0 ? -1 : bound;
    }

    // 判断 (r, c) 点是否在 grid 网格内
    public static boolean inArea(int[][] grid, int r, int c) {
        return 0 <= r && 0 < grid.length && 0 <= c && c < grid[0].length;
    }


}
