package cn.dmego.leetcode.solution.lc350_lc400;

/**
 * 判断子序列
 * @author dmego
 * @date 2022/04/20 10:44
 */
public class Solution_392 {

    // 双指针解法
    public boolean isSubsequence(String s, String t) {
        if (s == null || s.length() == 0) return true;
        if (t == null || t.length() == 0) return false;
        int i = 0;
        int jj = 0;
        for (; i < s.length(); i++) {
            boolean res = false;
            for (int j = jj; j < t.length(); j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    res = true;
                    jj = j + 1;
                    break;
                }
            }
            if (!res) return false;
        }
        return true;
    }

    /*
    题目要求：判断 s 是否是 t 的子序列
    动态规划解法：
    1. dp 数组定义：dp[i][j] 表示 s (0~-i-1) t (0~j-1) 相同的子序列长度
    2. 递推公式：
        如果 s[i - 1] = t[i - 1], dp[i][j] = dp[i - 1][j - 1] + 1;
        因为要判断 s 是否是 t 的子序列，s 需要一个一个字符判断，而 t 则可以对字符进行删减操作
        当 s[i - 1] != t[i - 1] 时，s[i - 1] 需要与 t[i - 2] 进行比较
        dp[i][j] = dp[i][j - 1]
    3. 初始化：dp[0][0] 初始化为 0
    4. 遍历顺序：从前往后
    5. 最后的结果：dp[s.length()][t.length()] 数组定义为两个字符串相同子序列的长度，如果值等于 s.length(), 说明 s 是 t 的子序列
    */
    public boolean isSubsequence2(String s, String t) {
        if (s == null || s.length() == 0) return true;
        if (t == null || t.length() == 0)return false;
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[s.length()][t.length()] == s.length();
    }
}
