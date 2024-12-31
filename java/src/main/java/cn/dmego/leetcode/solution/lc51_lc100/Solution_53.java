package cn.dmego.leetcode.solution.lc51_lc100;

import java.util.Arrays;

/**
 * 最大连续子数组和
 * @author dmego
 * @date 2021/09/23 12:42
 */
public class Solution_53 {

    /**
     动态规划解法：
     定义状态(子问题)：dp[i] 表示以 nums[i] 结尾的 连续子数组的最大和
     初始值：dp[0] = nums[0] 第一个数，只有它自己，最大和也就是他自己
     状态转移方程(子问题之间的联系)：
     假设已经求出 nums[i-1] 的连续子数组的最大和dp[i-1]，那么nums[i] 的连续子数组的最大和就能求出来了 dp[i] + nums[i]
     这是在数组元素都大于0的情况，当数组中存在负数，那么情况就是这样：
     1. 当 dp[i - 1] > 0, dp [i] = dp[i - 1] + nums[i];
     2. 当 dp [i -1] <= 0时，此时 dp[i] 如果还接在 dp[i-1] 后面，最大子序和也不会比他本身大，所以 dp[i] = nums[i];
     题目求最大子序和，所以我们需要遍历 dp 数组，找出最大的一个值。

     空间优化:
     使用一个 pre 变量来表示 dp[i - 1] 的值
     使用一个 res 变量来表示最终结果值
     初始时，pre = nums[0]，res = nums[0];
     从 1 开始遍历数组
        1. 第一步先更新 pre 的值(再本轮循环中，pre当前值是 dp[i - 1]，更新后的值是 dp[i])
            pre = Math.max(nums[i], pre + nums[i]);
        2. 更新 res 结果值： res = Math.max(res, pre);
     */
    public int maxSubArray(int[] nums) {
        int pre = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            pre = Math.max(nums[i], pre + nums[i]);
            res = Math.max(res, pre);
        }
        return res;
    }

    /**
     变种：求最大连续子数组的开始和结束下标-------》返回最大连续子数组
     */
    public static int[] maxSubArray2(int[] nums) {
        int pre = nums[0];
        int start = 0, end = 0;
        int res = pre;
        for (int i = 1; i < nums.length; i++) {
            int oldPre = pre;
            if (pre > 0) {
                pre = pre + nums[i];
                if (pre > oldPre) {
                    end = i;
                    res = pre;
                }
            } else {
                pre = nums[i];
                if (pre > oldPre) {
                    start = end = i;
                    res = pre;
                }
            }
        }
        int[] result = new int[end - start + 1];
        System.arraycopy(nums, start, result, 0, end - start + 1);
        System.out.println("==== res:" + res);
        return result;
    }


    public static void main(String[] args) {
        int [] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int[] max = maxSubArray2(nums);
        int res = 0;
        for (int i = 0; i < max.length; i++) {
            res = res + max[i];
        }
        System.out.println(Arrays.toString(max));
        System.out.println("===res: "+ res);
    }
}
