package cn.dmego.leetcode;

/**
 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。

 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。

 */
public class Solution_80 {

    /**
     题意分析：
        这道题是 26 题的延续，主要不同的地方在于：使每个元素最多出现 2 次

     解题思路：
        我们还是延续 26 题的解法，采用双指针
        0. 当nums.length <= 2 时，直接返回即可
        1. 定义一个 slow 指针，开始时 slow = 2, 其表示下一个待加入新数组的元素。新数组的最后一个元素是nums[slow-1]
        新数组的倒数第二个元素是nums[slow-2], 刚开始时 新数组就是 {nums[0], nums[1]}
        2. 定义一个 fast 指针，开始时 fast = 2, 它用来遍历有序数组
        3. 循环遍历有序数组，循环结束条件是 fast = nums.length
            3.1 当 nums[fast] == nums[slow-2] 时, nums[fast] == nums[slow-1] 一定成立，因为数组是有序的
            而 slow-2, slow-1, fast 是递增的下标。
            3.2 所以只有当 nums[fast] != nums[slow-2] 时，nums[fast] 才能加入到 新数组 中，否则 fast 指针继续移动
        4. 最后新数组的最后一个元素是 nums[slow-1], 所以长度就是 slow;

     */
    public static int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len <= 2) {
            return len;
        }
        int fast = 2, slow = 2;
        while (fast < len) {
            if (nums[fast] != nums[slow - 2]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    /**
        解法二: 该题型的通用解法
        前面我们是使用双指针来做，其中 fast 指针主要目的是用来遍历数组的，那么我们是不是可以不用定义指针，采用 foreach 方式来遍历
        这样我们只需要再定义一个 slow 指针即可。开始时 slow = 0
        foreach 循环中遍历 nums, 得到每一个 数组元素 num 就是我们需要比较的元素
        假设题目要求每个元素最多出现 K 次。
        则循环中刚开始的前 K 个元素一定就是 新数组的数据，直接加入新数组即可，也就是 nums[slow]=num,slow++;
        从 slow = K+1 开始，slow 就表示下一个要进入新数组的元素。
        和题解一分析一样，如果 num == nums[slow-k], 那么 num 是重复的元素，并且此时重复个数超过了 K 个
        所以 只需要判断 num != nums[slow-k], 如果成立， 则 num 需要加入新数组，nums[slow] = num, slow++;
        最后返回新数组的长度就是 slow;
     */
    public static int removeDuplicates2(int[] nums) {
        return process(nums, 2);
    }

    public static int process(int[] nums, int k) {
        int slow = 0;
        for (int num : nums) {
            if (slow < k || nums[slow - k] != num) {
                nums[slow++] = num;
            }
        }
        return slow;
    }

}
