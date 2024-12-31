package cn.dmego.leetcode.solution.lc1_lc50;

/**
 * [14] 最长公共前缀
 * @author dmego
 * @date 2022/03/10 08:53
 */
public class Solution_14 {

    /**
     纵向扫描+横向遍历
     index 表示纵向扫描时，需要比较的字符下标。
     取第一个字符为比较基准
     */
    public String longestCommonPrefixF(String[] strs) {
        int index = 0;
        String f = strs[0];
        // 第一个字符如果为空，直接返回空串
        if (f == null || f.length() == 0) return "";
        // 纵向扫描
        while (true) {
            // 如果下标 index 已经到第一个字符 f 的末尾
            if (index >= f.length()) {
                // 直接返回当前的最长公共前缀
                return f.substring(0, index);
            }
            // 取第一个字符 f 当前下标的字符 c
            char c = f.charAt(index);
            // 横向遍历每个字符串
            for (int i = 1; i < strs.length; i++) {
                String s = strs[i];
                // 如果字符串为空，获取当前下标 index 处的字符不等于 c, 说明当前下标 index 已经不满足公共前缀要求
                if (s == null || s.length() == 0 || index >= s.length() || s.charAt(index) != c) {
                    // 直接返回当前的最长公共前缀
                    return f.substring(0, index);
                }
            }
            // 字符下标往后移动一位
            index++;
        }
    }

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
