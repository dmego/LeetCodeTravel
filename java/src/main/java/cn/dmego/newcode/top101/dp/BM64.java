package cn.dmego.newcode.top101.dp;

/**
 * 最小花费爬楼梯
 * @author dmego
 * @date 2022/03/21 09:24
 */
public class BM64 {

    public int minCostClimbingStairs (int[] cost) {
        int n = cost.length;
        if (n == 1) return cost[0];
        // dp[i] 表示爬到第 i 个台阶需要花费的最小费用
        int[] dp = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            // 爬到第 i 个台阶有两种方法: (1) 从 i - 2 台阶往上爬 2 阶; (2) 从 i - 1 台阶往上爬 1 阶
            dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
        }
        return dp[n];
    }
}
