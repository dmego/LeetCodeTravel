package cn.dmego.leetcode.solution.lc401_;

import cn.dmego.util.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 两数之和 IV - 输入二叉搜索树
 *
 * 给定一个二叉搜索树 root 和一个目标结果 k，如果二叉搜索树中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 *
 * @author dmego
 * @date 2022/10/10 19:43
 */
public class Solution_653 {

    /**
     解法一：中序遍历 + 哈希表
     时间复杂度：O(n), 空间复杂度O(n)
     */
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        // 使用 Set, 因为二叉搜索树，节点值不会重复
        Set<Integer> cache = new HashSet<>();
        return dfs(root, cache, k);
    }

    public boolean dfs(TreeNode root, Set<Integer> cache, int k) {
        if (root == null) return false;
        if (cache.contains(k - root.val)) return true;
        cache.add(root.val);
        return dfs(root.left, cache, k) || dfs(root.right, cache, k);
    }

    /**
     解法二：不额外使用空间，空间复杂度O(1), 时间复杂度O(nlogn)
     二叉树搜索树特性是，左子树 < 根 < 右子树，如果我们从前序遍历角度看，结果是有序的
     左子树的最后一个左节点是最小值，右子树的最后一个右节点是最大值
     我们可以根据这个特定，使用首尾双指针的方式来查找是否存在满足 之和结果为 k 的两个节点

     */
    public boolean findTarget2(TreeNode root, int k) {
        if (root == null) return false;

        TreeNode left = root;
        TreeNode right = root;

        // 找到左子树的最左节点
        while (left.left != null) {
            left = left.left;
        }
        // 找到右子树的最右节点
        while (right.right != null) {
            right = right.right;
        }
        // left, right 双指针循环遍历查找
        while (left != right) {
            int res = left.val + right.val;
            // res = k, 说明找到了，直接返回 true
            if (res == k) {
                return true;
            }
            // res < k, 说明 left 值太小，需要++，也就是找到比 left 大的第一个元素
            else if (res < k) {
                left = findNextLeft(root, left);
            }
            // res > k，说明 right 值太大，需要--，也就是找到比 right 小的第一个元素
            else if (res > k) {
                right = findNextRight(root, right);
            }
        }
        return false;
    }

    // 在二叉搜索树 root 的左子树上，找到比 node 大的第一个元素，注意返回值区间是 (node.val, root.val]
    private TreeNode findNextLeft(TreeNode root, TreeNode node) {
        TreeNode pre = null;
        TreeNode curr = root;
        while (curr != null) {
            if (curr.val > node.val) {
                pre = curr;
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return pre;
    }

    // 在二叉搜索树 root 的右子树上，找到比 node 小的第一个元素，注意返回值区间是 [root.val, node.val)
    private TreeNode findNextRight(TreeNode root, TreeNode node) {
        TreeNode pre = null;
        TreeNode curr = root;
        while (curr != null) {
            if (curr.val < node.val) {
                pre = curr;
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        return pre;
    }





    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        Solution_653 s = new Solution_653();
        boolean target = s.findTarget(root, 4);
        System.out.println(target);
    }

}
