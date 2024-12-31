package cn.dmego.leetcode.solution.lc151_lc200;

/**
 * 寻找峰值
 *
 * 峰值元素是指其值严格大于左右相邻值的元素。
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 */
public class Solution_162 {

    /*
      二分查找
      - 因为只需要找到一个峰值，我们可以根据爬山的理念，只要沿着比 mid 元素大的方向继续找，就一定能找到一个峰值
      - 如果 nums[mid] > nums[mid + 1], 峰值一定在 [l, mid] 区间内，让 r = mid, 继续二分
      - 如果 nums[mid] < nums[mid + 1], 峰值一定在 [mid + 1, r] 区间内，让 l = mid + 1，继续二分
      - 最后 l == r，等于找到的一个峰值，返回
     */
    public int findPeakElement(int[] nums) {
        if (nums.length == 1) return 0;
        int l = 0, r = nums.length - 1;
        // 当 判断条件为 l < r 时, 能保证 mid不会取 0 或 num.length - 1, 这样就不会有越界问题
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] < nums[mid + 1]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }


    public static void main(String[] args) {
        int[] nums = {1,3,1,2,1,4,5,3,1,2};
        Solution_162 s = new Solution_162();
        int res = s.findPeakElement(nums);
        System.out.println(res);

    }
}
