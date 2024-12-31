package cn.dmego.leetcode.solution.lc401_;

import cn.dmego.util.TreeNode;

import java.util.LinkedList;

/**
 * 二叉树的完全性检测
 * 给定一个二叉树的 root ，确定它是否是一个 完全二叉树 。
 *
 * 在一个 完全二叉树 中，除了最后一个关卡外，所有关卡都是完全被填满的，并且最后一个关卡中的所有节点都是尽可能靠左的。它可以包含 1 到 2^h
 * 节点之间的最后一级 h 。
 */
public class Solution_958 {
    /*
       完全二叉树的层序遍历结果中，null 值只会在最后出现，如果中间出现 null 值，后续还有非 null 的节点，一定不是完全二叉树
     */
    public boolean isCompleteTree(TreeNode root) {
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        boolean hasNull = false;
        while (!deque.isEmpty()) {
            TreeNode node = deque.poll();
            if (node == null) {
                hasNull = true;
            } else {
                if (hasNull) return false;
                deque.add(node.left);
                deque.add(node.right);
            }
        }
        return true;
    }
}
