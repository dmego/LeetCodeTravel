package cn.dmego.leetcode.solution;

/**
 * 搜索旋转排序数组
 * @author dmego
 * @date 2022/01/06 21:21
 */
public class Solution_33 {

    /**
      解法一: 循环遍历，暴力解法
      解法二：二分查找
        因为数组原来是排序的，经过一个点的旋转之后，数组基本还是有序的
        将数组一分为二，肯定有一边是有序的，此时判断 target 是否在有序的子数组的中间
        如果是 则继续二分查找该区间，如果不是，就查找另外一个区间
     */
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return -1;
        if (n == 1) return nums[0] == target ? 0 : -1;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = (right - left + 1) / 2 + left;
            //int mid = (left + right) / 2;
            if (nums[mid] == target) return mid;
            // 如果 [left, mid] 区域是有序的
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // 如果 [mid, right] 区域是有序的
            else if (nums[mid] <= nums[right]){
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
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
