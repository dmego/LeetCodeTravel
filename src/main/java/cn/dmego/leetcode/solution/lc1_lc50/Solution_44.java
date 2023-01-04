package cn.dmego.leetcode.solution.lc1_lc50;

/**
 * [44] 通配符匹配
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。

 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 */
public class Solution_44 {

    /**
     与第 10 题的区别
        '?' 与 '.' 规则一样，都是匹配任何单个字符
        '*' 的规则不同，本题是匹配任意字符串，包括空串，而第10题是匹配0个或多个 '*' 前一个字符

     dp 数组定义：dp[i][j] 表示 s[0, i-1] 与 p[0, j-1] 是否匹配
     初始化：
        dp[0][0] = true
        dp[i][0] = false
        dp[0][j],也就是空字符串与 p[0, j-1] 是否匹配。此时，只有当 p[0, j-1] 全是 '*' 时,dp[0][j] = true
     状态转移方程：
        当 s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?', dp[i][j] = dp[i - 1][j - 1]
        当 p.charAt(j - 1) == '*' 时，
            当 s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.' 时，
                1.'*' 位置可以删除，所以 dp[i][j] = dp[i][j - 1]
                2.因为 '*' 能匹配任意字符串，所以 可以看 s[0, i-2] 与 p[0, j-1] 是否匹配，所以 dp[i][j] = dp[i - 1][j]
            当 s.charAt(i - 1) != p.charAt(j - 2) 时，同上，dp[i][j] = dp[i - 1][j]
            所以 dp[i][j] = dp[i][j - 1] || dp[i - 1][j]

     */
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') dp[0][j] = dp[0][j - 1];
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }

            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {

        String s = "acdcb";
        String p = "a*c?b";
        //String p = "*a*b";
        Solution_44 solution = new Solution_44();
        boolean match = solution.isMatch(s, p);
        System.out.println(match);


    }

}
