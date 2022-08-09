package cn.dmego.leetcode.solution.lc301_lc350;

import java.util.Arrays;

/**
 * 零钱兑换
 * @author dmego
 * @date 2022/04/06 10:57
 */
public class Solution_322 {

    public int coinChange(int[] coins, int amount) {
        // dp[j] 表示凑成金额 j 需要的最少硬币数
        int[] dp = new int[amount + 1];
        // dp[j] = dp[j - coins[i]] + 1;
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                if (dp[j - coins[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        Solution_322 s = new Solution_322();
        int change = s.coinChange(new int[]{2}, 3);
        System.out.println(change);
    }

}
