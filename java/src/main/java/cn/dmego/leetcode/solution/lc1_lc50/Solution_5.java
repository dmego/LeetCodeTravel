package cn.dmego.leetcode.solution.lc1_lc50;

/**
 * 最长回文子串
 * @author dmego
 * @date 2022/01/05 09:53
 */
public class Solution_5 {

    /**
     暴力解法
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        // 单个字符的字符串， 最长回文串就是自己
        if (len < 2) return s;
        int maxLen = 1; // 最长回文串的长度
        int start = 0; // 最长回文串的开始节点
        char[] charArr = s.toCharArray();
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (j - i + 1 > maxLen && palindrome(charArr, i, j)) {
                    maxLen = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    // 判断 [start, end] 区间字符串是否是回文
    public boolean palindrome(char[] charArr, int start, int end) {
        while (start <= end) {
            if (charArr[start] != charArr[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    /**
     动态规划解法
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
     5. 最后的结果： 定义一个变量记录最长回文子串的长度 maxLen，一个变量记录最长回文子串的开始下标 start
        每次 dp[i][j] = true 时， 更新 maxLen 和 start 的值
     */
    public String longestPalindrome2(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int maxLen = 0; // 最长回文子串的长度
        int start = 0; // 最长回文子串的尾下标
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 1 || dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }


    /**
      中心扩展法

     */
    public String longestPalindrome3(String s) {


        return null;
    }


    public static void main(String[] args) {
        String s = "babad";
        Solution_5 solution = new Solution_5();
        String s1 = solution.longestPalindrome(s);
        System.out.println(s1);
    }

}
