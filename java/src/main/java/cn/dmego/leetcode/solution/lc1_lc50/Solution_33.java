package cn.dmego.leetcode.solution.lc1_lc50;

/**
 * 搜索旋转排序数组
 * @author dmego
 * @date 2022/01/06 21:21
 */
public class Solution_33 {

    /**
      解法一: 循环遍历，暴力解法

      解法二：二分查找
        - 因为数组原来是排序的，经过一个点的旋转之后，数组一般分为两半，从拐点区分，两边也都是有序的
        - 将数组进行二分，不管 mid 落在哪边，肯定有一边是有序的
        - 我们判断 [left， mid] 或 [mid, right] 区间是否是有序的，
            - 是则继续判断 target 是否在该有序区间中间，是则继续二分
            - 如果不是，我们更新 left 或 right 的值，继续二分另外一个区间，继续判断哪段区间是有序的
        - 最后我们一定能在有序的区间上找到 target 值，否则返回 -1
     */
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return -1;
        if (n == 1) return nums[0] == target ? 0 : -1;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            // 如果 mid = target 直接返回 mid
            if (nums[mid] == target) return mid;
            // 如果 [left, mid] 区域是有序的
            if (nums[left] < nums[mid]) {
                // 判断 target 是否在区间内
                // 注意是 nums[left] <= target, 区间是闭区间
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                }
                // 如果不在区间内，更新 l 的值，继续二分，找有序区间
                else {
                    left = mid + 1;
                }
            }
            // 如果 [mid, right] 区域是有序的
            else if (nums[mid] <= nums[right]){
                // 判断 target 是否在区间内
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                }
                // 如果不在区间内，更新 r 的值，继续二分，找有序区间
                else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution_33 solution = new Solution_33();
        int[] nums = new int[]{4,5,6,7,0,1,2};
        int search = solution.search(nums, 3);
        System.out.println(search);
    }

}
