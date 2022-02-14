package cn.dmego.leetcode.solution;

import cn.dmego.util.TreeNode;

/**
 * 求二叉树根节点到叶子节点的数字之和（树中每个节点都存放有一个 0 到 9 之间的数字）
 * @author dmego
 * @date 2022/02/14 10:11
 */
public class Solution_129 {

    /**
     解法一: 使用 String 求出没条路径，当到达叶子节点时，将 String 转为 int,
     并累加到 sum 总和之中

     注意：这种解法效率低下
     */
    int sum = 0;
    public void dfs (TreeNode root, String num) {
        // 如果 root == null，直接返回
        if (root == null) {
            return;
        }
        // 遇到节点节点，路径上的数字之和的转为 int 类型，并累加到 sum 中
        if (root.left == null && root.right == null) {
            // 因为节点上的值在 0~9 是一位数，所以不用考虑进位问题
            sum += Integer.parseInt(num + root.val);
            return;
        }
        // 递归求左子树
        dfs(root.left, num + root.val);
        // 递归求右子树
        dfs(root.right, num + root.val);
    }

    /**
     解法二: dfs深度遍历，在遍历过程中求所有的值并累加
     */
    public int dfs2(TreeNode root, int num) {
        if (root == null) return 0;
        // temp 表示从根节点到 root 的数字之和
        int temp = num * 10 + root.val;
        // 如果 root 就是叶子节点，那么直接返回就行
        if (root.left == null && root.right == null){
            return temp;
        }
        // 递归求 root 的 左子树 和 右子树，累加就是所有路径的数字之和
        return dfs2(root.left, temp) + dfs2(root.right, temp);
    }

    public int sumNumbers(TreeNode root) {
        dfs(root, "");
        return sum;

        // return dfs2(root, 0);
    }
}
