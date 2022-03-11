package cn.dmego.leetcode.solution;

import cn.dmego.util.TreeNode;

/**
 * @author dmego
 * @date 2022/03/11 16:03
 */
public class Solution_98 {
    // 二叉搜索树中序遍历是有序的
    long pre = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        boolean left = isValidBST(root.left);
        if (root.val <= pre) return false;
        else pre = root.val;
        boolean right = isValidBST(root.right);
        return left && right;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-2147483648, null, new TreeNode(2147483647));
        Solution_98 s = new Solution_98();
        boolean validBST = s.isValidBST(root);
        System.out.println(validBST);
    }
}
