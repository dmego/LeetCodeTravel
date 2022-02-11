package cn.dmego.leetcode.solution;

import cn.dmego.util.TreeNode;

/**
 * 将有序数组转换为二叉搜索树
 * @author dmego
 * @date 2022/02/11 10:19
 */
public class Solution_108 {

    public TreeNode dfs(int[] nums, int lo, int hi) {
        if (lo > hi) return null;
        int mid = (lo + hi) >> 1;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = dfs(nums, lo, mid  - 1);
        node.right = dfs(nums, mid + 1, hi);
        return node;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
       return dfs(nums, 0, nums.length - 1);
    }

}
