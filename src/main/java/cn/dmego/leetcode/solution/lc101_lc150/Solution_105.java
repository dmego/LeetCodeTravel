package cn.dmego.leetcode.solution.lc101_lc150;

import cn.dmego.util.PrintTree;
import cn.dmego.util.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 从前序和中序遍历序列构建二叉树
 * （数组中不存在重复元素）
 * @author dmego
 * @date 2022/02/23 09:55
 */
public class Solution_105 {

    /**
     解法一：分治法 + 递归
     前序遍历：根 左 右
     中序遍历：左 根 右
     我们可以发现：前序遍历的根节点始终会出现在数组的第一位，而中序遍历中，根节点一定在数组中间
     我们可以先取前序数组第一个元素 root ，也就是树的根节点
     然后去中序数组中找到根节点 root，将中序数组从根节点左右分为两半，左半边的元素是 root 的左子树节点，右半边的元素是root的右子树节点
     根据中序数组分出的左右两半数组的长度大小，将前序数组也分为两半，从root后面截到左子树数组长度，然后剩下的就是右子树数组长度
     根据前序和中序截出了两个数组，递归调用上面逻辑，直到前序和中序数组都被截完
     */
    Map<Integer, Integer> inorderCache = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 构建中序数组的 map 缓存
        for (int i = 0; i < inorder.length; i++) {
            inorderCache.put(inorder[i], i);
        }
        return dfs(preorder, 0, preorder.length - 1, inorder, 0 , inorder.length - 1);
    }

    public TreeNode dfs(int[] preorder, int preL, int preR, int[] inorder, int inL, int inR) {
        // 递归结束条件
        if (preL > preR || inL > inR) return null;
        // 新建一个根节点
        TreeNode root = new TreeNode(preorder[preL]);
        // 找出根节点在中序数组中的位置
        int rootInIndex = inorderCache.get(preorder[preL]);
        // root 节点左子树的 节点数
        int leftLen = rootInIndex - inL;
        root.left = dfs(preorder, preL + 1, preL + leftLen, inorder, inL, rootInIndex - 1);
        root.right = dfs(preorder, preL + 1 + leftLen, preR, inorder, rootInIndex + 1, inR);
        return root;
    }

    public static void main(String[] args) {
        int[] preorder = new int[]{3,9,20,15,7};
        int[] inorder = new int[]{9,3,15,20,7};
        Solution_105 s = new Solution_105();
        TreeNode treeNode = s.buildTree(preorder, inorder);
        PrintTree.print(treeNode);

    }



}
