package cn.dmego.leetcode.solution.lc251_lc300;

import cn.dmego.util.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 二叉树的序列化与反序列化
 * @author dmego
 * @date 2022/03/16 13:56
 */
public class Solution_297 {

    // 解法1：前序遍历，
    class Codec_1 {
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
            if (root == null) {
                buff.append("#,");
                return;
            }
            buff.append(root.val).append(",");
            serializeHelper(root.left, buff);
            serializeHelper(root.right, buff);
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

    // 解法二：层序遍历
    class Codec_2 {
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null)
                return null;
            LinkedList<TreeNode> deque = new LinkedList<>();
            StringBuffer buff = new StringBuffer();
            deque.addLast(root);
            while (!deque.isEmpty()) {
                TreeNode node = deque.pollFirst();
                if (node != null) {
                    buff.append(node.val).append(",");
                    deque.addLast(node.left);
                    deque.addLast(node.right);
                } else {
                    buff.append("#,");
                }
            }
            buff.deleteCharAt(buff.length() - 1);
            return buff.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null)
                return null;
            LinkedList<String> deque = new LinkedList<>(Arrays.asList(data.split(",")));
            LinkedList<TreeNode> nodeDeque = new LinkedList<>();
            TreeNode root = new TreeNode(Integer.valueOf(deque.pollFirst()));
            nodeDeque.addLast(root);
            while (!nodeDeque.isEmpty()) {
                TreeNode node = nodeDeque.pollFirst();
                String val1 = deque.pollFirst();
                String val2 = deque.pollFirst();
                if (!"#".equals(val1)) {
                    TreeNode left = new TreeNode(Integer.valueOf(val1));
                    node.left = left;
                    nodeDeque.addLast(left);
                }
                if (!"#".equals(val2)) {
                    TreeNode right = new TreeNode(Integer.valueOf(val2));
                    node.right = right;
                    nodeDeque.addLast(right);
                }
            }
            return root;
        }
    }

}
