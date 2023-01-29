package cn.dmego.leetcode.solution.lc401_;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * [567] 字符串的排列
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
 * @author dmego
 * @date 2021/12/21 01:02
 */
public class Solution_567 {

    /**
     滑动窗口，同 438， 76 题
     */
    public boolean checkInclusion3(String s1, String s2) {
        int left = 0, right = 0;
        Map<Character,Integer> windows = new HashMap<>();
        Map<Character,Integer> needs = new HashMap<>();
        int needsCut = 0;
        for (int i = 0; i < s1.length(); i++) {
            needs.put(s1.charAt(i), needs.getOrDefault(s1.charAt(i), 0) + 1);
        }
        while (right < s2.length()) {
            char c = s2.charAt(right);
            right++;
            if (needs.containsKey(c)) {
                windows.put(c, windows.getOrDefault(c, 0) + 1);
                if (needs.get(c).intValue() == windows.get(c).intValue()) {
                    needsCut++;
                }
            }
            // 当 right >= p.length() 时，固定窗口已经形成
            if (right >= s1.length()) {
                // 当 needsCut 值等于 needs.size() 时，说明此时窗口是一个满足条件的子串
                if (needsCut == needs.size()) {
                    return true;
                }
                c = s2.charAt(left);
                left++;
                if (needs.containsKey(c)) {
                    if (needs.get(c).intValue() == windows.get(c).intValue()) {
                        needsCut--;
                    }
                    windows.put(c, windows.getOrDefault(c, 0) - 1);
                }
            }
        }
        return false;
    }

    public static boolean checkInclusion(String s1, String s2) {
        // 保证 s1 长度比 s2 小
        if (s2.length() < s1.length()) return false;
        // 因为都是小写字母，用 数组记录并比较是否相同
        int[] array1 = new int[26];
        int[] array2 = new int[26];
        // 初始化数组
        for (int i = 0; i < s1.length(); i++) {
            array1[s1.charAt(i) - 'a'] += 1;
            array2[s2.charAt(i) - 'a'] += 1;
        }
        int start = 0, end = s1.length() - 1;
        while (end < s2.length()) {
            if (Arrays.equals(array1, array2)) {
                return true;
            } else {
                start++;
                end++;
                array2[s2.charAt(start - 1) - 'a'] -= 1;
                if (end < s2.length()) {
                    array2[s2.charAt(end) - 'a'] += 1;
                }
                if (Arrays.equals(array1, array2)) {
                    return true;
                }
            }
        }
        return false;
    }



    public static boolean checkInclusion2(String s1, String s2) {

        Map<Character, Integer> map = resetS1Map(s1);

        int start = 0, end = s1.length() - 1;
        while (end < s2.length()) {
            boolean flag = true;
            for (int i = start; i <= end; i++) {
                if (map.containsKey(s2.charAt(i))) {
                    if (map.get(s2.charAt(i)) == 0) {
                        flag = false;
                        break;
                    } else {
                        map.put(s2.charAt(i), map.get(s2.charAt(i)) - 1);
                    }
                } else {
                    flag = false;
                    break;
                }
            }
            if (flag) return true;
            map = resetS1Map(s1);
            start++;
            end++;
        }
        return false;
    }

    public static Map<Character, Integer> resetS1Map(String s1) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            if (map.containsKey(s1.charAt(i))) {
                map.put(s1.charAt(i), map.get(s1.charAt(i)) + 1);
            } else {
                map.put(s1.charAt(i), 1);
            }
        }
        return map;
    }

    public static void main(String[] args) {
        boolean b = checkInclusion("abc", "ccccbbbbaaaa");
        System.out.println(b);
    }
}
