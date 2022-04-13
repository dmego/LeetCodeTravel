package cn.dmego.leetcode.solution;

/**
 * 买卖股票的最佳时机 4
 * @author dmego
 * @date 2022/04/13 10:51
 */
public class Solution_188 {

    public int maxProfit(int k, int[] prices) {
        if (k == 0 || prices.length == 0)
            return 0;
        int[][] dp = new int[prices.length][k * 2 + 1];
        for (int i = 0; i < k * 2; i++) {
            if (i % 2 == 0) {
                dp[0][i] = 0;
            } else {
                dp[0][i] = -prices[0];
            }
        }

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = 0;
            for (int j = 1; j <= k * 2; j++) {
                if (j % 2 == 1) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] - prices[i]);
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + prices[i]);
                }
            }
        }
        return dp[prices.length - 1][k * 2];
    }

}
