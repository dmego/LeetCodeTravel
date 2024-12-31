package cn.dmego.leetcode.solution.lc1_lc50;

/**
 * 10. 正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 */
public class Solution_10 {

    /**
     1. dp 数组定义：`dp[i][j]` 表示 字符串 s[0, i-1] 与 字符串规律 p[0, j-1] 是否匹配
     2. 初始化： `dp[0][0]` 表示空串 s 与 空串 p 是否匹配 ==> `dp[0][0] = true`
               `dp[0][j]` 表示空串 s 与 字符规律 p 是否匹配，这个需要遍历 p,例如当p为`a*` 时，就匹配
               `dp[i][0]` 表示字符串 s 与空串 p 是否匹配，由于p是字符规律，当p为空时，`dp[i][0] = false`
     3.状态转移方程：
        我们在求整个匹配过程时，第一层循环遍历 s, 从左到右，第二层循环遍历 p，从左到右
        3.1. 当 s.chatAt(i-1) = p.charAt(j-1) 时，`dp[i][j] = dp[i-1][j-1]`，表示字符串 s[0, i-1] 与 字符串规律 p[0, j-1] 是否匹配
             取决于字符串 s[0, i-2] 与 字符串规律 p[0, j-2] 是否匹配, 因为s[i-1] 与 p[j-1] 位置已经匹配上了。
        3.2. 当 p.charAt(j-1) = '.' 时，结果与 3.1 相同，因为 '.' 能匹配任何字符
        3.3. 当 p.charAt(j-1) = '*' 时，由于 '*' 表示匹配 0 个或多个 前面 的那个元素，有如下情况：
            3.3.1. 当前面的那个元素，也就是 p.charAt(j-2) != s.charAt(i-1) 时，根据 '*' 的特性，可以在匹配时，删除 `p[i-2 ~ i-1]`, 也就是
                   字符串 s[0, i-1] 与 字符串规律 p[0, j-1] 是否匹配 取决于字符串 s[0, i-1] 与 字符串规律 p[0, j-3] 是否匹配
                   也就是 `dp[i][j] = dp[i][j-2]`
            3.3.2 当 p.charAt(j-2) = s.charAt(i-1) 时，
                这里比较难理解，举例说明：假设 s = 'caaab', p = 'ca*b'
                   当 i = 1, j = 3 时，s[0, 0] = 'c', p[0, 2] = 'ca*', 符合 3.3.1，所以 `dp[1][3] = dp[1][1] = true`;
                   当 i = 2, j = 3 时，s[0, 1] = 'ca', p[0, 2] = 'ca*',  符合 3.3.2，因为根据 '*' 的特性，'a*' 可以表示为 'a'、'aa'、'aaa' 、'aa...aa' 等。
                   字符串 s[0, i-1] 与 字符串规律 p[0, j-1] 是否匹配只需要看 字符串 s[0, i-2] 与 字符串规律 p[0, j-1] 是否匹配即可，
                   也就是看 s[0, 0] 与 p[0, 2] 是否匹配：`dp[2][3] = d[1][3] = true`

                   当 i = 3, j = 3 时，此时 s[0, 2] = 'caa', p[0, 2] = 'ca*', `dp[3][3] = dp[2][3] = true`
                   当 i = 4, j = 3 时, 此时 s[0, 3] = 'caaa', p[0, 2] = 'ca*' `dp[4][3] = dp[3][3] = true`

               根据上面的规则，可以得出：`dp[i][j] = dp[i - 1][j]`
           3.3.3 当 p.charAt(j - 2) = '.' 时，与 3.3.2 一样
     */
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        // 定义 dp 数组
        boolean[][] dp = new boolean[m + 1][n + 1];
        // 初始化 dp[0][0]
        dp[0][0] = true;
        // 初始化 dp[0][j], 当 p 是 a*b*a*c*d*x* 这种格式(偶数位为*，奇数为是字符)，则可以匹配空串 s
        for (int j = 1; j <= n; j = j + 2) {
            if (p.charAt(j - 1) == '*') {
                if (j == 1) dp[0][j] = true;
                // 按照 * 的规则，删除 p 的 * 后面一个字符，看看能不能匹配上
                else dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*' && j > 1) {
                    if (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1)) {
                        // 当s[i-1] 与 p[j-2] 即使匹配上了，由于*的特性，也可以消去p[j-2]，当做不匹配的情况
                        // 所以这里应该是两种情况的 或(||), 只要有一种能全匹配即可
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }

        return dp[m][n];
    }


    public static void main(String[] args) {
        String s = "mississippi";

        String p = "mis*is*p*.";
        //String p = "*";
        Solution_10 so = new Solution_10();
        boolean match = so.isMatch(s, p);
        System.out.println(match);


    }
}
