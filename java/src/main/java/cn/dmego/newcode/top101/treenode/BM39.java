package cn.dmego.newcode.top101.treenode;

import cn.dmego.util.TreeNode;

import java.util.LinkedList;

/**
 * 序列化二叉树
 * @author dmego
 * @date 2022/03/16 11:18
 */
public class BM39 {
    // 序列化使用层序遍历
    String Serialize(TreeNode root) {
        if (root == null) return null;
        LinkedList<TreeNode> deque = new LinkedList<>();
        StringBuilder buff = new StringBuilder();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            TreeNode node = deque.pollFirst();
            if (node == null) buff.append("#,");
            else {
                buff.append(node.val).append(",");
                deque.addLast(node.left);
                deque.addLast(node.right);
            }
        }
        buff.deleteCharAt(buff.length() - 1);
        return buff.toString();
    }

    // 反序列化
    TreeNode Deserialize(String str) {
        if (str == null || str.length() == 0) return null;
        String[] vals = str.split(",");
        LinkedList<TreeNode> deque = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        deque.addLast(root);
        int i = 1;
        while (i < vals.length) {
            TreeNode node = deque.removeLast();
            if (!vals[i].equals("#")) {
                TreeNode left = new TreeNode(Integer.parseInt(vals[i]));
                node.left = left;
                deque.addLast(left);
            }
            i++;
            if (!vals[i].equals("#")) {
                TreeNode right = new TreeNode(Integer.parseInt(vals[i]));
                deque.addLast(right);
                node.right = right;
            }
            i++;
        }
        return root;
    }
}
