package cn.dmego.leetcode;

import java.util.Arrays;

/**
 * @author dmego
 * @date 2021/12/14 16:58
 */
public class Solution_189 {

    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int[] tmp = new int[n];
        System.arraycopy(nums, 0, tmp, 0, n);
        int i = 0, j = n - k;
        while (i < n) {
            if (j < n) {
                nums[i] = tmp[j];
                i++;
                j++;
            } else {
                j = 0;
            }
        }
    }

    /**
     nums = 1 2 3 4 5 6 7; k = 3
     nums 反转： 7，6，5，4，3，2，1
     nums 0~k 反转：5，6，7，4，3，2，1
     nums k+1 ~ n 反转：5，6，7，1，2，3，4 得到结果
     */
    public static void rotate2(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    /**
     * 反转 nums 数组的 [start, end] 区间
     * 双指针，start end 交换位置
     */
    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[end];
            nums[end] = nums[start];
            nums[start] = tmp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4};
        int k = 2;
        rotate2(nums, k);
        System.out.println(Arrays.toString(nums));
    }
}
