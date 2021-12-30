package cn.dmego.leetcode.solution;

/**
 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。

 说明: 为什么返回数值是整数，但输出的答案是数组呢?
    请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 */
public class Solution_26 {

    /**

     读懂题意：
        1. 数组是有序的 --> 也就说我们不用担心数组中重复的元素分布在各个下标下。重复的元素一定是连续的
        2. 原地删除 --> 我们不能利用外部空间，也就是不能使用暴力方法来解决这道题 --> 通常我们采用双指针来解题
        3. 使每个元素 只出现一次 --> 新数组有序且没有重复元素

     解题思路：
        0. 当数组元素 <= 1 时，数组中不可能存在重复元素，直接返回原数组即可
        1. 我们采用双指针法来解决该问题
            1.1 定义一个慢 slow 指针，开始指向有序数组的第 2 个元素 nums[slow] = nums[1], 其表示下一个待加入结果数组的元素
            则结果数组的最后一个元素就是nums[slow - 1] --> 最后返回的新数组长度也就是 slow;
            1.2 定义一个快 fast 指针，开始指向有序数组的第2个元素，其表示正在处理的有序数组的元素的下标
        2. 我们的思路就是利用 fast 指针遍历有序数组
            2.1 如果 nums[fast] != nums[slow -1], 说明 nums[fast] 不是重复元素，
            此时我们就让 nums[slow] == nums[fast]; slow++, fast++
            2.2 如果 nums[fast] == nums[slow -1], 说明 nums[fast] 是重复元素，继续移动 fast 指针即可
        3. 最后如果 fast == nums.length 说明已经遍历结束，新数组的长度就是 slow;

     */
    public static int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return len;
        }
        int fast = 1, slow = 1;
        while (fast < len) {
            if (nums[fast] != nums[slow - 1]) {
               nums[slow] = nums[fast];
               slow++;
            }
            fast++;
        }
        return slow;
    }
}
