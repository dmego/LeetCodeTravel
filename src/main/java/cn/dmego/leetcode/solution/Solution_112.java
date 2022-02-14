package cn.dmego.leetcode.solution;

import cn.dmego.util.TreeNode;

/**
 * 路径总和
 *
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点
 * 的路径，这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 * @author dmego
 * @date 2022/02/14 11:00
 */
public class Solution_112 {

    boolean result = false;
    public void dfs(TreeNode root, int sum, int targetSum) {
        if (root == null) return;
        sum += root.val;
        if (root.left == null && root.right == null) {
            if (sum == targetSum) {
                result = true;
            }
            return;
        }
        dfs(root.left, sum, targetSum);
        dfs(root.right, sum, targetSum);
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        dfs(root, 0, targetSum);
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        Solution_112 s = new Solution_112();
        boolean b = s.hasPathSum(root, 3);
        System.out.println(b);
    }

}
