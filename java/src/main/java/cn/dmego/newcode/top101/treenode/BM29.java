package cn.dmego.newcode.top101.treenode;

import cn.dmego.util.TreeNode;

/**
 * 二叉树中和为某一值的路径(一)
 * @author dmego
 * @date 2022/03/05 16:15
 */
public class BM29 {

    /**
     采用 DFS 前序遍历二叉树，将遍历时遇到的节点时依次相加，当遍历到叶子节点后，判断当前累加的值是否等于目标值
     如果是则返回 true, 否则返回 false
     */
    public boolean hasPathSum (TreeNode root, int sum) {
        if (root == null) return false;
        return dfs(root, 0, sum);
    }

    // cur 是当前的累加和
    public boolean dfs (TreeNode root, int cur, int sum) {
        // 如果节点是 null, 直接返回 false
        if (root == null) return false;
        // 节点累加值
        cur += root.val;
        // 如果是叶子节点，判断单前累加值是否等于目标值sum,是则返回 true
        if (root.left == null && root.right == null) {
            return cur == sum;
        }
        // 递归求 root 的左子树结果
        boolean left = dfs(root, cur, sum);
        // 递归求 root 右子树结果
        boolean right = dfs(root, cur, sum);
        // 结果返回：只有左右子树其中一个递归下去找到了满足条件的叶子节点就行了
        return left || right;
    }

}
