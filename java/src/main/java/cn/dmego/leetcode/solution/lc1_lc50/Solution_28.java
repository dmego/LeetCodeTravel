package cn.dmego.leetcode.solution.lc1_lc50;

/**
 实现 strStr() 函数。

 给你两个字符串 haystack 和 needle ，
 请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回 -1 。

 思考如果 needle 是空串，该返回什么？
    C 语言里的 strStr() 以及 java 里面的indexOf() 都是返回 0
 */
public class Solution_28 {

    /**
     解法一：直接使用 Java API
     string.substring() 截取haystack 串，然后与 needle 串比较
     */
    public static int strStr(String haystack, String needle){
        int len1 = haystack.length();
        int len2 = needle.length();
        if (len2 == 0) {
            return 0;
        }
        if (len1 == 0) {
            return -1;
        }
        for (int i = 0; i + len2 <= len1; i++) {
            if (haystack.substring(i, i + len2).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    /**
     解法二：双指针解法
     顺着上面解法的思路，如果不用内置的API, 如何遍历每个子串
     可以采用双指针：
        index1 是 haystack 的遍历指针
        index2 是 needle 的遍历指针
     循环条件： index1 < haystack.length() 时
        如果 haystack.charAt(index1) == needle.charAt(index2)
            说明匹配了字符，此时 index1++，index2++;
        如果 haystack.charAt(index1) != needle.charAt(index2)
            说明遍历过程中遇到了 字符不匹配的情况
                此时 index1 回到前一个开始的下一个位置 index1 = index1 - index2 + 1;
                index2 回到 needle 字符串的开头 index2 = 0;
     */
    public static int strStr2(String haystack, String needle) {
        int len1 = haystack.length();
        int len2 = needle.length();
        if (len2 == 0) {
            return 0;
        }
        if (len1 == 0) {
            return -1;
        }
        int index1 = 0, index2 = 0;
        while (index1 < len1) {
            if (haystack.charAt(index1) != needle.charAt(index2)) {
                index1 = index1 - index2 + 1;
                index2 = 0;
            } else {
                if (index2 == len2 - 1) {
                    return index1 - len2 + 1;
                }
                index2++;
                index1++;
            }
        }
        return -1;
    }

}
