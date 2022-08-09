package cn.dmego.leetcode.solution.lc151_lc200;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dmego
 * @date 2021/09/18 14:12
 */
public class Solution_169 {

    /**
     使用Map 来存放遍历数组中的每个值和每个值出现的次数
     然后判断是否有值出现的次数 > 2/n;
     */
    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int value = 1;
            if (map.containsKey(nums[i])) {
                value += map.get(nums[i]);
            }
            map.put(nums[i], value);
        }

        for (Integer key : map.keySet()) {
            if (map.get(key) > nums.length >> 1) {
                return key;
            }
        }
        return -1;
    }

    /**
     摩尔投票法：
     初始化候选人 cand 为 nums[0], 候选人票数 count = 1;
     遍历数组 当遇到与候选人相同的值（nums[i] == cand） 时， 票数+1（count++）
     当遇到与候选人票数不等的值时 ，票数-1 （count--）
     如果 count = 0, 更换候选人 cand = nums[i]
     遍历结束后，cand 的值就是结果(不用考虑不存在的情况，题目前提是一定存在)

     说明正确性：
     如果存在多数元素，那么多数元素的个数 M 和 其他剩余元素的个数 N 一定满足 M - N >= 1;
     也就是说在一定存在多数元素情况下，遍历下来，留下来的一定多数元素。
     */
    public static int majorityElement2(int[] nums) {
        int cand = nums[0], count = 1;
        // 注意从 1 开始
        for (int i = 1; i < nums.length; i++) {
            if (cand == nums[i]) {
                count++;
            } else {
                // count = 0 时重新开始设置候选人和count值
                if (count == 0) {
                    cand = nums[i];
                    count = 1;
                } else {
                    count--;
                }
            }
        }
        return cand;
    }



    public static void main(String[] args) {

    }
}
