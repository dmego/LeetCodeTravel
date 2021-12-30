package cn.dmego.leetcode.solution;

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

    /*
    如何判断两棵树 root1 和 root2 是否对称
    1.根节点值相等 root1.val = root2.val
    2.root1的左节点值和root2右节点值相等 root1.left.val = root2.right.val
    3.root1的右节点值和root2的左节点值相等 root1.right.val = root2.left.val
    */
    public static boolean isSymmetric2(TreeNode root) {
        if (root == null) return true;
        return dfs(root.left, root.right);
    }

    /**
     递归判断两棵树是否对称
     */
    public static boolean dfs(TreeNode left, TreeNode right) {
        // 如果两棵树都是 null 返回 true 对称的
        if (left == null && right == null) return true;
        // 如果两棵其中只有一棵是 null, 返回 false 肯定是非对称的
        if (left == null || right == null) return false;
        // 如果两棵树的 root 节点值不相等，不满足要求 返回 false
        if (left.val != right.val) return false;
        // 根据对称树的特性 递归判断
        boolean bool1 = dfs(left.left, right.right);
        boolean bool2 = dfs(left.right, right.left);
        return bool1 && bool2;
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
