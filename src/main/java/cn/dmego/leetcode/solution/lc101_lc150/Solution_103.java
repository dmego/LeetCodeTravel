package cn.dmego.leetcode.solution.lc101_lc150;

import cn.dmego.util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Solution_103
 *   二叉树的锯齿形层序遍历
 * @author dmego
 * @date 2022/8/12 10:38
 */
public class Solution_103 {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        // 定义一个双端队列
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        // 将根节点加入到队列中
        deque.addFirst(root);
        // 表示是从左往右遍历，还是从右往左
        boolean leftToRight = true;
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node;
                // 当从左往右遍历时
                if (leftToRight) {
                    // 队头节点出队
                    node = deque.pollFirst();
                    // addLast, 保证队列里面顺序是：[right, left]，下一次是从右往左遍历，直接 pollLast
                    if (node.left != null) {
                        deque.addLast(node.left);
                    }
                    if (node.right != null) {
                        deque.addLast(node.right);
                    }
                }
                // 否则就是从右往左遍历
                else {
                    // 队尾节点出队
                    node = deque.pollLast();
                    // addFirst, 保证队列里元素顺序为: [right, left], 下一次从左往右遍历，直接 pollFirst
                    if (node.right != null) {
                        deque.addFirst(node.right);
                    }
                    if (node.left != null) {
                        deque.addFirst(node.left);
                    }
                }
                // 将遍历的结果添加到本层集合中
                list.add(node.val);
            }
            // 将刚遍历的一层节点集合添加到结果集中
            result.add(list);
            // 更改下一次遍历的方向
            leftToRight = !leftToRight;
        }
        return result;
    }

}
