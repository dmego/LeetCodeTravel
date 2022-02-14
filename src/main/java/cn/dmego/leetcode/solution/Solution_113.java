package cn.dmego.leetcode.solution;

import cn.dmego.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 路径总和 II
 *
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 *
 * @author dmego
 * @date 2022/02/14 12:09
 */
public class Solution_113 {

    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> list = new LinkedList<>();
    public void dfs (TreeNode root, int sum, int targetSum) {
        if (root == null) return;
        sum += root.val;
        list.add(root.val);
        if (root.left == null && root.right == null && sum == targetSum) {
            result.add(new ArrayList<>(list));
            list.removeLast();
            return;
        }
        dfs(root.left, sum, targetSum);
        dfs(root.right, sum, targetSum);
        list.removeLast();
    }
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) return result;
        dfs(root, 0, targetSum);
        return result;
    }
}
