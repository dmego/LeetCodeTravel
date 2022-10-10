package cn.dmego.leetcode.solution.lc1_lc50;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 *
 * @author dmego
 * @date 2022/10/10 10:51
 */
public class Solution_15 {
    /*
    - 按照两数之和的思路（a + b = -c），先固定一个数，作为 target, 然后从剩下的数组元素中做两数之和
    - 我们可以先对数组进行排序，这样第二层循环做两数之和的时候,就可以使用首尾双指针来操作
    - 如何保证不包含重复的元素：
      - 当 target 值相同时，第一层循环 continue; 因为求得的结果肯定相同
      - 当已经求得一个结果 nums[j] + nums[k] == target 时，如果 nums[j + 1] == nums[j], 那么如果再继续求第三个数，求得的结果肯定和上一个相同，所以继续做 j++, 直到 nums[j] != num[j+1]
    - 优化点：
      - 由于我们三数之和结果为 0, 因为数组已经排序，如果第一个数 a > 0, 那么就不用继续找了，后续的肯定没有满足条件的 b 和 c 了。
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) return result;
        // 先排序，保证第二层遍历时，双指针的操作
        Arrays.sort(nums);
        // 第一层循环固定一个数为 target
        for (int i = 0; i < nums.length - 1; i++) {
            // 如果 target > 0 则 后面的两个数之和一定 > target, 不存在结果，直接 break;
            if (nums[i] > 0) break;
            // target 相同时，去重
            if (i > 0 && nums[i - 1] == nums[i]) continue;
            // 第二层，双指针循环遍历数组
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int res = nums[j] + nums[k] + nums[i];
                if (res == 0) {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                    // 第二层去重，当 target 相同时，第一个数 nums[j] 相同，第三个数也会相同，就重复了
                    while (j < k && nums[j - 1] == nums[j]) {
                        j++;
                    }
                }
                else if (res < 0) j++;
                else if (res > 0) k--;
            }
        }
        return result;
    }

}
