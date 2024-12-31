package cn.dmego.newcode.top101.treenode;

import cn.dmego.util.TreeNode;

/**
 * 二叉树的最大深度
 * @author dmego
 * @date 2022/03/05 11:17
 */
public class BM28 {

    /**
     深度就是叶子节点到根节点距离，最大距离就是所有叶子节点里面距离最长的一个
     采用 DFS 深度遍历：
     如果 root 是 null，返回 0， 如果 root 是叶子节点(左节点和有节点都为null)返回 1
     递归求 root 的左子树深度 left, 和右子树深度 right
     root 的深度则是 左右子树中最大的深度 + 1, Math.max(left, right) + 1
     */
    public int maxDepth (TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }
}
