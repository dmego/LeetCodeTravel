package cn.dmego.leetcode.solution.lc1_lc50;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 四数之和
 * 
 * @author dmego
 * @date 2022/10/10 11:11
 */
public class Solution_18 {

    /*
        与 三数之和思路一样，就是 target 多一层，最后一层是双指针
        注意点：
         - 排序和去重
         - 和超过了 int 类型，需要用 long
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 4) return result;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            // 去重
            if (i > 0 && nums[i - 1] == nums[i]) continue;
            for (int j = i + 1; j < nums.length - 1; j++) {
                // 去重
                if (j > i + 1 && nums[j - 1] == nums[j]) continue;
                int k = j + 1, m = nums.length - 1;
                // 双指针遍历
                while (k < m) {
                    // 防止 int 类型移除，使用 long 类型
                    long res = (long)nums[i] + nums[j] + nums[k] + nums[m];
                    if (res == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k], nums[m]));
                        k++;
                        m--;
                        // 最后一层去重
                        while (k < m && nums[k - 1] == nums[k]) {
                            k++;
                        }
                        while (m > k && nums[m + 1] == nums[m]) {
                            m--;
                        }
                    } else if (res < target) {
                        k++;
                    } else if (res > target) {
                        m--;
                    }
                }
            }
        }
        return result;
    }
}
