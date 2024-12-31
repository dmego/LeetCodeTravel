package cn.dmego.leetcode.solution.lc401_;

/**
 * 最长回文子序列
 * @author dmego
 * @date 2022/04/24 14:10
 */
public class Solution_516 {

    /*
    1. dp 数组的定义：dp[i][j] 表示区间 [i, j] 内的最长回文子串
    2. 初始化：当 i = j 时，dp[i][j] = 1
    3. 递推公式：
        当 s[i] = s[j] 时，dp[i][j] = dp[i+1][j-1] + 2
        当 s[i] != s[j] 时，说明同时将 s[i] 和 s[j] 加入到子串不能构成回文子串，那就分表加入，看看哪个构成的回文串最长
            dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1])
    4. 遍历顺序：i 从后往前， j 从前往后
    */
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) dp[i][i] = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }

    public static void main(String[] args) {
        Solution_516 s = new Solution_516();
        int res = s.longestPalindromeSubseq("aaa");
        System.out.println(res);
    }

}
