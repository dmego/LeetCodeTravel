package cn.dmego.leetcode.solution;

import java.util.Arrays;

/**
 * 最长连续递增序列
 * @author dmego
 * @date 2022/04/14 10:06
 */
public class Solution_674 {
    /*
    1. dp 数组定义：dp[i] 表示 nums[0] ~ nums[i] 之间的最长连续递增子序列
    2. 递推公式：if (nums[i - 1] < nums[i]) dp[i] = dp[j] + 1;
    3. 初始化：dp[i] 都初始化为 1
    */
    public int findLengthOfLCIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }
}
