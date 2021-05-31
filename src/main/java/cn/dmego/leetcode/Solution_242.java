package cn.dmego.leetcode;

import java.util.Arrays;

/**
 242. 有效的字母异位词
 */
public class Solution_242 {

    /**
        解法一：
        先把两个字符串转为数组，对数组进行排序，然后判断两个数组是否相等。
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        Arrays.sort(sArr);
        Arrays.sort(tArr);
        return Arrays.equals(sArr, tArr);
    }

    /**
     解法二：使用哈希表记录频次 table（用数组代替优化空间）
     - 遍历字符串s, 统计每个字符 char 出现的频次
     - 遍历字符串t, table[char] 中的频次 -1，判断 table[char] < 0，如果是
     说明 s 与 t 中字符出现频率对不上，不满足条件
     */
    public static boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        // 因为是小写字母，定义一个 26 大小的数组记录每个字符出现频次
        // 如果输入字符串包含 unicode 字符，则这块我们使用 hashMap 来记录
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            table[t.charAt(i) - 'a']--;
            if (table[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }


}
