package cn.dmego.leetcode.solution.lc401_;
/**
 * 寻找数组的中心下标
    给你一个整数数组 nums ，请计算数组的 中心下标 。
    数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
    如果中心下标位于数组最左端，那么左侧数之和视为 0 ，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。
    如果数组有多个中心下标，应该返回 最靠近左边 的那一个。如果数组不存在中心下标，返回 -1
 */
public class Solution_724 {

    /*
        数组元素总和：P[nums.length - 1]
        i 下标的左边之和为 P[i - 1] = nums[0] + nums[1] + ... + nums[i - 1]
        i 下标的右边之和为(总和 - i 下标左边之和 - i 下标的值): p[nums.length - 1] - p[i - 1] - nums[i];
    */
    public int pivotIndex(int[] nums) {
        int[] P = new int[nums.length];
        P[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            P[i] = P[i - 1] + nums[i];
        }
        if (P[nums.length - 1] - P[0] == 0) return 0;
        for (int i = 1; i < nums.length; i++) {
            if (P[i - 1] == P[nums.length - 1] - P[i - 1] - nums[i]) {
                return i;
            }
        }
        return -1;
    }

    // 题解二：见 Solution 1991

}