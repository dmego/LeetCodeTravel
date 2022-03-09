package cn.dmego.leetcode.solution;

import java.util.Arrays;

/**
 * 缺失的第一个正数
 * 从 1 开始的，缺失的第一个正整数
 * @author dmego
 * @date 2022/03/09 14:33
 */
public class Solution_41 {

    /**
     假设 nums 全为负数，那么第一个正数就是 1
     假设 nums 全为正数，或者既有正数，又有负数，以 nums 的长度 nums.length = n 为基础
     定义一个长度为 n + 1 的数组 res[]，遍历 nums, 如果 0 < nums[i] < n + 1,  res[nums[i]] = 1;
     遍历 res[] 数组，如果 [1, n] 之间所有数都为 1， 说明 nums 中缺失的第一个正数为 n + 1
     否则缺失的第一个正数是 [1, n] 中第一个不等于 1 的数
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int[] res = new int[n + 1];
        for (int num : nums) {
            if (num > 0 && num <= n) {
                res[num] = 1;
            }
        }
        for (int i = 1; i < res.length; i++) {
            if (res[i] == 0) return i;
        }
        return n + 1;
    }

    /**
      解法二：排序后遍历
     */
    public int firstMissingPositive2(int[] nums) {
        Arrays.sort(nums);
        // 如果是负数，跳过； 如果是正数，与 1 进行比较。
        int cur = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) continue;
            // 跳过重复元素
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] == cur) cur++;
            else return cur;
        }
        return cur;
    }

    /**
     解法三：原地操作
     首先要明确，我们的结果一定在 [1, n + 1] 这个范围内，
     因为就算 nums 从 1(nums[0]) 到 n(nums[n-1]) 连续，那么最小的正数就是 n+1
     我们可以采用原地挪动的方法：将值等于 i（i > = 1） 的元素移动到 下标是 i - 1 的位置
     然后我们遍历 nums, 如果 nums[i] != i + 1, 那么 i 就是最小的正数

     为什么 是 while 循环 而不是 if， 举例：
     [3,1,4,2] 经过一次交换后变成了 [4,1,3,2] 此时 i = 0, 位置元素 4 不在其应该在的位置
     如果我们是 if, 就会执行 i++, 4 这个元素将不会被处理
     如果我们是 while 循环，条件继续成立，[4,1,3,2] 经过交换后变成 [2,1,3,4]，继续交换变成 [1,2,3,4]
     此时，下标 i = 0 位置的元素是 1，是正确的元素
     */
    public int firstMissingPositive3(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // nums[i] >= 1 && nums[i] <= n 这个条件是保证元素范围在 [1, n] 之间
            // nums[i] != i + 1 这个是过滤那些元素在正确下标的元素
            // nums[nums[i] - 1] != nums[i] 下标 nums[i] - 1 与 i 位置元素相同，不必进行交换
            while (nums[i] >= 1 && nums[i] <= n && nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                swap(nums, nums[i] - 1, i);
            }
        }
        // 遍历数组，找到第一个 nums[i] != i + 1 的值，并返回
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) return i + 1;
        }
        // 如果上面的遍历都满足，说明 nums是 [1, n], 所以最小正数就是 n + 1
        return n + 1;
    }

    // 交换 nums 中 a 下标和 b 下标的两个数
    public void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1};
        Solution_41 s = new Solution_41();
        int i = s.firstMissingPositive3(array);
        System.out.println(i);
    }

}
