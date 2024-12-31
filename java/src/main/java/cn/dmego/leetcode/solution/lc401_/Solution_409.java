package cn.dmego.leetcode.solution.lc401_;

import java.util.HashMap;
import java.util.Map;

/**
 * [409] 最长回文串
    给定一个包含大写字母和小写字母的字符串s，返回通过这些字母构造成的 最长的回文串。
 * 在构造过程中，请注意 区分大小写 。比如"Aa"不能当做一个回文字符串。
 */
public class Solution_409 {

    /**
     * 使用 HashMap
     */
    public int longestPalindrome(String s) {
        if (s.length() == 1) return 1;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.get(s.charAt(i)) == null ? 1 : map.get(s.charAt(i)) + 1);
        }
        int res = 0;
        boolean one = false;
        for (Integer value : map.values()) {
            if (value % 2 == 1 & !one) one = true;
            res += value / 2;
        }
        return one ? res * 2 + 1 : res * 2;
    }

    /**
     * 优化空间，使用 int[128] 来表示大小写字母：a:1,b:2 ...
     */
    public int longestPalindrome2(String s) {
        if (s.length() == 1) return 1;
        int[] cache = new int[128];
        for (int c : s.toCharArray()) {
            cache[c]++;
        }
        // odd 表示不能参与构成回文串的字符个数
        int odd = 0;
        for (int n : cache) {
            // 如果 cache[n] 所代表的字符出现次数是奇数，那么那个多的一个数就不能参与构成回文串。
            if (n % 2 == 1) odd++;
        }
        // 如果 odd != 0, 在构建回文串时，中间可以加 1 个出现次数是奇数的字符。
        return s.length() - odd + (odd != 0 ? 1 : 0);
    }


    public static void main(String[] args) {
        String s = "abccccdd";
        Solution_409 so = new Solution_409();
        int res = so.longestPalindrome2(s);
        System.out.println(res);
    }

}
