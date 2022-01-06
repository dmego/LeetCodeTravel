package cn.dmego.leetcode.codetop.medium;

import java.util.Arrays;

/**
 * 数组中第K个最大元素
 * 注意：是包含重复元素的
 * @author dmego
 * @date 2021/12/31 17:39
 */
public class LC_215 {
    public static int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,3,1,2,4,5,5,6};
        int k = 4;
        int kthLargest = findKthLargest(nums, k);

        System.out.println(kthLargest);
    }
}
