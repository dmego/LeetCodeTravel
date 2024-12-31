package cn.dmego.jzoffer;

/**
 * 剑指 Offer 39. 数组中出现次数超过一半的数字
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 */
public class Offer_39 {

    // 同 LC 169
    public int majorityElement(int[] nums) {
        // 候选值
        int res = nums[0];
        // 每个候选值的得票数
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            // 当遍历到数字与候选值不同时
            if (nums[i] != res) {
                // 如果候选值的得票数已经为 0 了，则换一个候选值
                if (count == 0) {
                    res = nums[i];
                    count = 1;
                }
                // 否则将候选值的得票 - 1
                count--;
            }
            // 当遍历到数字与候选值相同时，候选值的得票数 + 1
            else {
                count++;
            }
        }
        return res;
    }
}
