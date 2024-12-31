package cn.dmego.leetcode.solution.lc51_lc100;

/**
 * [88] 合并两个有序数组
 */
public class Solution_88 {
    /**
     - 因为两个数组都是有序的，所以遍历的时候我们可以从后往前遍历
     - 定义 3 个下标：
        - index = m + n - 1, 表示合并后数组的下标
        - index1 = m - 1, 表示 nums1 遍历的下标
        - index2 = n - 1, 表示 nums2 遍历的下标
     - 比较 nums1[index1] 与 nums2[index2] 的值。将较大的值放到 nums1[index] 位置
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) return;
        int index1 = m - 1, index2 = n - 1;
        int index = m + n - 1;
        while (index1 >= 0 && index2 >= 0) {
            if (nums1[index1] >= nums2[index2]) {
                nums1[index] = nums1[index1];
                index1--;
            } else {
                nums1[index] = nums2[index2];
                index2--;
            }
            index--;
        }
        while (index1 >= 0) {
            nums1[index] = nums1[index1];
            index1--;
            index--;
        }
        while (index2 >= 0) {
            nums1[index] = nums2[index2];
            index2--;
            index--;
        }
    }
}
