package cn.dmego.jzoffer;

import cn.dmego.util.TreeNode;

/**
 * 剑指 Offer 55 - I. 二叉树的深度
 */
public class Offer_55_I {

    // lc 104
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }
}
