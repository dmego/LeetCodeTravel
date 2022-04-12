package cn.dmego.leetcode.solution;

/**
 * 买卖股票的最佳时机 2
 * @author dmego
 * @date 2022/04/12 15:01
 */
public class Solution_122 {

    /**
     与第一题的的不同之处在于，本题的股票买卖可以买卖多次，第一题是股票只能买卖一次, 相同的是任何时候也只能同时持有一只股票。
     1. dp 数组定义：
         dp[i][0] 表达第 i 天持有股票的最大收益
         dp[i][1] 表示第 i 天不持有股票的最大收益
     2. 递推公式：
        dp[i][0] 持有股票的两种情况：
             i-1 天就持有了，dp[i][0] = dp[i - 1][0]
             i-1 天不持有了，因为股票可以买卖多次，i 天可以再次持有, dp[i][0] = dp[i - 1][1] - prices[i]
        dp[i][1] 不持有股票的两种情况：
             i-1 天就不持有了，dp[i][1] = dp[i - 1][1]
             i-1天还持有，i 天卖出，dp[i][1] = dp[i - 1][0] + prices[i]
     3. 初始化和遍历的顺序和第一题一样
     */
    public int maxProfit (int[] prices) {
        if (prices.length == 0) return 0;
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[prices.length - 1][1];
    }

}
