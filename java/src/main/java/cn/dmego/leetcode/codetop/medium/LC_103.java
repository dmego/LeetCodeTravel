package cn.dmego.leetcode.codetop.medium;

import cn.dmego.util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 二叉树锯齿遍历
 * @author dmego
 * @date 2022/01/05 15:26
 */
public class LC_103 {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        boolean leftToRight = true; // 是否从左往右遍历
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node;
                if (leftToRight) {
                     node = deque.pollLast();
                    if (node.left != null) {
                        deque.addFirst(node.left);
                    }
                    if (node.right != null) {
                        deque.addFirst(node.right);
                    }
                } else {
                    node = deque.pollFirst();
                    if (node.right != null) {
                        deque.addLast(node.right);
                    }
                    if (node.left != null) {
                        deque.addLast(node.left);
                    }
                }
                list.add(node.val);
            }
            result.add(list);
            // 倒转遍历方向
            leftToRight = !leftToRight;
        }
       return result;
    }

}
