package cn.dmego.newcode.top101.dp;

/**
 * 打家劫舍 2
 * @author dmego
 * @date 2022/04/11 15:22
 */
public class BM79 {

    public int rob (int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        // 将环形分为两半，考虑两种情况
        // 情况1：考虑偷第一家，不考虑偷最后一家
        int rob1 = rangeRob(nums, 0, nums.length - 2);
        // 情况2：不考虑偷第一家，考虑偷最后一家
        int rob2 = rangeRob(nums, 1, nums.length - 1);
        return Math.max(rob1, rob2);
    }

    public int rangeRob(int[] nums, int start, int end) {
        int[] dp = new int[end - start + 1];
        dp[0] = nums[start];
        dp[1] = nums[start + 1];
        for (int i = 2; i <= end - start; i++) {
            // nums[start + i] 表示第 i 家的价值
            dp[i] = Math.max(dp[i - 2] + nums[start + i], dp[i - 1]);
        }
        return dp[end - start];
    }
}
