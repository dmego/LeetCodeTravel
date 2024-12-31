package cn.dmego.leetcode.solution.lc201_lc250;

import java.util.Arrays;

/**
 * 存在重复元素
 * 给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。
 * @author dmego
 * @date 2022/03/15 19:01
 */
public class Solution_217 {

    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) return true;
        }
        return false;
    }

}
