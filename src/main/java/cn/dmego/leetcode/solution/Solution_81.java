package cn.dmego.leetcode.solution;

/**
 * 81. 搜索旋转排序数组 II(数组有重复元素)
 * @author dmego
 * @date 2022/01/06 22:28
 */
public class Solution_81 {

    public boolean search(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) return true;
            // 相比与 33 题，如果 left = mid = right 就无法判断哪个区间是有序的了，将left 和 right 各移动一位，继续二分
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
            } else if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] <= nums[right]) {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    /**
     进阶：如果存在 target，返回最小的下标
     */
    public int search2(int[] arr, int target) {
        int n = arr.length;
        if (n == 1) return target == arr[0] ? 0 : -1;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = (right + left) / 2;
            if (arr[left] == target) {
                return left;
            }
            if (arr[mid] == target) {
                while (mid - 1 > 0 && arr[mid - 1] == target) mid--;
                return mid;
            }
            if (arr[mid] == arr[left] && arr[mid] == arr[right]) {
                left++;
                right--;
            } else if (arr[left] <= arr[mid]) {
                if (arr[left] <= target && target < arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (arr[mid] < target && target <= arr[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution_81 s = new Solution_81();
        int search = s.search2(new int[]{21, 21, -21, -20, -17, -8, -6, -2, -2, -1, 0, 2, 3, 4, 4, 6, 11, 13, 14, 16, 17, 18, 20}, 4);
        System.out.println(search);
    }
}
