package cn.dmego.leetcode.solution;

import cn.dmego.util.TreeNode;

/**
 * 判断平衡二叉树
 * @author dmego
 * @date 2022/02/11 09:25
 */
public class Solution_110 {

    public int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    public boolean isBalanced(TreeNode root) {
        return dfs(root) != -1;
    }

}

