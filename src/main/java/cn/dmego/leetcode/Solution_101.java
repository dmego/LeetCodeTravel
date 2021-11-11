package cn.dmego.leetcode;

import cn.dmego.leetcode.util.TreeNode;

import java.util.LinkedList;

/**
 * @author dmego
 * @date 2021/11/11 10:00
 */
public class Solution_101 {

    /**
     什么对称树，如何比较
     1. 首先判断根节点的左子树 left 和右子树 right 值是不是相等
     2. left.left 和 right.right 如果相等，继续比较
     3. left.right 和 right.left，如果相等 继续比较
     */

    /**
    迭代解法
     队列的替代：
        使用 LinkedList, addLast 是向尾部添加, pollFirst 是取头部
        LinkedList 可以存 null 值
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.addLast(root.left);
        deque.addLast(root.right);
        while (!deque.isEmpty()) {
            TreeNode left = deque.pollFirst();
            TreeNode right = deque.pollFirst();
            if (left == null && right == null) continue;
            if (left == null || right == null) return false;
            if (left.val != right.val) return false;
            deque.addLast(left.left);
            deque.addLast(right.right);
            deque.addLast(left.right);
            deque.addLast(right.left);
        }
        return true;
    }

    /**
    递归解法
     */
    public static boolean isSymmetric2(TreeNode root) {
        if (root == null) return true;
        return dfs(root.left, root.right);
    }

    public static boolean dfs (TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return true;
        if (left.val != right.val) return false;
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }


    public static void main(String[] args) {
        // [1,2,2,3,4,4,3]
        // [1,2,2,null,3,null,3]
        TreeNode root = new TreeNode(1, new TreeNode(2, null, new TreeNode(3)),new TreeNode(2,null, new TreeNode(3)));
        //TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)),new TreeNode(2,new TreeNode(4), new TreeNode(3)));
        // [2,3,3,4,5,5,4,null,null,8,9,null,null,9,8]

        boolean symmetric = isSymmetric(root);
        System.out.println(symmetric);

    }
}
