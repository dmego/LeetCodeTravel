package cn.dmego.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dmego
 * @date 2021/12/21 01:02
 */
public class Solution_567 {


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
