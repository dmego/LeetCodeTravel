package cn.dmego.leetcode.codetop.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 全排列
 * @author dmego
 * @date 2022/01/10 17:30
 */
public class LC_46 {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> list = new LinkedList<>();
    boolean[] used;

    public void backtracking(int[] nums) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                list.addLast(nums[i]);
                backtracking(nums);
                list.removeLast();
                used[i] = false;
            }
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];
        backtracking(nums);
        return result;
    }

    public static void main(String[] args) {
        LC_46 lc = new LC_46();
        List<List<Integer>> permute = lc.permute(new int[]{1, 2, 3});
        System.out.println(permute);


    }

}
