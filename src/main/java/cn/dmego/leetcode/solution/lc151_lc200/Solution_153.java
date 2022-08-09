package cn.dmego.leetcode.solution.lc151_lc200;

/**
 * 寻找旋转排序数组中的最小值
 * @author dmego
 * @date 2022/01/07 09:12
 */
public class Solution_153 {

    /**
     如何理解旋转：数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2],..., a[n-2]]
        也就是把数组最后一个元素移动到第一位
     数组是升序排序的，未旋转前，最小值是 nums[0], 旋转之后，最小值一定在旋转点上。
     所以求最小值也就是求旋转点

     二分查找会将区间分为三部分 left mid right
     1. 如果 mid > right 最小值一定在右区间 left = mid + 1
     2. 如果 mid < right 最小值一定在左区间 right = mid

     */
    public int findMin(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int left = 0, right = n - 1;
        // 最后 left = right 都是最小值
        while (left < right) {
            int mid = (right + left) / 2;
            if (nums[mid] > nums[right]) {
                // mid > right mid 不可能是最小值，left = mid + 1
                left = mid + 1;
            } else {
                // mid < right mid 有可能就是 最小值，所有右区间要等于 mid
                right = mid;
            }
        }
        return nums[left];
    }

    // 33 题的思路
    public int findMin2(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int min = nums[0];
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = (right + left) / 2;
            if (nums[left] <= nums[mid]) {
                min = Math.min(min, nums[left]);
                left = mid + 1;
            } else {
                min = Math.min(min, nums[mid]);
                right = mid - 1;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        Solution_153 solution = new Solution_153();
        int min = solution.findMin(new int[]{4, 5, 6, 7, 1, 2, 3});
        System.out.println(min);
    }
}
