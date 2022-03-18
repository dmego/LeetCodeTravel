package cn.dmego.newcode.top101.treenode;

import cn.dmego.util.TreeNode;

/**
 * 判断是不是平衡二叉树
 * @author dmego
 * @date 2022/03/16 11:04
 */
public class BM36 {
    // 结果
    boolean result = true;
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) return true;
        treeDeep(root);
        return result;
    }

    // 递归求二叉树的高度
    public int treeDeep(TreeNode root) {
        if (root == null) return 0;
        // 叶子节点，高度为 1
        if (root.left == null && root.right == null) return 1;
        // 递归求左子树高度
        int left = treeDeep(root.left);
        // 递归求右子树高度
        int right = treeDeep(root.right);
        // 左右子树高度差 > 1, 不满足平衡条件，更新 result = false
        if (Math.abs(left - right) > 1) result = false;
        // 向上返回给父节点能提供的最大高度： MAX(left, right) + 本身高度 1
        return Math.max(left, right) + 1;
    }
}
