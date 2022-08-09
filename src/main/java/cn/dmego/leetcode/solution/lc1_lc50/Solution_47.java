package cn.dmego.leetcode.solution.lc1_lc50;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 全排列II
 * 有重复项数字
 * @author dmego
 * @date 2022/03/01 16:01
 */
public class Solution_47 {

    /**
     解法一： 使用 Set 进行去重
     */
    class Solution {
        Set<List<Integer>> result = new HashSet<>();
        LinkedList<Integer> list = new LinkedList<>();
        boolean[] used;

        public void backtracking(int[] nums) {
            if (list.size() == nums.length) {
                result.add(new ArrayList<>(list));
            }
            for (int i = 0; i < nums.length; i++) {
                if(!used[i]) {
                    used[i] = true;
                    list.addLast(nums[i]);
                    backtracking(nums);
                    used[i] = false;
                    list.removeLast();
                }
            }
        }

        public List<List<Integer>> permuteUnique(int[] nums) {
            used = new boolean[nums.length];
            backtracking(nums);
            return new ArrayList<>(result);
        }
    }

    /**
     解法二: 先排序，然后再在横向 同一树层上进行去重

     首先理解题目要求：
        在一个排列里面，数字是允许重复的，没有要求，例如 [1,1,2]。
        但是在结果里面，不能有重复的排列出现, 例如 [[1,1,2],[1,1,2]] 就出现了重复的排列

     在横向遍历上去重，还是纵向递归上去重：
        横向遍历，是同一树层，求的是不同的排序，是不允许重复的，
        纵向递归，是同一树枝，求的是同一个排序内的数，是运行重复的
        所以在横向遍历上去重

    如何去重：
        对于原数组的顺序是不影响我们求出结果的，我们可以先排序，让相同的元素先在一起
        如果 nums[i] == nums[i - 1] 说明 nums[i] 是重复的
        如何确定是在横向遍历上还是纵向递归上，我们可以使用 used[i - 1] 来进行判断
        当 used[i - 1] = true 时， 说明 nums[i - 1] 是被使用的，也就是是说在同一个排列内
        所以去重的条件是：
            if (i > 0 && nums[i] == nums[i -1] && used[i - 1])
                continue;
        判断是重复后，跳过该元素，在横向上继续遍历下一个元素
     */
    class Solution2 {

        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        boolean[] used;

        public void backtracking(int[] nums) {
            if (list.size() == nums.length) {
                result.add(new ArrayList<>(list));
            }
            for (int i = 0; i < nums.length; i++) {
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                    continue;
                }
                if (!used[i]) {
                    used[i] = true;
                    list.addLast(nums[i]);
                    backtracking(nums);
                    used[i] = false;
                    list.removeLast();
                }
            }
        }

        public List<List<Integer>> permuteUnique(int[] nums) {
            used = new boolean[nums.length];
            // 先排序
            Arrays.sort(nums);
            backtracking(nums);
            return result;
        }
    }

}
