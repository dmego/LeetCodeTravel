package cn.dmego.leetcode.solution.lc1_lc50;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合
 * @author dmego
 * @date 2022/01/12 17:07
 */
public class Solution_22 {
    /**
             ""
         (       )
       (   )   (   )
      ( ) ( ) ( ) ( )
     ()()()()()()()()

     数字 n 表示括号的对数，我们按照左右两个括号()来画出所有可能的结果
     发现这是一棵满二叉树，我们要想得到所有的结果，可以使用深度优先遍历

     但是这样遍历出来有很多组合情况不是有效的括号，例如 (((((, ))))), ))))(( 等
     我们需要对这些进行过滤

     我们可以发现 对于有效的左括号 ( 出现的次数一定是 n 次， 有效的右括号 ) 出现的次数也一定的 n 次
     根据这个前提，我们可以在遍历的过程中，统计 左右括号出现的次数，如果有一个次数大于 n 则结束递归。

     上面的前提不能完全过滤所有无效的组合，例如：())()(，)))((( 这种情况，左右括号数都满足条件
     但是却不是有效的括号
     这种情况可以通过 right > left 条件既可以过滤，也就是说 当组合中右括号数 > 左括号数时，已经是不满足条件的值了
     因为在遍历过程中满足条件的只有两种情况：
        1. 左括号 和 右括号相等 (())
        2. 左括号 大于 右括号数 (())(
     */

    List<String> result = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        if (n == 0) return result;
        // left right 表示左括号，右括号数量
        dfs("", n * 2, 0,0);
        return result;
    }

    public void dfs (String paths, int len, int left, int right) {
        if (left > len / 2 || right > left) return;
        if (paths.length() == len) {
            result.add(paths);
            return;
        }
        dfs(paths + "(", len, left + 1, right);
        dfs(paths + ")", len, left, right + 1);
    }

    public static void main(String[] args) {
        Solution_22 s = new Solution_22();
        List<String> strings = s.generateParenthesis(3);
        System.out.println(strings);
    }
}
