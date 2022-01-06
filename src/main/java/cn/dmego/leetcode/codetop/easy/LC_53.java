package cn.dmego.leetcode.codetop.easy;

/**
 * 最大子数组和
 * @author dmego
 * @date 2022/01/05 11:28
 */
public class LC_53 {

    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int maxSum = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = nums[i];
            if (dp[i - 1] > 0) {
                dp[i] += dp[i - 1];
            }
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }
}
