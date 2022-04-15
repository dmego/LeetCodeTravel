package cn.dmego.newcode.top101.dp;

/**
 * 买卖股票最佳时机二
 * @author dmego
 * @date 2022/04/12 15:01
 */
public class BM81 {

    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len == 0) return 0;
        int[][] dp = new int[len][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < len; i++) {
            // 可以买卖多次，买入时，需要把上一次卖出时的利润算上
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[len - 1][1];
    }

}
