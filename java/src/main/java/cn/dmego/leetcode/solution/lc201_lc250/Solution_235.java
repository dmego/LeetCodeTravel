package cn.dmego.leetcode.solution.lc201_lc250;

import cn.dmego.util.TreeNode;

/**
 * 二叉搜索树的最近公共祖先
 * @author dmego
 * @date 2022/01/06 17:42
 */
public class Solution_235 {


    /**
     二叉搜索树的特定： root.left.val < root.val < root.right.val
        也就是说 root 左子树上所有节点的值 一定小于 root.val
                root 右子树上所有节点的值 一定大于 root.val

     可以采用先序遍历： 如果 p.val < root.val < q.val 那么 root 就是最近公共祖先，直接返回


     */
    TreeNode ans;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int min = Math.min(p.val, q.val);
        int max = Math.max(p.val, q.val);
        dfs(root, min, max);
        return ans;
    }

    public void dfs(TreeNode root, int min, int max) {
        if (min <= root.val && root.val <= max) {
            ans = root;
            return;
        }
        if (root.left != null) dfs(root.left, min, max);
        if (root.right != null) dfs(root.right, min, max);
    }
}
