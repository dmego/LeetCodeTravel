package cn.dmego.jzoffer;

import cn.dmego.util.BTreePrinter;
import cn.dmego.util.TreeNode;

/**
 * 剑指 Offer 26. 树的子结构
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 */
public class Offer_26 {

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) return false;
        return preOrder(A, B);
    }

    // 前序遍历二叉树 A
    public boolean preOrder(TreeNode A, TreeNode B) {
        boolean res = false;
        // 当遍历到节点值等于 树 B 的根节点时，使用 dfs(A,B)来判断是不是相同的结构
        if (A.val == B.val) {
            res = dfs(A, B);
        }
        // 如果不是，继续遍历 A 的左子树或者右子树，
        if (!res) {
            boolean left = false;
            if (A.left != null) {
                left = preOrder(A.left, B);
            }
            boolean right = false;
            if (A.right != null) {
                right = preOrder(A.right, B);
            }
            // 只要有一个子树与 B 是相同的结构，树 A 就与 B 是相同的结构
            res = left || right;
        }
        return res;
    }

    // 深度优先遍历判断 B 是否是 A 的子结构
    public boolean dfs(TreeNode A, TreeNode B) {
        // 如果 A == null 但是 B != null, 说明 B 一定不是 A 的子结构
        if (A == null && B != null) return false;
        // 如果 B == null, 不管 A 是什么，都是子结构
        if (B == null) return true;
        if (A.val != B.val) return false;
        // 判断 A 与 B 的左子树是否满足子结构要求
        boolean b1 = dfs(A.left, B.left);
        // 判断 A 与 B 的右子树是否满足子结构要求
        boolean b2 = dfs(A.right, B.right);
        return b1 && b2;
    }

    public static void main(String[] args) {
        Offer_26 test = new Offer_26();

        TreeNode A = new TreeNode(3);
        TreeNode al = new TreeNode(4);
        TreeNode ar = new TreeNode(5);
        TreeNode all = new TreeNode(1);
        TreeNode alr = new TreeNode(2);
        A.left = al;
        A.right = ar;
        al.left = all;
        al.right = alr;
        BTreePrinter.printNode(A);

        TreeNode B = new TreeNode(4);
        TreeNode bl = new TreeNode(1);
        B.left = bl;
        BTreePrinter.printNode(B);

        boolean res = test.isSubStructure(A, B);
        System.out.println(res);

    }
}
