package cn.dmego.leetcode.solution.lc101_lc150;

import cn.dmego.util.PrintTree;
import cn.dmego.util.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 从中序和后续遍历序列中构建二叉树
 * @author dmego
 * @date 2022/02/23 09:56
 */
public class Solution_106 {

    /**
     中序遍历：左 根 右
     后序遍历：左 右 根
     按照 105 题思路
     */
    Map<Integer, Integer> inorderCache = new HashMap<>();
    public TreeNode dfs(int[] inorder, int inL, int inR, int[] postorder, int postL, int postR) {
        if (inL > inR || postL > postR) return null;
        // 后序遍历的最后一个元素始终是根节点
        TreeNode root = new TreeNode(postorder[postR]);
        // 找到根节点在中序数组中的下标
        int rootInIndex = inorderCache.get(postorder[postR]);
        // 计算左子树的节点长度
        int leftLen = rootInIndex - inL;
        root.left = dfs(inorder, inL, rootInIndex - 1, postorder, postL, postL + leftLen - 1);
        root.right = dfs(inorder, rootInIndex + 1, inR, postorder, postL + leftLen, postR - 1);
        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            inorderCache.put(inorder[i], i);
        }
        return dfs(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public static void main(String[] args) {
        int[] inorder = new int[]{9,3,15,20,7};
        int[] postorder = new int[]{9,15,7,20,3};
        Solution_106 s = new Solution_106();
        TreeNode treeNode = s.buildTree(inorder, postorder);
        PrintTree.print(treeNode);

    }

}
