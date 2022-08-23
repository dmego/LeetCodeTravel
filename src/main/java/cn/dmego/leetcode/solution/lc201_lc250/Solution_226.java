package cn.dmego.leetcode.solution.lc201_lc250;

import cn.dmego.util.TreeNode;

/**
 * 翻转二叉树（二叉树的镜像）
 */
public class Solution_226 {

    // 递归法
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.right = invertTree(left);
        root.left = invertTree(right);
        return root;
    }

}
