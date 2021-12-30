package cn.dmego.leetcode.hot100.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dmego
 * @date 2021/12/30 23:48
 */
public class LC_3 {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int maxLen = 0;
        Map<Character, Integer> map = new HashMap<>();
        // 滑动窗口 开始，结束节点
        for (int start = 0, end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            // 如果 滑动窗口内包含 c 元素
            if (map.containsKey(c)) {
                // 重新指定滑动窗口的开始位置为 （窗口内 c 元素位置+1）
                // 为什么要用 Max 判断，有可能 c 元素不在窗口内，但是因为 map 中元素不会删除，会被查询出来
                start = Math.max(start, map.get(c) + 1);
            }
            map.put(c, end);
            // 每次都更新 maxLen
            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;
    }


    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) return 0;
        int maxLen = 0;
        int[] map = new int[128];
        for (int start = 0, end = 0; end < s.length(); end++) {
            if (map[s.charAt(end)] != 0) {
                // map[s.charAt(end)] 不用+1, map[] 赋值的时候已经 +1 了
                start = Math.max(start, map[s.charAt(end)]);
            }
            // 这里 +1, 保证 map[] > 0,
            map[s.charAt(end)] = end + 1;
            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LC_3 lc = new LC_3();
        int length = lc.lengthOfLongestSubstring("abcabcbb");
        System.out.println(length);
    }
}
