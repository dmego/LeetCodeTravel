package cn.dmego.leetcode;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 *
 * 问总共有多少条不同的路径？
 */
public class Solution_62 {
    /**
     什么是路径：从 (0,0) Start 到 (m, n) Finish 的一整条路线

     确定dp[i][j] 含义：从 (0,0) 到 (i,j) 共有多少条不同路径

     递推公式：dp[i]][j] 怎么求得 ?
        因为从 (i,j) 点出发每次只能往下 (i,j+1) 或者往右 (i+1,j) 移动,
        所以到达(i,j) 点也就只能从 (i-1,j) 或者 (i,j-1) 这两个位置出发
        dp[i][j] = dp[i-1][j] + dp[i][j-1]

     初始化值：dp[i][j] 有哪些值我们是可以确定的？
        dp[0][0] = 1 : 从(0,0) 到 (0,0) 只有一条路径
        dp[0][j] = 1 : 从(0,0) 到 (0,j) 只能往下移动，只有一条路径
        dp[i][0] = 1 : 同理

     遍历的顺序：m x n 是一个二维数组，我们该如何遍历 ？
        首先我们已经确定了 [0][0] ~[m][0]; [0][0] ~ [0][n] 的值
        我们下一个能确定是就是 [1][1] 这个值 dp[1][1] = dp[0][1] + dp[1][0]
        求出dp[1][1] 我们就能继续求出 dp[1][2] = dp[1][1] + dp[0][2]
        。。。。
        所以我们的遍历顺序是从左到右遍历 m x n
            (1,1) ~ (m,n)
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        // 初始化
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int j = 0; j < n; j++) dp[0][j] = 1;

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {

    }
}
