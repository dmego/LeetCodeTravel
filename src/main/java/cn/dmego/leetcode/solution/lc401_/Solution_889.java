package cn.dmego.leetcode.solution.lc401_;


import cn.dmego.util.PrintTree;
import cn.dmego.util.TreeNode;

import java.util.HashMap;

/**
 * 根据前序和后序遍历构造二叉树
 *
 * @author dmego
 * @date 2022/10/8 12:48
 */
public class Solution_889 {

    /**
     前序遍历: 根-左子树-右子树
     后序遍历：左子树-右子树-根
     我们只需要知道左，右子树有多少个节点，就可以进行分组，递归构造出二叉树
     假设左子树的长度为 L, 则 pre[1] 是左子树的第一个节点，也就是左子树的根，
     则 pre[1] 在后序遍历中的位置，也就是左子树的根，也就是后序遍历左子树的最后一个节点
     L = indexOf(post[pte[1]]) + 1
     我们根据 L 的长度就可以将 前序，后序遍历的数组各分为三个部分
     前序：根-左子树-右子树：[0] [1, L) [L, len]
     后序：左子树-右子树-根：[0, L) [L, len) [len]
     */
    HashMap<Integer, Integer> postCache = new HashMap<>();
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        if (preorder == null || postorder == null) return null;
        int len = preorder.length;
        for (int i = 0; i < len; i++) {
            postCache.put(postorder[i], i);
        }
        return dfs(preorder, 0, len - 1, 0, len - 1);
    }

    public TreeNode dfs(int[] preorder, int pl, int pr, int ol, int or) {
        if (pl > pr || ol > or) return null;
        TreeNode root = new TreeNode(preorder[pl]);
        // 只有一个节点时，直接返回
        if (pl == pr) return root;
        // L 表示左子树的长度，左子树的第一个节点在前序遍历的下标是 pl + 1
        int L = postCache.get(preorder[pl + 1]) - ol + 1;
        root.left = dfs(preorder, pl + 1, pl + L, ol, ol + L - 1);
        root.right = dfs(preorder, pl + L + 1, pr, ol + L, or - 1);
        return root;
    }

    public static void main(String[] args) {
        int[] preorder = {1,2,4,5,3,6,7};
        int[] postorder = {4,5,2,6,7,3,1};
        Solution_889 s = new Solution_889();
        TreeNode treeNode = s.constructFromPrePost(preorder, postorder);
        PrintTree.print(treeNode);

    }
}
