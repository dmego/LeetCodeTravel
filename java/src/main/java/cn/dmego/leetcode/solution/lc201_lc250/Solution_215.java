package cn.dmego.leetcode.solution.lc201_lc250;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 数组中第 K 个最大的元素
 * @author dmego
 * @date 2021/09/22 09:07
 */
public class Solution_215 {

    // 解法一：使用 sort 函数，排序
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - 1 - k];
    }


    // 解法二：使用快排先将数组进行排序
    public int findKthLargest2(int[] nums, int k) {

        return 0;
    }

    public static void main(String[] args) {

       //[3,2,3,1,2,4,5,5,6], k = 4
        int[] nums = {3,2,3,1,2,4,5,5,6};
        int k = 4;
        Solution_215 s = new Solution_215();
        int res = s.findKthLargest(nums, k);
        System.out.println(res);
    }



}
