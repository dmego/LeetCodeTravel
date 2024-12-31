package cn.dmego.leetcode.solution.lc101_lc150;

/**
 * 买卖股票的最佳时机
 * @author dmego
 * @date 2022/04/12 14:23
 */
public class Solution_121 {

    /**
     贪心法：找出最左边的最小值和最右边的最大值
     在最小的时候买入，最大的时候卖出，获得的利润最大
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int min = prices[0];
        int maxPrice = 0;
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            maxPrice = Math.max(maxPrice, prices[i] - min);
        }
        return maxPrice;
    }


    /**
     动态规划解法：
     1. dp 数组定义：
         dp[i][0] 表示在当天持有股票的最大收益
         dp[i][1] 表示在当天不持有股票的最大收益
     2. 递推公式：
        对于 dp[i][0]，当天"持有"有两种情况:
             i - 1 天就持有了，今天继续，dp[i][0] = dp[i - 1][0]
             当天买入股票(注意不能加上 i - 1 天不持有时的收益，只能买卖一次)：dp[i][0] = -prices[i]
             取两种情况的最大值：dp[i][0] = Math.max(dp[i - 1][0], -prices[i])
         对于 dp[i][1], 当天"不持有"股票也有两种情况：
         i - 1 天就不持有了，dp[i][1] = dp[i - 1][1]
         当天卖出股票，dp[i][1] = dp[i][0] + prices[i]
         取两种情况的最大值：dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i])
     3. 初始化：
         dp[0][0] = -prices[0]
         dp[0][1] = 0;
     4. 遍历顺序：从前往后
     最后结果返回 dp[prices.length - 1][1] 也就是在最后一天不持有股票的最大收益
     */
    public int maxProfit2(int[] prices) {
        if (prices.length == 0) return 0;
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for(int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[prices.length - 1][1];
    }


}
