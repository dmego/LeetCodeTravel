package cn.dmego.newcode.top101.treenode;

import cn.dmego.util.TreeNode;

/**
 * 判断是不是二叉搜索树
 * @author dmego
 * @date 2022/03/11 11:24
 */
public class BM34 {

    public boolean isValidBST (TreeNode root) {
        // write code here
        if (root == null) return true;
        return dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     每个节点的值必须满足一个区间范围，默认根节点区间范围为 [MIN, MAX]
     如果当前节点值 root.val < MIN 或者 root.val > MAX 则不满足条件，return false
     递归判断 root.left, 并缩小区间右边界 MAX = root.val
     递归判断 root.right 并缩小区间左边界 MIN = root.val
     */
    public boolean dfs(TreeNode root, int min, int max) {
        if (root == null) return true;
        if (root.val < min || root.val > max) return false;
        return dfs(root.left, min, root.val) && dfs(root.right, root.val, max);
    }

    /**
     利用二叉搜索树中序遍历特点：有序（从小到大）
     如果遍历的节点小于等于前一个遍历的节点，那么就不满足条件，返回 false
     */
    int pre = Integer.MIN_VALUE; // 记录前一个遍历的节点值
    public boolean isValidBST2 (TreeNode root) {
        // write code here
        if (root == null) return true;
        boolean left = isValidBST2(root.left);
        if (root.val <= pre) return false;
        else pre = root.val; // 更新前一个节点值
        boolean right = isValidBST2(root.right);
        return left && right;
    }

}
