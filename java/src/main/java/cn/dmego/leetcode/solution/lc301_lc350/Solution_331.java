package cn.dmego.leetcode.solution.lc301_lc350;

import java.util.LinkedList;

/**
 * Solution_331
 *  验证二叉树的前序序列化
 * @author dmego
 * @date 2022/7/5 09:44
 */
public class Solution_331 {


    /**
     * 前序遍历是 根节点-左子树-右子树，我们利用栈特性
     * 从前往后遍历序列化出来的节点
     * 如果遍历到非 #，入栈
     * 如果遍历到 #
     *    1. 如果栈为空，直接返回 false
     *    2. 如果栈顶元素为非 #，说明当前 # 是栈顶元素的左节点，# 入栈
     *    3. 如果栈顶元素为 #，说明栈顶下一个元素就是子树根节点，先弹出 #，再弹出子树根节点，弹出之后，入栈一个 #
     */
    public boolean isValidSerialization(String preorder) {
        if (preorder == null) return false;
        LinkedList<String> deque = new LinkedList<>();
        String[] nodes = preorder.split(",");
        for (String node : nodes) {
            while ("#".equals(node) && !deque.isEmpty() && deque.peek().equals(node)) {
                // # 出栈
                deque.pop();
                if (deque.isEmpty()) {
                    return false;
                }
                // num 出栈
                deque.pop();
            }
            deque.push(node);
        }
        return deque.size() == 1 && deque.peek().equals("#");
    }


    /**
     * 解法二：利用树的出度和入度特性：在一棵树中，所有节点的入度之和等于出度之和
     * 根节点：入度 0 出度 2；
     * 普通节点：入度 1 出度 2；
     * 空节点(#)：入度：1 出度 0
     *
     */
    public boolean isValidSerialization2(String preorder) {
        if (preorder == null) return false;
        String[] nodes = preorder.split(",");
        // diff 表示为 出度 - 入度, 初始化为 1 是因为 根节点
        int diff = 1;
        for (String node : nodes) {
            // 先减去一个入度
            diff -= 1;
            if (diff < 0) return false;
            // 不是 # 节点，加上两个出度
            if (!"#".equals(node)) {
                diff += 2;
            }
        }
        return diff == 0;
    }

    public static void main(String[] args) {
        Solution_331 s = new Solution_331();
        String str = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        boolean result = s.isValidSerialization(str);
        System.out.println(result);
    }

}
