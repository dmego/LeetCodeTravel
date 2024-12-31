package cn.dmego.leetcode.solution.lc401_;

/**
 * 零钱兑换 2
 * 凑成面值为 amount 的组合数是多少
 * @author dmego
 * @date 2022/04/03 12:02 PM
 */
public class Solution_518 {

    /**
     转化为完全背包问题：
     背包容量 amount, 物品 coins, 重量和价值 coins[i], 求组合数
     dp 数组定义：dp[j] 表示凑成总金额为 j 的货币组合数为 dp[j]
     递推公式：dp[j] += dp[j - coins[i]] -->(求装满背包有几种方法，一般公式都是：dp[j] += dp[j - nums[i]];)
     初始化值：dp[0] = 1
     遍历顺序：为了保证是求组合，对顺序没有要求，一定要先遍历物品，保证物品遍历在第一层
     如果物品在第二层，就会出现物品有序的情况
     */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        // 先遍历物品
        for (int i = 0; i < coins.length; i++) {
            // 再遍历背包
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }

}
