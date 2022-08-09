package cn.dmego.leetcode.solution.lc301_lc350;

/**
 * 最佳买卖股票时机含冷冻期
 * @author dmego
 * @date 2022/04/13 10:53
 */
public class Solution_309 {

    /*
      卖出股票后，有一天的冻结期
      dp[i][0] 当前持有股票：
        i-1天就持有，dp[i][0] = dp[i - 1][0]
        i-1天卖出的，i 天买入，dp[i][0] = dp[i - 2][1] - prices[i];
        i-2天卖出的，i 天买入，dp[i][0] = dp[i - 1][1] - prices[i];
          dp[i][1] 当天不持有股票
        i-1天就不持有 dp[i][1] = dp[i - 1][1]
        i-1天持有，i天卖出，dp[i][1] = dp[i - 1][0] + prices[i]
    */
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        boolean sell = false;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], (sell ? dp[i - 2][1] : dp[i - 1][1]) - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
            if (dp[i][1] > dp[i - 1][1]) {
                sell = true;
            } else {
                sell = false;
            }
        }
        return dp[prices.length - 1][1];
    }
}
