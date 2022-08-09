package cn.dmego.leetcode.solution.lc401_;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素）
 输出每个元素的下一个更大元素。
 数字 x 的下一个更大的元素是按数组遍历顺序，
 这个数字之后的第一个比它更大的数，
 这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 */
public class Solution_503 {

    /**
     *
     */
    public static int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] mnums = new int[len*2];
        System.arraycopy(nums,0,mnums,0,len);
        System.arraycopy(nums,0,mnums,len,len);

        Map<Integer,Integer> nextBigMap = new HashMap<>();
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = len*2 - 1; i >= 0; i--) {
            while (!deque.isEmpty() && deque.peek() <= mnums[i]) {
                deque.pop();
            }
            nextBigMap.put(i, deque.isEmpty() ? -1 : deque.peek());
            deque.push(mnums[i]);
        }
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = nextBigMap.get(i);
        }
        return res;
    }

    /**
     *
     */
    public static int[] nextGreaterElements2(int[] nums) {
        int len = nums.length;
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[len];
        for (int i = len*2 - 1; i >= 0; i--) {
            int index = i % len;
            while (!deque.isEmpty() && deque.peek() <= nums[index]) {
                deque.pop();
            }
            res[index] = deque.isEmpty() ? -1 : deque.peek();
            deque.push(nums[index]);
        }
        return res;
    }


    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        int[] ints = nextGreaterElements(nums);
        System.out.println(Arrays.toString(ints));
    }
}
