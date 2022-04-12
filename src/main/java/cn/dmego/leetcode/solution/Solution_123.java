package cn.dmego.leetcode.solution;

/**
 * 买卖股票的最佳时机 3
 * @author dmego
 * @date 2022/04/12 15:36
 */
public class Solution_123 {

    /**
     本题和前两题的区别在于：股票最多只能买卖 两次 ，也就是说可以不买卖，买卖一次或者买卖两次
     这样的话，对于一天，可以有 5 种买卖的状态：
        1. 什么也不操作
        2. 第一次的持有
        3. 第一次的不持有
        4. 第二次的持有
        5. 第二次的不持有
     但是每次只能是其中的一种状态

     1. dp 数组的定义：dp[i][j] 表示在第 i 天 j 状态下的最大收益，其中 j 取值 0~4，共 5 种状态
     2. 递推公式：其实这里的递推公式和第一题是基本类似的了
        dp[i][0] = dp[i - 1][0]：什么也不操作的话，等于 i-1 天什么也不操作时候的值
        dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
        dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
        dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
     3. 初始化：
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0; 当天买入当天卖出 收益是 0
        dp[0][3] = -prices[0]; 第二次持有前，第一次的肯定已经卖出了
        dp[0][4] = 0;
     4. 遍历顺序从前往后

     */
    public int maxProfit (int[] prices) {
        if (prices.length == 0) return 0;
        int[][] dp = new int[prices.length][5];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        dp[0][3] = -prices[0];
        dp[0][4] = 0;

        for(int i = 1; i < prices.length; i++) {
            dp[i][0] = dp[i - 1][0];
            // 第一次持有
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            // 第一次不持有
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            // 第二次持有：(1) i-1天就持有了，(2) i 天第二次买入, i-1天就要第一次卖出
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        return dp[prices.length - 1][4];
    }
}
