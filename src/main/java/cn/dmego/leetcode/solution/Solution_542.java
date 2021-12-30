package cn.dmego.leetcode.solution;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author dmego
 * @date 2021/12/22 20:58
 */
public class Solution_542 {
    /**
     多源 BFS: 求 每个 1 到最近一个 0 的距离 --> 转换为 0 到 每一个 1 的最近距离
     为什么要转换: 由 0 去找 1，在找的过程中计算距离；如果是 1 找 0， 找到后，再回去更新 1 的 值就比较麻烦了
     初始化时，把 0 入队列，把所有 1 标记为 未被访问过 -1
     */
    public int[][] updateMatrix(int[][] mat) {
        Deque<int[]> deque = new ArrayDeque<>();
        int[][] direction = new int[][] {{-1,0},{1,0},{0,-1},{0,1}};
        int m = mat.length;
        int n = mat[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    // 0 入队列
                    deque.addLast(new int[]{i, j});
                } else if (mat[i][j] == 1){
                    // 在原数组基础上，将所有 1 初始化为未访问状态 -1
                    mat[i][j] = -1;
                }
            }
        }

        int step = 0; // 0 ~ 1 的最小距离 (例比994: 腐烂橘子感染最近一个新鲜橘子的时间)
        while (!deque.isEmpty()) {
            step++; // 最小距离也相当于 BFS 的层数
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                int[] node = deque.pollFirst();
                for (int[] direct : direction) {
                    int x = node[0] + direct[0];
                    int y = node[1] + direct[1];
                    // 如果在矩阵内，并且是未标识的 1 （-1）
                    if (inArea(mat, x, y) && mat[x][y] == -1) {
                        // 这个未标识 1 到 最近 0 的距离，也就是 0 到 这个 1 的最近距离，也就是当前遍历的层数
                        // 在原数组基础上进行修改，mat[x][y] 原值 -1， 改完 step，也就是距离最近0的距离
                        mat[x][y] = step;
                        // 将这个已经标识的 1 放入队列(类比994：将被感染的橘子放入队列)
                        // 也就是下一层需要遍历的节点
                        deque.addLast(new int[]{x, y});
                    }
                }
            }
        }

        return mat;
    }

    public boolean inArea(int[][] mat, int r, int c) {
        return 0 <= r && r < mat.length && 0 <= c && c < mat[0].length;
    }
}
