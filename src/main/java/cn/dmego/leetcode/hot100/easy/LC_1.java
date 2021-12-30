package cn.dmego.leetcode.hot100.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * @author dmego
 * @date 2021/12/30 09:36
 */
public class LC_1 {

    public int[] twoSum(int[] nums, int target) {
        if (nums.length < 2) return new int[]{};
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,4};
        int target = 6;
        LC_1 lc = new LC_1();
        int[] result = lc.twoSum(nums, target);
        System.out.println(Arrays.toString(result));
    }

}
