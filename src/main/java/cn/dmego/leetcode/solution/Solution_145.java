package cn.dmego.leetcode.solution;

import cn.dmego.util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * 二叉树后序遍历
 * @author dmego
 * @date 2022/02/22 09:37
 */
public class Solution_145 {

    /**
     * 解法一：递归解法
     */
    public void postDfs(TreeNode root, List<Integer> result) {
        if (root == null) return;
        postDfs(root.left, result);
        postDfs(root.right, result);
        result.add(root.val);
    }
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postDfs(root, result);
        return result;
    }

    /**
     * 解法二：迭代解法1
     * 根据后序遍历顺序：左 右 根 --> 反转之后就是 根 右 左
     * 和前序遍历 根 左 右 很像，我们可以按照前序遍历的思路来进行迭代
     * 然后进行反转
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.push(root);
        while (!deque.isEmpty()) {
            TreeNode node = deque.pop();
            result.add(node.val);
            // 左节点先入栈
            if (node.left != null) {
                deque.push(node.left);
            }
            // 由节点后入栈
            if (node.right != null) {
                deque.push(node.right);
            }
        }
        // 反转 result
        Collections.reverse(result);
        return result;
    }

    /**
     * 解法三：迭代解法2
     * 根据后序遍历的特点：左 右 根
     * 循环条件，root != null || 栈不为空
     * 循环：root != null; 将 root 入栈，root = root.left
     * 如果 root == null, 出栈一个节点 node = deque.pop(), 节点node是 root 的父节点
     * 如果 node 的 右节点已经被遍历过，则将 node.val 加入到结果集中
     * 如果 node 的 右节点没有被遍历过，root = node.right, 继续进行遍历
     */
    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Deque<TreeNode> deque = new ArrayDeque<>();
        // 前一个遍历节点
        TreeNode pre = null;
        while (root != null || !deque.isEmpty()) {
            while (root != null) {
                deque.push(root);
                root = root.left;
            }
            root = deque.pop();
            // 如果 root.right == null 或者 root.right 被访问过了
            // 上面循环下来，root.left = null, 如果 root.right = null 则说明 root 是叶子节点, 需要遍历
            // root.right == pre 时，说明 root的右节点被遍历到了，按照 左右根的顺序，root 节点该被遍历了
            if (root.right == null || root.right == pre) {
                // 将 root 加入到结果集
                result.add(root.val);
                // 标记上一个被访问的节点
                pre = root;
                // 访问当前节点后，需要继续出栈，所以将 root 指向 null
                root = null;
            } else {
                // 根节点再次入栈(因为根节点还没被访问)
                deque.push(root);
                // root 指向右节点
                root = root.right;
            }
        }
        return result;
    }



}

