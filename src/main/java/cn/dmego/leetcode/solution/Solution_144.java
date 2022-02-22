package cn.dmego.leetcode.solution;

import cn.dmego.util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 二叉树前序遍历
 * @author dmego
 * @date 2022/02/22 09:37
 */
public class Solution_144 {
    /**
     * 解法一：递归
     */
    public void preDfs(TreeNode root, List<Integer> result) {
        if (root == null) return;
        result.add(root.val);
        preDfs(root.left, result);
        preDfs(root.right, result);
    }
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preDfs(root, result);
        return result;
    }


    /**
     * 解法二：迭代解法1
     * 先将根节点入栈
     * 循环遍历根节点左子树，入栈
     * 如果根节点左子树为空，出栈一个节点 node，该节点为父节点
     * 将节点指向 node.right, 继续循环
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Deque<TreeNode> deque = new ArrayDeque<>();
        while (root != null || !deque.isEmpty()) {
            while (root != null) {
                result.add(root.val);
                deque.push(root);
                root = root.left;
            }
            root = deque.pop();
            root = root.right;
        }
        return result;
    }

    /**
     * 解法三：迭代解法2
     * 先将 根节点入栈
     * 循环判断，栈是否为空
     * 出栈一个节点，将值加入 遍历结果集
     * 如果 根节点右节点 != null, 入栈
     * 如果 根节点左节点 != null, 入栈
     */
    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.push(root);
        while (!deque.isEmpty()) {
            TreeNode node = deque.pop();
            result.add(node.val);
            if (node.right != null) {
                deque.push(node.right);
            }
            if (node.left != null) {
                deque.push(node.left);
            }
        }
        return result;
    }


}
