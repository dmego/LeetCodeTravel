package cn.dmego.leetcode.solution;

import cn.dmego.util.TreeNode;

/**
 * 二叉树的最近公共祖先
 * @author dmego
 * @date 2022/01/06 16:52
 */
public class Solution_236 {

    /**
     什么是祖先：若节点 p 在 root 节点的左(右)子树中，或者 p == root, 则称 root 是 p 的祖先
     什么是最近公共祖先：若 root 节点同时是p, q 的祖先, 并且 root.left 和 root.right 不满足同时是 p,q 的祖先
        则root是p, q 的最近公共祖先。

     root 满足的最近公共祖先的条件：
        1. 节点 p, q 分别在 root 的 左子树和 右子树中
        2. p == root 且 q 在 root 的左或右子树中
        3. q == root 且 p 在 root 的左或右子树中

     使用后序遍历，遇到 p, 或 q 节点后直接返回。根据左右节点返回值的不同，将 最近公共祖先最终返回
     递归后序遍历步骤：
     1. 递归结束条件
        1.1 root == null, return null
        1.2 root == p / q， return root (p / q)
     2.递归左右子树，保存返回值 left right
     3. 根据左右子树的递归结果，返回不同的返回值
        3.1 如果 left == null, right == null 说明 p, q 不在 root 的左右子树上，return null;
        3.2 如果 left != null && right != null 说明，p, q 在 root 的左右子树上，root 就是最近公共祖先，return root;
        3.3 如果 left != null && right == null 说明，p, q 在 root 的left 子树上，return left
        3.4 right != null 同 3.3
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }
}
