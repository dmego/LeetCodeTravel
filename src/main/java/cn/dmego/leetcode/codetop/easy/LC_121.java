package cn.dmego.leetcode.codetop.easy;

/**
 * 买卖股票的最佳时间
 * @author dmego
 * @date 2022/01/05 15:11
 */
public class LC_121 {

    public int maxProfit(int[] prices) {
        int day = 0;
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[day]) {
                max = Math.max(max, prices[i] - prices[day]);
            } else {
                day = i;
            }
        }
        return max;
    }

}
