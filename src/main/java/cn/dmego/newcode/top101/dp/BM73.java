package cn.dmego.newcode.top101.dp;

/**
 * 最长回文子串
 * 对于长度为n的一个字符串A（仅包含数字，大小写英文字母），请设计一个高效算法，计算其中最长回文子串的长度。
 * @author dmego
 * @date 2022/04/24 13:51
 */
public class BM73 {

    public int getLongestPalindrome (String A) {
        // dp[i][j] 表示 [i, j] 区间内子串是否是回文串
        boolean[][] dp = new boolean[A.length()][A.length()];
        int maxLen = 0;
        for (int i = A.length() - 1; i >= 0; i--) {
            for (int j = i; j < A.length(); j++) {
                if (A.charAt(i) == A.charAt(j)) {
                    if (j - i <= 1) dp[i][j] = true;
                    else if (dp[i + 1][j - 1]) dp[i][j] = true;
                    if (dp[i][j]) maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        return maxLen;
    }

}
