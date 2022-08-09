package cn.dmego.leetcode.solution.lc1_lc50;

/**
 * [14] 最长公共前缀
 * @author dmego
 * @date 2022/03/10 08:53
 */
public class Solution_14 {


    /**
     横向遍历：
     初始化最长公共前缀 res 为第一个字符串为最长公共前缀，
     遍历字符串数组，与遍历到的字符串两两比较，更新 res
     如果 res 更新成了 "", 直接返回 ""
     如果开始比较时，就将 res 遍历完了。 直接返回 res
     遍历结束后，最后结果返回 res
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String res = strs[0];
        for (int i = 0; i < strs.length; i++) {
            int j = 0;
            // 循环条件取两个字符串中最小的长度
            for (; j < Math.min(res.length(), strs[i].length()); j++) {
                if (res.charAt(j) != strs[i].charAt(j)) break;
            }
            // 更新 res
            res = res.substring(0, j);
            // 如果 res = "" 或者 res = strs[0] 直接返回
            if (res.length() == 0 || j == strs[0].length() - 1) return res;
        }
        return res;
    }


    /**
     纵向扫描：
     将字符串数组看成一张二维表，从纵向比较每一列，如果出现某一列上的字符不是一样的，
     那么该列之前的子串就是最长公共前缀
     */
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        // 字符串的个数, 纵向比较使用
        int count = strs.length;
        // 横向比较时，取第一个字符串的长度
        int len = strs[0].length();
        for (int i = 0; i < len; i++) {
            // 纵向比较的字符
            char c = strs[0].charAt(i);
            for (int j = 1; j < count; j++) {
                // 如果一个字符串遍历结束了，这个字符串就是最长公共前缀。或者出现某一列字符不相等，这一列之前的子串就是最长公共前缀
                if (i >= strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        // 最后如果所有的字符串和第一个字符串纵向比较都相等，第一个字符串就是最长公共前缀
        return strs[0];
    }
}
