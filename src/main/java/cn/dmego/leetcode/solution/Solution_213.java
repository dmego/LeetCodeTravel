package cn.dmego.leetcode.solution;

/**
 * 打家劫舍 2
 * @author dmego
 * @date 2022/04/11 15:04
 */
public class Solution_213 {

    /**
     我们可以将其转换为打家劫舍的第一题题型，将环形的房屋剪开，根据题意：偷第 1 家，最后一家就不能偷，不偷第一家，最后一家就可以偷，分为两种情况：
     1. 考虑偷第一家，不考虑偷最后一家，那么房屋的范围就变成了 [0, nums.length - 2];
     2. 不考虑头第一家，考虑偷最后一家，那么房屋的范围就变成了 [1, nums.length - 1];
     这两种情况都可以用打家劫舍第一题的方式求出，然后取其中最大的一个就行了。

     1. dp 数组定义：dp[i] 表示偷到下标为 i 房屋时能获得的最大金额
     2. 递推公式：dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1])
     3. 初始化： dp[0] = nums[0], dp[1] = Math.max(dp[0], dp[1]);

     */
    public int rob(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        if (nums.length == 2)
            return Math.max(nums[0], nums[1]);
        int rob1 = simpleRob(nums, 0, nums.length - 2);
        int rob2 = simpleRob(nums, 1, nums.length - 1);
        return Math.max(rob1, rob2);
    }

    public int simpleRob(int[] nums, int start, int end) {
        int[] dp = new int[end - start + 1];
        dp[0] = nums[start];
        dp[1] = Math.max(nums[start], nums[start + 1]);
        // dp 数组
        for (int i = 2; i <= end - start; i++) {
            // 第 i 间房屋的价值是 nums[start + i]
            dp[i] = Math.max(dp[i - 2] + nums[start + i], dp[i - 1]);
        }
        return dp[end - start];
    }
}
