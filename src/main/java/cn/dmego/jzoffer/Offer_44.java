package cn.dmego.jzoffer;

/**
 * 剑指 Offer 44. 连续子数组的最大和
 */
public class Offer_44 {

    // lc 53
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            res = Math.max(dp[i], res);
        }
        return res;
    }
}
