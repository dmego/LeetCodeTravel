package cn.dmego.newcode.top101.dp;

import java.util.Arrays;

/**
 * 最长递增子序列 (一)
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7]
 * 的子序列。
 * @author dmego
 * @date 2022/04/14 11:25
 */
public class BM71 {
    /**
     dp[i] 表示长度为 i 的数组的最长递增子序列
     当 nums[i] > nums[j]时，dp[i] = Math.max(dp[i], dp[j] + 1) 0 <= j < i;
     最后结果返回 dp[i] 的最大值
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    res = Math.max(res, dp[i]);
                }
            }
        }
        return res;
    }

}
