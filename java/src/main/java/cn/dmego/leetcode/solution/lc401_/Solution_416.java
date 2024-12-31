package cn.dmego.leetcode.solution.lc401_;

/**
 * 分割等和子集
 * @author dmego
 * @date 2022/03/25 17:59
 */
public class Solution_416 {

    /*
     理解题意：
        参数：一个只包含 正整数 的非空数组；求：这个是否存在两个子集，满足两个子集的元素和相等
        假设数组元素总和为 sum, 也就是说是否存在元素和等于 sum/2 的子集

    我们将其抽象为 0-1 背包问题：
        背包容量：sum/2
        物品总数：nums.length;
        物品的重量和价值：nums[i]

    dp 数组定义：dp[j] 表示容量为 j 的背包能装的最大物品价值(最大子集和),
        和题意的关系：当 dp[sum/2] = sum/2 时，说明存在这样的子集满足子集和等于 sum/2

    递推公式：dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i])
    初始化：
    遍历顺序：0-1背包滚动数组的顺序

    */
    public boolean canPartition(int[] nums) {
        if (nums.length == 1) return false;
        // 先求容量
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        // 如果和是奇数，一定不会存在
        if (sum % 2 == 1) return false;
        int m = sum/2;
        int[] dp = new int[m + 1];
        // 遍历物品
        for (int i = 0; i < nums.length; i++) {
            // 遍历背包容量
            for (int j = m; j >= 0; j--) {
                if (j - nums[i] >= 0) {
                    dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
                }
            }
        }
        return dp[m] == m;
    }

}
