package cn.dmego.newcode.top101.treenode;

import cn.dmego.util.PrintTree;
import cn.dmego.util.TreeNode;

import java.util.LinkedList;

/**
 * 二叉树镜像
 * @author dmego
 * @date 2022/03/08 08:58
 */
public class BM33 {

    // 递归法
    public TreeNode Mirror (TreeNode pRoot) {
        // pRoot = null, 直接返回
        if (pRoot == null) return null;
        // 交换 pRoot的左右节点
        TreeNode temp = pRoot.left;
        pRoot.left = pRoot.right;
        pRoot.right = temp;
        // 递归操作 pRoot 的左节点
        Mirror(pRoot.left);
        // 递归操作 pRoot 的右节点
        Mirror(pRoot.right);
        // 结果返回的还是 pRoot, 镜像树的根节点不变
        return pRoot;
    }

    // 迭代法
    public TreeNode Mirror2 (TreeNode pRoot) {
        if (pRoot == null) return null;
        // 使用 LinkedList 作为队列，因为可以保存 null 值
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.addLast(pRoot);
        while (!deque.isEmpty()) {
            TreeNode root = deque.pollFirst();
            // 如果 root = null, 跳过
            if (root == null) continue;
            // 交换 root的左节点和右节点
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            // 将 root 的左节点和右节点添加到队列，继续交换
            deque.add(root.left);
            deque.add(root.right);
        }
        return pRoot;
    }



    public static void main(String[] args) {
        TreeNode root = new TreeNode(8);
        TreeNode left1 = new TreeNode(7);
        TreeNode left2 = new TreeNode(6);
        TreeNode left3 = new TreeNode(5);
        root.left = left1;
        left1.left = left2;
        left2.left = left3;
        BM33 bm = new BM33();
        TreeNode node = bm.Mirror2(root);
        PrintTree.print(node);
    }

}
