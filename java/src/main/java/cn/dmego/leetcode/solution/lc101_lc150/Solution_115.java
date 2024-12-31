package cn.dmego.leetcode.solution.lc101_lc150;

import java.util.Arrays;

/**
 * 不同的子序列
 * @author dmego
 * @date 2022/04/21 10:20
 */
public class Solution_115 {

    /*
    1. dp 数组的定义: dp[i][j] 表示 以下标 i-1 结尾 s 的子序列中，以下标 j-1 结尾的 t 出现的个数
    2. 初始化：
        dp[i][0] 表示以下标 i-1 结尾 s 的子序列中，空串出现的个数，很明显都是 1，所以 dp[i][0] = 1;
        dp[0][j] 表示空串的子序列中，以下标 j-1 结尾的 t 出现的个数，当 j = 0 时，空串是空串的子序列，dp[0][0] = 1,
        否则，dp[0][j] = 0, 空串的子序列只有空串。
    3. 递推公式：
        当 s[i - 1] = t[j - 1] 时，说明 s 在下标 i-1 位置的字符和 t 在下标 j-1 位置的字符相同，此时分为两种情况：
            使用 s[i - 1] 参与匹配子序列：dp[i][j] = dp[i - 1][j - 1]:
                因为 s[i-1] 和 t[j-1]能匹配上，t (0~j-1) 出现的个数和 以下标i-2结尾s的子序列中，以下标j-2结尾的t出现的个数相同
            不使用 s[i - 1] 参与匹配子序列：dp[i][j] = dp[i - 1][j]：使用 s[i-1] 的前一个s[i-2] 的结果，

        当 s[i - 1] != t[j - 1]时，不能使用 s[i - 1] 参与匹配子串，dp[i][j] = dp[i - 1][j]
    4. 遍历顺序，从前往后
    5. 结果：最后返回 dp[s.length()][t.length()]
    */
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        Arrays.fill(dp[0], 0);
        for (int i = 0; i <= s.length(); i++)
            dp[i][0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[s.length()][t.length()];
    }

}
