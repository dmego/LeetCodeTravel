package cn.dmego.leetcode.solution.lc401_;

/**
 * 买卖股票的最佳时机含手续费
 * @author dmego
 * @date 2022/04/13 11:20
 */
public class Solution_719 {

    public int maxProfit(int[] prices, int fee) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            // 如果卖出为负数，就不卖出，dp[i - 1][0] + prices[i] - fee < 0 时，取值 0
            dp[i][1] = Math.max(dp[i - 1][1], (Math.max(dp[i - 1][0] + prices[i] - fee, 0)));
        }
        return dp[prices.length - 1][1];
    }

}
