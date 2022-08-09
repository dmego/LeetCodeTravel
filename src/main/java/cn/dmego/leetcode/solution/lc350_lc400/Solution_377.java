package cn.dmego.leetcode.solution.lc350_lc400;

/**
 * 组合总和 4
 * @author dmego
 * @date 2022/04/06 10:32
 */
public class Solution_377 {

    public int combinationSum4(int[] nums, int target) {
        // dp[j] 表示组成和为 j 的组合数是 dp[j]
        int[] dp = new int[target + 1];
        dp[0] = 1;
        // 排列问题，先遍历背包
        for (int j = 0; j <= target; j++) {
            // 再遍历物品
            for (int i = 0; i < nums.length; i++) {
                if (j - nums[i] >= 0) {
                    dp[j] += dp[j - nums[i]];
                }
            }
        }
        return dp[target];
    }

}
