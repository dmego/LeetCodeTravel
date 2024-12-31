package cn.dmego.leetcode.codetop.medium;

import java.util.Arrays;

/**
 * 最长递增子序列
 * @author dmego
 * @date 2022/01/11 10:14
 */
public class LC_300 {

    /**
     注意：子序列不是子数组，不需要连续

     dp[i] 表示前 i 个元素的最长递增子序列
     dp[i] 默认初始化都为 1， 元素本身就是一个子序列
     如何确定 dp[i]: dp[i] 表示前 i 个元素的最长递增子序列，所以它需要和 前 i 个元素都比较
        for j = 0; j < i; j++ {
            // 如果当前元素 nums[i] > nums[j] 位置上的元素，那么
            if (nums[i] > nums[j]) {
                dp[i] = Math.max(dp[i], dp[j]+1)
             }
        }
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 1) return 1;
        int[] dp = new int[nums.length];
        // dp 数组以 1 填充
        Arrays.fill(dp, 1);
        int maxLen = dp[0];
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }


}
