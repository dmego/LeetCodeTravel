package cn.dmego.leetcode.solution.lc401_;

import cn.dmego.util.TreeNode;

/**
 * 合并二叉树
 */
public class Solution_617 {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return null;
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        // 合并 val
        root1.val = root1.val + root2.val;
        // 合并 左子树，赋值给 root1
        root1.left = mergeTrees(root1.left, root2.left);
        // 合并 右子树，赋值给 root1
        root1.right = mergeTrees(root1.right, root2.right);
        // 返回 root1
        return root1;
    }
}