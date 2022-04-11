package cn.dmego.leetcode.solution;

import cn.dmego.util.TreeNode;

/**
 * 打家劫舍 3
 * @author dmego
 * @date 2022/04/11 15:05
 */
public class Solution_337 {

    /**
     树型DP问题：
     1. 确定遍历方式和返回值：后序遍历，返回值：dp[2] 数组，dp[0] 表示偷当前家，dp[1] 表示不偷当前家
     2. 确定终止条件：空节点：返回 new int{0,0} 叶子节点：new int{root.val, 0}
     3. dp的递归公式：
     如果偷当前节点，该节点的子节点不能偷：dp[0] = dfs(root.left)[1] + dfs(root.right)[1] + root.val
     如果不偷当前节点，该节点的子节点可以考虑偷：dp[1] = Math.max(dfs(root.left)[0], dfs(root.left)[1]) +
     Math.max(dfs(root.right)[0], dfs(root.right)[1])
     最后返回 dp

     因为是后序遍历，根节点是最后一个求出来的，返回偷和不偷中的最大一个值就行了
     */
    public int rob(TreeNode root) {
        if (root == null)
            return 0;
        int[] dp = dfsRob(root);
        return Math.max(dp[0], dp[1]);
    }

    public int[] dfsRob(TreeNode root) {
        if (root == null)
            return new int[] { 0, 0 };
        if (root.left == null && root.right == null)
            return new int[] { root.val, 0 };
        // 递归遍历左节点
        int[] left = dfsRob(root.left);
        // 递归遍历右节点
        int[] right = dfsRob(root.right);
        int[] dp = new int[2];
        // 偷当前节点
        dp[0] = root.val + left[1] + right[1];
        // 不偷当前节点
        dp[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return dp;
    }

}
