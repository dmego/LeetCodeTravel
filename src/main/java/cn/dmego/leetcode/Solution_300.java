package cn.dmego.leetcode;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7]
 * 的子序列。
 */
public class Solution_300 {

    /**
     注意: 必须是严格递增，相等不属于严格递增
     状态定义：dp[i] 表示 nums 前 i 个数字的最长子序列长度
     初始状态：dp[i] 初始值都是 1，每个数字自己单独也是一个长度为 1 的子序列
     转移方程：在循环(i<nums.length)下，每次计算dp[i] 时，都需要遍历 nums 数组的[0, i) 区间：
     1. 当 nums[i] > nums[j] 时，说明 nums[i] 可以接在 nums[j] 后面，此时 dp[i] = max(dp[i], dp[j]+1);
     2. 当 nums[i] <= nums[j] 时，不满足接在 nums[j] 后面，不更新 dp[j]
     经过上面的计算，dp 数组就存储了以 nums 每个数字结尾的最长子序列长度，遍历 dp 数组，返回最大值即可
     转移方程为：dp[i] = max (dp[i], dp[j]+1) for j in [0, i)
     复杂度分析：时间复杂度 O(N^2) 空间复杂度 O(N)
     */
    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return -1;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }


    public static void main(String[] args) {

    }
}
