package cn.dmego.leetcode.solution.lc201_lc250;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且
 *  * abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
 * @author dmego
 * @date 2022/03/15 19:01
 */
public class Solution_219 {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (k < 0 || nums == null || nums.length == 0) return false;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int j = map.get(nums[i]);
                if (Math.abs(i - j) <= k) return true;
                else map.put(nums[i], i);
            } else {
                map.put(nums[i], i);
            }
        }
        return false;
    }
}
