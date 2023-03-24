package cn.dmego.jzoffer;

import cn.dmego.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 * 给定一棵二叉搜索树，请找出其中第 k 大的节点的值。
 */
public class Offer_54 {

    /*
     中序遍历是有序的，从小到大排序
     第 k 大，第 1 大在遍历结果最后面。第 k 大就是倒数第 k 个元素。
     */
    public int kthLargest(TreeNode root, int k) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result.get(result.size() - k);
    }

    public void dfs(TreeNode root, List<Integer> result) {
        if (root == null) return;
        dfs(root.left, result);
        result.add(root.val);
        dfs(root.right, result);
    }

}
