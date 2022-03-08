package cn.dmego.leetcode.solution;

import cn.dmego.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的所有路径
 * @author dmego
 * @date 2022/03/08 20:54
 */
public class Solution_257 {

    List<String> result = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        dfs(root, "");
        return result;
    }

    public void dfs(TreeNode root, String path) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            path = path + root.val;
            result.add(path);
            return;
        }
        path = path + root.val + "->";
        dfs(root.left, path);
        dfs(root.right, path);
    }
}
