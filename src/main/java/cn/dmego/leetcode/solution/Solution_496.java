package cn.dmego.leetcode.solution;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 给你两个 没有重复元素 的数组nums1 和nums2，其中nums1是nums2的子集。

 请你找出 nums1每个元素在nums2中的下一个比其大的值。

 nums1中数字x的下一个更大元素是指x在nums2中对应位置的右边的第一个比x大的元素。
 如果不存在，对应位置输出 -1
 */
public class Solution_496 {

    /**
     * 解法一：
     */
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] res = new int[len1];
        for (int i = 0; i < len1; i++) {
            Deque<Integer> deque = new ArrayDeque<>();
            for (int j = len2 - 1; j >= 0; j--) {
                while (!deque.isEmpty() && deque.peek() <= nums2[j]) {
                    deque.pop();
                }
                if (nums1[i] == nums2[j]) {
                    res[i] = deque.isEmpty() ? -1 : deque.peek();
                    break;
                }
                deque.push(nums2[j]);
            }
        }
        return res;
    }

    /**
     * 解法二： 单调栈优化
     * 先求出 nums2 中每个元素的下一个更大的值，存放在 Map 中
     * 因为 nums1 是 nums2 的子集，所以结果直接从 Map 中取就行
     *
     */
    public static int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] res = new int[len1];
        // 先求出 nums2 中每个元素x的下一个最大的元素，放入 hashMap 中，之后 nums1直接从 map 中查
        Deque<Integer> deque = new ArrayDeque<>();
        Map<Integer, Integer> nextBigMap = new HashMap<>();
        for (int i = len2 - 1; i >= 0; i--) {
            while(!deque.isEmpty() && deque.peek() <= nums2[i]) {
                deque.pop();
            }
            nextBigMap.put(nums2[i], deque.isEmpty() ? -1 : deque.peek());
            deque.push(nums2[i]);
        }

        for (int i = 0; i < len1; i++) {
            res[i] = nextBigMap.get(nums1[i]);
        }
        return res;
    }
}
