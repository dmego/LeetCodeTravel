package cn.dmego.newcode.top101.treenode;

import cn.dmego.util.TreeNode;

import java.util.LinkedList;

/**
 * 对称二叉树
 * @author dmego
 * @date 2022/03/07 14:27
 */
public class BM31 {

    /**
     解法一: 递归法
     对称二叉树是说把二叉树从根节点对折，其左子树和右子树能够重合，也就是说节点对称，且值相等
     我们可以递归根节点的左右子树，首先判断根节点左节点和右节点是不是相等，如果相等，则递归调用
     左子树的左子树，右子树的右子树：boolean res1 = dfs(left.left, right.right)
     左子树的有子树，右子树的左子树：boolean res2 = dfs(left.right, right.left)
     最后的结果 res1 && res2
     */
    public boolean isSymmetrical(TreeNode pRoot) {
        if(pRoot == null) return true;
        return dfs(pRoot.left, pRoot.right);
    }

    public boolean dfs(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        else if (left != null && right != null) {
            if (left.val != right.val) return false;
            return dfs(left.left, right.right) && dfs(left.right, right.left);
        } else {
            return false;
        }
    }

    /**
     解法二：迭代法

     */
    public boolean isSymmetrical2(TreeNode pRoot) {
        if(pRoot == null) return true;
        // 使用 LinkedList 作为双端队列，因为可以存储 null 值
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.addLast(pRoot.left);
        deque.addLast(pRoot.right);
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i= 0; i < size; i += 2) {
                // 从队列中取两个节点出来
                TreeNode left = deque.pollFirst();
                TreeNode right = deque.pollFirst();
                if (left == null && right == null) continue;
                if (left == null || right == null) return false;
                else if (left != null && right != null) {
                    if (left.val != right.val) return false;
                    // 左子树左节点，右子树右节点
                    deque.addLast(left.left);
                    deque.addLast(right.right);
                    // 左子树右节点，右子树左节点
                    deque.addLast(left.right);
                    deque.addLast(right.left);
                }
            }
        }
        return true;
    }
}
