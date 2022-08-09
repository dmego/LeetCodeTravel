package cn.dmego.leetcode.solution.lc51_lc100;

import cn.dmego.util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 二叉树中序遍历
 * @author dmego
 * @date 2022/02/22 09:36
 */
public class Solution_94 {

    /**
     * 解法一：递归解法
     */
    public void inDfs(TreeNode root, List<Integer> result) {
        if (root == null) return;
        inDfs(root.left, result);
        result.add(root.val);
        inDfs(root.right, result);
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inDfs(root, result);
        return result;
    }

    /**
     * 解法二：迭代解法
     * 循环判断栈是否为空 或者 root == null
     * 循环：如果 root != null, 入栈
     * 如果 root == null, 出栈一个元素 node, node 是 root 的父节点，此时将 root.val 加入结果集
     * root = node.right，继续循环
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Deque<TreeNode> deque = new ArrayDeque<>();
        while (root != null || !deque.isEmpty()) {
            while (root != null) {
                deque.push(root);
                root = root.left;
            }
            // 出栈是 root 的 父节点
            root = deque.pop();
            // 根节点先加入结果集
            result.add(root.val);
            // 指向右节点，继续循环
            root = root.right;
        }
        return result;
    }

}
