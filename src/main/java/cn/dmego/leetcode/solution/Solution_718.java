package cn.dmego.leetcode.solution;

/**
 * 最长重复子数组
 * @author dmego
 * @date 2022/04/14 10:09
 */
public class Solution_718 {

    /**
    子数组其实就是连续的子序列
    1. dp 数组的定义：dp[i][j] 表示 以nums1[i - 1] 与 以 nums2[j - 1] 结尾的最长子数组的长度
        为什么是 0 ~ i - 1，而不能直接表示 0 ~ i 之间的，因为 dp[i][j] 只能从 dp[i - 1][j - 1]
        推导出来，如果 表示 0 ~ i 之间，那么就需要把 dp[i][0] 和 dp[0][j] 都先求出来，但是如果我们表示 0 ~ i-1 之间，我们
        就可以直接初始化 dp[i][0] 和 dp[0][j] 因为这没有一样，没有-1 的下标，直接初始化为 0
    2. 递推公式：
        如果 nums[i - 1] = nums[j - 1] 那么 dp[i][j] = dp[i - 1][j - 1] + 1;
    3. 初始化：dp[0][j] dp[i][0] 都初始化为 0 即可
    注意 dp 数组的定义：是以某个下标结尾的最长子数组长度，而不是整个字符串，求最值，找到 dp[i][j] 中最大的值即可
     */
    public int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        int res = 0;
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }

}
