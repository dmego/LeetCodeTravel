package cn.dmego.leetcode.solution.lc1_lc50;

/**
 * [4] 寻找两个正序数组的中位数
 * @author dmego
 * @date 2022/02/24 15:48
 */
public class Solution_4 {

    /**
     * 暴力解法：先合并数组，然后求中位数
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        if (n == 0) return findMedian(nums2);
        else if (m == 0) return findMedian(nums1);
        else {
            // 合并两个数组
            int i = 0, j = 0, k = 0;
            int[] nums = new int[m + n];
            while (i < n && j < m) {
                if (nums1[i] <= nums2[j]) {
                    nums[k] = nums1[i];
                    i++;
                } else {
                    nums[k] = nums2[j];
                    j++;
                }
                k++;
            }
            while (i < n) {
                nums[k] = nums1[i];
                i++;
                k++;
            }
            while (j < m) {
                nums[k] = nums2[j];
                j++;
                k++;
            }
            return findMedian(nums);
        }
    }

    // 计算一个数组的中位数
    public double findMedian(int[] nums) {
        int n = nums.length;
        if (n % 2 == 1) {
            return nums[n / 2];
        } else {
            return (double) (nums[n / 2] + nums[(n / 2) - 1]) / 2;
        }
    }

    public static void main(String[] args) {
        Solution_4 s = new Solution_4();
        int[] nums1 = new int[]{1,2,3,4};
        int[] nums2 = new int[]{5,6,7,8};
        double result = s.findMedianSortedArrays(nums1, nums2);
        System.out.println(result);

    }


}
