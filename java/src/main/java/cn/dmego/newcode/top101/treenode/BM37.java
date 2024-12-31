package cn.dmego.newcode.top101.treenode;

import cn.dmego.util.TreeNode;

/**
 * 二叉搜索树的最近公共祖先
 * @author dmego
 * @date 2022/03/11 09:53
 */
public class BM37 {

    /**
     解法一：利用二叉搜索树的特点： left < root < right
     自上而下搜索二叉树，如果 root.val 在 p 和 q 中间，则说明 root.val 就是最近公共祖先
     */
    public int lowestCommonAncestor (TreeNode root, int p, int q) {
        // write code here
        int min = Math.min(p, q);
        int max = Math.max(p, q);
        while (root != null) {
            if (root.val < min) {
                root = root.right;
            } else if (root.val > max) {
                root = root.left;
            } else {
                return root.val;
            }
        }
        return -1;
    }

    /**
     解法二：DFS 深度遍历，自下向上
     如果 root = null, return -1, 如果 root.val = p 或 q, 直接返回
     递归调用 root.left, 返回值 left; 递归调用 root.right, 返回值 right
     如果 left 和 right != -1, 说明 root.val 就是最近公共祖先，直接返回
     如果 left != -1, 说明 p 或 q 在 left 所在子树上，返回 left；如果 right != -1, 同理
     */
    public int lowestCommonAncestor2 (TreeNode root, int p, int q) {
        // write code here
        if (root == null) return -1;
        if (root.val == p || root.val == q) return root.val;
        int left = lowestCommonAncestor(root.left, p, q);
        int right = lowestCommonAncestor(root.right, p, q);
        if (left == -1 && right == -1) return -1;
        else if (left == -1) return right;
        else if (right == -1) return left;
        else return root.val;
    }

}
