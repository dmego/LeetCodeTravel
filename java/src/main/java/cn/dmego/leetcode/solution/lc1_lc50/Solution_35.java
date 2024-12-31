package cn.dmego.leetcode.solution.lc1_lc50;

/**
 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

 题目翻译：在一个排序数组中找到第一个 大于等于 target 的值的 索引。
 特殊情况：如果 数组 最后一个值 < target 那么 result = nums.length

 */
public class Solution_35 {

    public static int searchInsert(int[] nums, int target) {
        int n = nums.length - 1;
        if (nums[n] < target) return n + 1;
        int low = 0, high = n;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else if (nums[mid] >= target) {
                high = mid;
            }
        }
        return high;

    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3};
        int target = 2;
        int i = searchInsert(nums, target);
        System.out.println(i);

    }

}
