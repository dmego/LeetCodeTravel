package cn.dmego.leetcode.solution.lc401_;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [438] 找到字符串中所有字母异位词
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 */
public class Solution_438 {

    /**
      滑动窗口：
      同 76 题类似
     */
    public List<Integer> findAnagrams(String s, String p) {
        int left = 0, right = 0;
        List<Integer> result = new ArrayList<>();
        Map<Character,Integer> windows = new HashMap<>();
        Map<Character,Integer> needs = new HashMap<>();
        int needsCut = 0;
        for (int i = 0; i < p.length(); i++) {
            needs.put(p.charAt(i), needs.getOrDefault(p.charAt(i), 0) + 1);
        }
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (needs.containsKey(c)) {
                windows.put(c, windows.getOrDefault(c, 0) + 1);
                if (needs.get(c).intValue() == windows.get(c).intValue()) {
                    needsCut++;
                }
            }
            // 当 right >= p.length() 时，固定窗口已经形成
            if (right >= p.length()) {
                // 当 needsCut 值等于 needs.size() 时，说明此时窗口是一个满足条件的子串
                if (needsCut == needs.size()) {
                    result.add(left);
                }
                c = s.charAt(left);
                left++;
                if (needs.containsKey(c)) {
                    if (needs.get(c).intValue() == windows.get(c).intValue()) {
                        needsCut--;
                    }
                    windows.put(c, windows.getOrDefault(c, 0) - 1);
                }
            }
        }
        return result;
    }

}
