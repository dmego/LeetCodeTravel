package cn.dmego.jzoffer;

import cn.dmego.util.BTreePrinter;
import cn.dmego.util.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 07. 重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字
 */
public class Offer_7 {

    /**
        同 LC 105 题
     */
    int index = 0;
    Map<Integer, Integer> inorderMap = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) return null;

        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        TreeNode root = buildTree(preorder, inorder, 0, inorder.length - 1);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder, int inLeft, int inRight) {
        TreeNode root = null;
        if (index < preorder.length) {
            root = new TreeNode(preorder[index]);
            index++;
            if (inLeft == inRight) return root;
            int inIndex = inorderMap.get(root.val);
            if (inIndex > 0 && inLeft <= inIndex - 1) {
                root.left = buildTree(preorder, inorder, inLeft, inIndex - 1);
            }
            if (inIndex < inorder.length - 1 && inIndex + 1 <= inRight) {
                root.right = buildTree(preorder, inorder, inIndex + 1, inRight);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        int[] preorder = {1,2,3};
        int[] inorder = {1,2,3};
        Offer_7 offer = new Offer_7();
        TreeNode root = offer.buildTree(preorder, inorder);
        BTreePrinter.printNode(root);
    }



}
