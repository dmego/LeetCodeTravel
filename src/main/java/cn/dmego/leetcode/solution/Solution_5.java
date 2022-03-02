package cn.dmego.leetcode.solution;

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
      中心扩展法

     */
    public String longestPalindrome2(String s) {


        return null;
    }


    public static void main(String[] args) {
        String s = "babad";
        Solution_5 solution = new Solution_5();
        String s1 = solution.longestPalindrome(s);
        System.out.println(s1);
    }

}
