package cn.dmego.leetcode.solution.lc51_lc100;

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
        // 左
        boolean left = isValidBST(root.left);
        // 如果当前节点的值 <= 前一个节点值 pre, 说明不满足有序，返回 false
        if (root.val <= pre) return false;
        // 更新前一个节点的值
        else pre = root.val;
        // 右
        boolean right = isValidBST(root.right);
        return left && right;
    }

    /**
     解法二：递归法
     每一个节点的值都在一个范围之内 [MIN < root.val < MAX]
     递归判断 root 的左子树，更新 MAX 为 root.val
     递归判断 root 的右子树，更新 MIN 为 root.val
     */
    public boolean isValidBST2(TreeNode root) {
        if (root == null) return true;
        return dfs(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }

    public boolean dfs(TreeNode root, long max, long min) {
        if (root == null) return true;
        if (root.val <= min || root.val >= max)
            return false;
        return dfs(root.left, root.val, min) && dfs(root.right, max, root.val);
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(-2147483648, null, new TreeNode(2147483647));
        Solution_98 s = new Solution_98();
        boolean validBST = s.isValidBST(root);
        System.out.println(validBST);
    }
}
