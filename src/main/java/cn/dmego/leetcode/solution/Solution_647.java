package cn.dmego.leetcode.solution;

/**
 * 回文子串
 * @author dmego
 * @date 2022/04/24 10:34
 */
public class Solution_647 {

    /**
        1. dp 数组的定义：dp[i][j] 表示区间 [i, j] 范围内的子串是否是回文串
        2. 递推公式：根据 s[i] 与 s[j] 的关系，主要分为两种情况：
            当 s[i] != s[j] 时: [i, j] 区间子串一定不是回文
            当 s[i] == s[j] 时，根据 i 与 j 的位置不同，分为 三 种情况:
                当 i == j 时，一个字符是回文，dp[i][j] = true
                当 |i - j| = 1 时，i 与 j 紧挨着， 如 "aa", dp[i][j] = true;
                否则我们就要判断 [i+1, j-1] 区间是否为回文，dp[i][j] = dp[i+1][j-1];
        3. 初始化: dp[i][j] 初始化都是 false;
        4. 遍历顺序：我们从递推公式中可以看到有一项 dp[i][j] = dp[i+1][j-1] 可以看出，
            在求 i 时 i+1 必须已知，在求 j 时， j-1 必须已知
            所以需要从下到上，从左到右进行遍历
        5. 最后的结果：当判断一个回文时， res +1, 最后返回 res;
        */
    public int countSubstrings(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int res = 0;
        // i 从后往前遍历
        for (int i = s.length() - 1; i >= 0 ; i--) {
            // j 从前往后遍历
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 1) dp[i][j] = true;
                    if (i < s.length() - 1 && j > 0 && dp[i+1][j-1]) dp[i][j] = true;
                    if (dp[i][j]) res += 1;
                }
            }
        }
        return res;
    }



    /**
     解法二： 暴力破解法
     */
    public int countSubstrings2(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (isPalindromic(s, j, i)) {
                    res += 1;
                }
            }
        }
        return res;
    }

    // 判断字符串是否是回文串
    public boolean isPalindromic(String s, int start, int end) {
        if (start > end)
            return false;
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution_647 s = new Solution_647();
        int res = s.countSubstrings("abcbac");
        System.out.println(res);
    }
}
