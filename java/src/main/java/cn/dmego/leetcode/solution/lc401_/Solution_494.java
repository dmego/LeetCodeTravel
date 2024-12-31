package cn.dmego.leetcode.solution.lc401_;

/**
 * 目标和
 * @author dmego
 * @date 2022/03/28 9:41 AM
 */
public class Solution_494 {

    /**
     理解题意：给定数组 nums，和一个目标数 target, 求 nums中的元素组合 + - 符合后等于 target 的种数

     使用动态规划来解决：
     将nums中的元素根据组合的 + - 不同分为两组，则有 left - right = target, 如果将 - 改成 +
     则有 left + right = sum, 根据这两个公式，可以得到：left = (sum + target) / 2;
     所以，将问题转换为：nums 中有多少种和为 left 的子集，可以转换为 0-1 背包问题

     动态规划五步：
     1. 确定 dp 数组定义：dp[j] 表示容量为 j 的背包 装满 有 dp[j] 种方法
     2. 递推公式：j - nums[i] 容量的背包 装满 有 dp[j - nums[i]] 种方法
     如果我们一一列举，就会有如下的递推公式：dp[j] = dp[j] + dp[j - nums[i]]
     3. 初始化：dp[0] = 1, 表示容量为 0 的背包有 1 种方法填满那就是装 0 个物品
     4. 遍历顺序：按照 0-1 背包滚动数组来遍历

     注意点：
     (sum + target) % 2 == 1 时，没有结果
     当 target > sum 时，也没有结果

     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum < Math.abs(target)) return 0;
        if ((sum + target) % 2 == 1) return 0;
        int left = (sum + target) / 2;

        int[] dp = new int[left + 1];
        dp[0] = 1;

        for (int i = 0; i < nums.length; i++) {
            for (int j = left; j >= 0; j--) {
                if (j - nums[i] >= 0) {
                    dp[j] += dp[j - nums[i]];
                }
            }
        }
        return dp[left];
    }

}
