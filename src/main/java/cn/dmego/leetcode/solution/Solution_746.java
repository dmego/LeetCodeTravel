package cn.dmego.leetcode.solution;

/**
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 *
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 *
 * 请你计算并返回达到楼梯顶部的最低花费。
 */
public class Solution_746 {
    /**
     理解题意：
     - cost[i] 表示从 第 i 个台阶往上爬需要支付的费用，而支付之后，可以选择往上爬一个台阶，或两个台阶
     - 我们可以选择从下标为 0 的台阶，或者 下标为 1 的台阶开始往上爬
     - cost.length >= 2,

     dp[i] 的含义：
        从第 i 阶爬到楼顶的最小花费（i 从 0 开始）
        因为可以选择从下标 0 或 下标 1 的台阶开始爬楼梯，

     初始化值： 因为台阶最小为 2 阶
        dp[0] = cost[0] （从 0 开始往上爬 2 阶到楼顶，花费 cost[0]）
        dp[1] = cost[1] (从 1 开始往上爬 1 阶到楼顶，花费 cost[1])

     状态转移方程：dp[i] = min(dp[i-1], dp[i-2]) + cost[i]
        解释：min(dp[i - 1], dp[i - 2]) 表示跳到 i 台阶的最小花费，因为调到 i 台阶可以从 i-1开始跳1阶，也可以从 i-2 开始跳2阶
            + cost[i] 表示从 i 台阶开始往上跳需要的花费，不管是跳到 i+1 还是 i+2 都需要 cost[i] 费用

     理解到达顶楼：
        cost[cost.length - 1] 是最后一个台阶，如果从这个台阶往上跳 1 阶就能跳到顶楼
        cost[cost.length - 2] 是倒数第二个台阶，如果从这个台阶往上跳 2 阶就能到顶楼
     所以 跳到顶楼的最小花费是 Math.min(dp[cost.length-1], dp[cost.length-2])

     */
    public static int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i-1], dp[i-2]) + cost[i];
            System.out.print(dp[i] + " ");
        }

        return Math.min(dp[cost.length - 1], dp[cost.length -2]);
    }

    public static int minCostClimbingStairs2(int[] cost) {
        int a = cost[0], b = cost[1];
        for (int i = 2; i < cost.length; i++) {
            if(i % 2 == 0) a = Math.min(a, b) + cost[i];
            else b = Math.min(a, b) + cost[i];
        }
        return Math.min(a, b);
    }


    public static void main(String[] args) {
        int[] cost = new int[]{10,15,20};
        System.out.println(minCostClimbingStairs(cost));
    }

}
