package cn.dmego.leetcode.solution.lc401_;

import java.util.Arrays;

/**
 * 找到数组的中间位置
 *
 */
public class Solution_1991 {

    public int findMiddleIndex(int[] nums) {
        int sumLeft = 0; // 表示中间位置 i 左边之和, 初始化为 0
        int sumRight = Arrays.stream(nums).sum(); // 表示中间位置 i 的右边元素之和，初始化为数组所有元素和
        for (int i = 0; i < nums.length; i++) {
        //    sum
        }
        return 0;
    }

}
