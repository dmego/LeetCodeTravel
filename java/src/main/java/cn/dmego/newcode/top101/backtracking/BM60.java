package cn.dmego.newcode.top101.backtracking;

import java.util.ArrayList;

/**
 * 括号生成
 * @author dmego
 * @date 2022/03/14 11:05
 */
public class BM60 {
    /**
     n 代表 括号的对数，也就是有 n 个 ( 和 n 个 ), 如果我们不考虑括号是否有效
     有多种排列？我们可以按照二叉树的形式来求出这些排列
     二叉树左节点是 （ 右节点是 ），我们从 根节点 "" 开始对这棵树进行深度优先遍历
     递归结束： 遍历节点数达到 2n 个，遍历结束
     如何判断括号是否有效：
        按照根左右， 一定是先遍历左节点，后遍历右节点，如果出现右节点数(或左节点) > n，不符合条件
        如果出现左节点数 < 右节点数，不符合
     */
    ArrayList<String> result = new ArrayList<>();
    public ArrayList<String> generateParenthesis (int n) {
        // write code here
        if (n == 0) return result;
        dfs(2 * n, "", 0, 0);
        return result;
    }

    public void dfs(int m, String s, int l, int r) {
        if (l > m/2 || r > m/2) return;
        if (r > m) return;
        if (s.length() == m) {
            result.add(s);
        }
        dfs(m, s + "(", l + 1, r);
        dfs(m, s + ")", l, r + 1);
    }

    public static void main(String[] args) {
        BM60 bm = new BM60();
        ArrayList<String> strings = bm.generateParenthesis(2);
        System.out.println(strings);

    }

}
