package cn.dmego.leetcode.solution.lc401_;

import cn.dmego.util.TreeNode;

/**
 * 序列化和反序列化二叉搜索树
 */
public class Solution_449 {

    // 利用前序遍历序列化二叉搜索树，以逗号分割。
    public String serialize(TreeNode root) {
        if (root == null) return null;
        StringBuffer buff = new StringBuffer();
        serializeHelper(root, buff);
        buff.deleteCharAt(buff.length() - 1);
        return buff.toString();
    }

    public void serializeHelper(TreeNode root, StringBuffer buff) {
        if (root == null) return;
        buff.append(root.val).append(",");
        serializeHelper(root.left, buff);
        serializeHelper(root.right, buff);
    }


    // 定义一个 指针，用来指定当前遍历数组到了哪个位置
    int point = 0;
    public TreeNode deserialize(String data) {
        if (data == null) return null;
        String[] vals = data.split(",");
        return buildTree(vals,Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // 利用二叉搜索树的特定：left.val < root.val < right.val
    public TreeNode buildTree(String[] vals, int min, int max) {
        // 遍历到了数组最后，退出构建二叉树
        if (min > max || point >= vals.length) return null;
        int val = Integer.parseInt(vals[point]);

        // 如果当前节点的值不在当前 [min, max] 范围内，不添加当前节点
        // 保证了不会一直构造同一侧，左子树构建完之后，就会构建右子树
        if (val < min || val > max) {
            return null;
        }
        // 指针往后移动一位
        point++;
        // 按照前序逻辑构建当前节点
        TreeNode root = new TreeNode(val);

        // 先构建左子树，左子树上的所有节点值 < root.val
        root.left = buildTree(vals, min, root.val);
        // 后构建右子树，右子树上所有节点值 > root.val
        root.right = buildTree(vals, root.val, max);
        return root;
    }
}
