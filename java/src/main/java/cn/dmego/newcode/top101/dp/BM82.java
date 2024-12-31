package cn.dmego.newcode.top101.dp;

/**
 * 买卖股票的最佳时机 3
 * @author dmego
 * @date 2022/04/12 15:02
 */
public class BM82 {
    // 最多买卖两次
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len == 0) return 0;
        int[][] dp = new int[len][5];
        dp[0][0] = 0; // 无操作
        dp[0][1] = -prices[0]; // 第一次买入
        dp[0][2] = 0; // 第一次卖出
        dp[0][3] = -prices[0]; // 第二次买入
        dp[0][4] = 0; // 第二次卖出

        for (int i = 1; i < len; i++) {
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][0] - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        return dp[len - 1][4];
    }
}
