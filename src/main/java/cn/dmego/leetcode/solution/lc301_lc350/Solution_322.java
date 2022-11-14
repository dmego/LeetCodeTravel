package cn.dmego.leetcode.solution.lc301_lc350;

import java.util.Arrays;

/**
 * 零钱兑换
 * @author dmego
 * @date 2022/04/06 10:57
 */
public class Solution_322 {

    /*
    根据题意可以知道这是一个: 完全背包问题

    dp 数组定义：dp[j] 表示凑成 j 金额使用的最少硬币数是 dp[j]
    递推公式：dp[j] 的来源只有一个 dp[j - arr[i]], 而 dp[j - arr[i]] 表示凑成 j - arr[i] 面值使用的最少货币数,
        那么 dp[j] 只需要在他基础上加上一张就行了，dp[j] = dp[j - arr[i]] + 1; 因为是求使用的最小值，
        所以：dp[j] = min(dp[j], dp[j - arr[i] + 1)
    初始化：当我们要求最小值时，为了让初始值不影响结果，需要将所有值初始化为 MAX_VALUE,
        另外对于 dp[0] 需要初始化为 0。表示凑成 0 金额需要的最小张数为 0
    遍历的顺序：遍历的顺序与标准完全背包一样，因为不是求排列组合问题，先遍历物品还是先遍历背包对结果没有影响
     */
    public int coinChange(int[] coins, int amount) {
        // dp[j] 表示凑成金额 j 需要的最少硬币数
        int[] dp = new int[amount + 1];
        // dp[j] = dp[j - coins[i]] + 1;
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                // 如果 (j - coins[i]) 金额已经求出了最少硬币数，则可以求 dp[j]
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
