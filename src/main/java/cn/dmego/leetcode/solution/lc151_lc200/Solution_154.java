package cn.dmego.leetcode.solution.lc151_lc200;

/**
 * 寻找旋转排序数组中的最小值 II (数组元素会重复)
 * @author dmego
 * @date 2022/01/07 10:29
 */
public class Solution_154 {

    /**
      相比于 153 题， 需要考虑重复元素
      如何去重，当遇到重复元素时，一个一个跳过
     */
    public int findMin(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int left = 0, right = n -1;
        while (left < right) {
            int mid = (right + left) / 2;
            // 只判断右侧，如果相等 right 左移一位，继续二分比较
            if (nums[mid] == nums[right]) {
                right--;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }


    /**
      暴力解法
     利用旋转之后的元素
        1. 头到 旋转点 递增， 旋转点 最小，然后再由旋转点 到 尾也是递增
        2. 头到尾都是递增
     从前往后遍历数组，当 nums[i+1] < nums[i] 时，nums[i + 1] 就是最小值，否则最小值就是 nums[0]
     */
    public int minArray(int[] numbers) {
        for (int i = 0; i+1 < numbers.length; i++) {
            if (numbers[i] > numbers[i+1]) {
                return numbers[i+1];
            }
        }
        return numbers[0];
    }

    public static void main(String[] args) {

        Solution_154 s = new Solution_154();
        int i = s.minArray(new int[]{4, 5, 6, 7, 1, 2, 3});
        System.out.println(i);
    }
}
