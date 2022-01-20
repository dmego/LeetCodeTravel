package cn.dmego.leetcode.solution;

import cn.dmego.util.TreeNode;

/**
 * 二叉树最大路径和
 * 路径 是指从二叉树一个节点到另外一个节点经过的节点路径
 * 路径和 是经过的所有节点的值的和
 * 最大路径和 是所有的路径中，节点值和最大是多少
 *
 * @author dmego
 * @date 2022/01/20 14:41
 */
public class Solution_124 {

    /**
     DFS 深度优先遍历
     1. 如何理解路径？在二叉树中，一条路径一定是有一个根节点，要么是先上升后下降，要么是只有上升，要么只有下降
        那么我们就可以将一条路径分为 3 段，root 节点，root 左边路径，root 右边路径
        路径和也就是这 3 段加起来的和

        遍历出所有路径再统计出路径和，这样太复杂了，我们可以换一个思路，从最大路径和入手
       -10
     -6    5
        13  14
     我们可以使用 dfs(root) 方法, 求 root 节点能为它的父节点求提供的最大贡献值，具体步骤如下：
      1. 深度遍历的结束条件，遇到叶子节点，此时 dfs(叶子节点) 能提供的最大贡献值就是 root.val
      2. 递归求 left = dfs(root.left) 也就是 root.left 为 root 节点求最大路径的能提供的最大贡献值，如果返回值 < 0, 那么最大贡献值为 0
      3. 递归求 right = dfs(root.right) 也就是 root.right 为 root 节点求最大路径能提供的最大贡献值，如果返回值 < 0, 那么最大贡献值为 0
      4. 以 root 为根节点的最大路径和就是：root.val + left + right；我们需要定义一个全局变量，用来保存二叉树中的最大路径和，当我们在求每个节点的最大路径和后，最大的就是最大路径和
      5. 递归返回的结果是 root 节点能为它的父节点提供的最大贡献值：这个值=root.val + max(left, right)
         也就是对于一条路径，只能经过 root 节点的一边。最大路径和，那么一定是经过左 右节点中，值最大的那一边
     */
    int maxSum = Integer.MIN_VALUE;
    public int dfs(TreeNode root) {
        // 空节点的最大贡献值为 0
        if (root == null) return 0;
        // 递归求 root.left 节点能给 root 节点提供的最大贡献值, 如果返回值 < 0, 那么最大贡献值为 0
        int left = Math.max(dfs(root.left), 0);
        // 递归求 root.right 节点能给 root 节点提供的最大贡献值，如果返回值 < 0, 那么最大贡献值为 0
        int right = Math.max(dfs(root.right), 0);
        // sum 表示以 root 节点为根的最大路径和
        int sum = root.val + left + right;
        // 更新 maxSum 最大路径和
        maxSum = Math.max(maxSum, sum);
        // root 节点的最大贡献值 = root的值 + 左右节点之中能提供的最大的贡献值
        return root.val + Math.max(left, right);
    }

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

}
