package cn.dmego.newcode.top101.treenode;

import cn.dmego.util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 输出二叉树的右视图
 * 先根据二叉树的先序和中序恢复二叉树，然后输出二叉树的右视图
 * 二叉树每个节点的值在区间[1,10000]内，且保证每个节点的值互不相同
 * @author dmego
 * @date 2022/03/15 16:11
 */
public class BM41 {

    Map<Integer, Integer> middleCache = new HashMap<>();
    public int[] solve (int[] xianxu, int[] zhongxu) {
        if (xianxu == null || xianxu.length == 0 ||
                zhongxu == null || zhongxu.length == 0) return new int[]{};
        // 将中序数组缓存 (值， 下标)
        for (int i = 0; i < zhongxu.length; i++) {
            middleCache.put(zhongxu[i], i);
        }
        TreeNode root = recoverTree(xianxu,0, xianxu.length - 1, 0, zhongxu.length - 1);
        int[] res = rightView(root);
        return res;
    }


    // 根据二叉树的先序和中序恢复二叉树
    public TreeNode recoverTree(int[] pre, int pL, int pR, int mL, int mR) {
        if (pL > pR || mL > mR) return null;
        // 新建根节点, 前序数组的第一个节点就是根节点
        TreeNode root = new TreeNode(pre[pL]);
        // 在中序数组中找到根节点下标
        int mIndex = middleCache.get(pre[pL]);
        // 左子树的数组长度
        int leftLen = mIndex - mL;
        root.left = recoverTree(pre, pL + 1, pL + leftLen, mL, mIndex - 1);
        root.right = recoverTree(pre,pL + leftLen + 1, pR, mIndex + 1, mR);
        return root;
    }

    // 求二叉树的右视图(层序遍历，取每层最右边的元素)
    public int[] rightView(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addFirst(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = deque.removeLast();
                list.add(node.val);
                if (node.left != null) deque.addFirst(node.left);
                if (node.right != null) deque.addFirst(node.right);
            }
            result.add(list);
        }
        int[] res = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i).get(result.get(i).size() - 1);
        }
        return res;
    }

    public static void main(String[] args) {
        BM41 bm = new BM41();
        int[] solve = bm.solve(new int[]{1, 2, 4, 5, 3}, new int[]{4, 2, 5, 1, 3});
        System.out.println(Arrays.toString(solve));

    }


}
