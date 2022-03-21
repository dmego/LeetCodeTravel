package cn.dmego.leetcode.solution;

import cn.dmego.util.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 二叉树的序列化与反序列化
 * @author dmego
 * @date 2022/03/16 13:56
 */
public class Solution_297 {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return null;
        StringBuilder buff = new StringBuilder();
        serializeHelper(root, buff);
        // 删除最后一个逗号
        buff.deleteCharAt(buff.length() - 1);
        return buff.toString();
    }

    public void serializeHelper(TreeNode root, StringBuilder buff) {
        if (root == null) buff.append("#,");
        else {
            buff.append(root.val).append(",");
            serializeHelper(root.left, buff);
            serializeHelper(root.right, buff);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        LinkedList<String> deque = new LinkedList<>(Arrays.asList(data.split(",")));
        return buildTree(deque);
    }

    public TreeNode buildTree(LinkedList<String> deque) {
        String s = deque.removeFirst();
        if (s.equals("#")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(s));
        root.left = buildTree(deque);
        root.right = buildTree(deque);
        return root;
    }

}
