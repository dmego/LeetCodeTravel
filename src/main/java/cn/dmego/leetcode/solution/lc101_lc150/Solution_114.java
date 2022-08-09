package cn.dmego.leetcode.solution.lc101_lc150;

import cn.dmego.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树展开为链表
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 *
 *
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * @author dmego
 * @date 2022/02/16 12:32
 */
public class Solution_114 {

    /*
       解法一：暴力法： 缓存所有的节点，重新构建链表
     */
    List<TreeNode> list = new ArrayList<>();
    public void dfs (TreeNode root) {
        if (root == null) return;
        list.add(root);
        dfs(root.left);
        dfs(root.right);
    }

    public void flatten(TreeNode root) {
        if (root == null) return;
        dfs(root);
        root = list.get(0);
        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).left = null;
            list.get(i).right = list.get(i + 1);
        }
    }

    /*
        递归函数用途：将 root 二叉树展开为链表
        递归结束条件：root = null retutn;
        递归将 root 的左子树展开：flatten(root.left)
        递归将 root 的右子树展开：flatten(root.right)
        调用上面两个递归结束后，root 的左子树和右子树都已经展开为链表了，那么如何将 root 展开
        - 先将 root 的 右子树暂存
        - 然后将 root 的 左子树移动到 root 的右子树位置
        - 最后将 root 的右子树接到 root 左子树的最后一个节点的 右子树上
        */
    public void flatten2(TreeNode root) {
        // 如果 root = null 递归结束返回
        if (root == null) return;
        // 递归展开左子树
        flatten(root.left);
        // 递归展开右子树
        flatten(root.right);
        // 暂存 root 右子树
        TreeNode tempRight = root.right;
        // 将左子树移动到右子树的位置
        root.right = root.left;
        // 左子树置为 null
        root.left = null;
        // 循环求出最新右子树的最后一个节点
        while (root.right != null) {
            root = root.right;
        }
        // 将 暂存的tempRight 接到 root 的右节点上
        root.right = tempRight;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        root.left = node2;
        root.right = node5;
        node2.left = node3;
        node3.right = node4;
        node5.left = node6;

        Solution_114 s = new Solution_114();
        s.flatten2(root);

        System.out.println();
    }


    

}
