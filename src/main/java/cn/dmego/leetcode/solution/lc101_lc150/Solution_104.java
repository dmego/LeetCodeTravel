package cn.dmego.leetcode.solution.lc101_lc150;

import cn.dmego.util.TreeNode;

/**
 * 二叉树的最大深度
 * @author dmego
 * @date 2022/01/21 10:28
 */
public class Solution_104 {

    /**
     深度优先遍历：dfs()
     在二叉树中，从一个节点出发，要求最大深度，一定只能选左右两边的其中一个。
     我们采用 dfs(root) 深度优先遍历，返回结果值表示 root 这个节点能为它的父节点提供的最大深度
     当 root = null 时，能提供的最大深度是 0
     递归调用 left = dfs(root.left); right = dfs(root.right);
     返回值就是 Math.max(left, right) + 1; 意思是 root 节点的最大深度 = root 节点的左右节点中最大的深度节点 + 它本身
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }
}
