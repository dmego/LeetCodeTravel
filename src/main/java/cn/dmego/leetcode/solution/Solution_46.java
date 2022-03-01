package cn.dmego.leetcode.solution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 全排列
 * 不包含重复项数字
 * @author dmego
 * @date 2021/12/23 12:31
 */
public class Solution_46 {



    // 结果
    List<List<Integer>> result = new ArrayList<>();
    // 记录一组结果
    LinkedList<Integer> list = new LinkedList<>();
    // used 数组，记录元素是否被使用
    boolean[] used;

    // 回溯函数
    public void backtracking(int[] num) {
        // 递归终止条件
        if (list.size() == num.length) {
            result.add(new ArrayList<>(list));
        }
        // 横向遍历
        for (int i = 0; i < num.length; i++) {
            // 如果第 i 个元素没有被使用过
            if (!used[i]) {
                used[i] = true;
                list.addLast(num[i]);
                // 纵向遍历，递归
                backtracking(num);
                // 撤销
                used[i] = false;
                list.removeLast();
            }
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];
        backtracking(nums);
        return result;
    }
}
