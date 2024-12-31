package cn.dmego.leetcode.solution.lc151_lc200;

/**
 * 打家劫舍 1
 * @author dmego
 * @date 2022/04/11 15:03
 */
public class Solution_198 {
    /**
     1. dp 数组定义：dp[i] 表示偷到下标为 i 家(不一定偷)时能偷到的最大金额
     2. 递推公式：按照题意 下标为 i 的房屋有两种选择，偷和不偷
     偷 i  : 那么 i-1 一定没有偷，dp[i] = dp[i - 2] + nums[i]
     不偷i : 那么 dp[i] = dp[i - 1]
     所有：dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1])
     3. 初始化：根据递推公式知道：i 一定要从 2 开始，所以 dp[0] 和 dp[1] 的值要知道
     dp[0] 只有一种选择 dp[0] = nums[0]
     dp[1] = Math.max(nums[0], nums[1])
     4. 遍历顺序，从前往和后偷

     */

    public int rob(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        if (nums.length == 2)
            return Math.max(nums[0], nums[1]);
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }
}
