package cn.dmego.leetcode.solution;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
    给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
 */
public class Solution_3 {

    /**
     解法一：滑动窗口 + Set
     对于这题，如果我们采用暴力解法，思路是从头开始遍历 s 字符串的每个字符，来生成子串 res
     对于每次遍历到的字符：
        如果在子串中不存在，就加到子串的末尾；
        如果在子串中存在，那么就从头一个一个删除字符，直到删除重复字符。之后子串的起始位置变成了重复字符的下一个。
        然后把遍历到的字符加到子串的末尾。
        对于上面子串的操作，我们可以想到使用滑动窗口来解题。
     1. 使用 left 和 right 指针表示子串(窗口)的左右边界。除此之外，我们还需要定义一个结果 res
     2. 为了方便找出重复的字符，我们可以使用 Set 来解决：存储字符
     3. 遍历 字符串 s, 循环条件是 right < s.length
        3.1 判断遍历到的 chars = s.charAt(right) 是否在 Set 中存在:
        3.2 如果存在则说明遇到了重复字符, 删除Set中的 s.charAt(left) 字符，left++, 相当于一步步缩写窗口左边界，直到左边界移动到重复字符的下一个位置
        3.3 如果不存在，则将右边界字符添加到 Set 中，窗口右边界右移 right++;
     */
    public static int lengthOfLongestSubstring4(String s) {
        int len = s.length();
        Set<Character> set = new HashSet<>();
        int left = 0, right = 0, res = 0;
        while (right < len) {
            // 如果 Set 中存在重复字符
            if (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));// 删除左边界值
                left++; // 滑动窗口左边界向右移动
            } else {
                // 如果 Set 中不存在重复字符
                set.add(s.charAt(right)); // 将字符添加到Set 中
                res = Math.max(res, right - left + 1); // 计算子串最大值
                right++; // 滑动窗口右边界向右移动
            }
        }
        return res;
    }


    /**
     解法二: 滑动窗口 + hashMap
        1. 使用 Set 时，我们的左边界需要一步一步移动，这样效率太低
        2. 为了一步就能将左边界移动到正确位置，我们可以使用 Map 来解决：存储字符与对应的下标
        3. 遍历 s 字符串，循环条件是 end < s.length
            3.1 判断遍历到的 s.charAt(end) 是否在 Map 中存在，如果存在则说明遇到了重复字符
                此时，我们需要先取出 map 中 重复字符的 value 值。
                3.1.1 如果 value > start : 说明重复的字符确实在窗口内，我们需要把窗口左边界移动到 value + 1 位置(重复字符的下一个)。
                        start = value;
                3.1.2 如果 value < start : 说明重复字符串不在窗口内(出现这种情况的原因是因为map中的元素不会删除，只会更新)。
                        start 位置不变
            2.2 将 s.charAt(end) 添加到 map 中, map.put(s.charAt(end), i)
                注意：这里 key 是 字符，value 是 字符的下标。也就是说如果有多个重复的字符，
                    value 的值是最新遍历到的。
            2.3 更新 res 结果值 res = Math.max(res, end - start + 1); end - start + 1 就是窗口的大小。
     */
    public static int lengthOfLongestSubstring(String s) {
        int len = s.length();
        Map<Character,Integer> map = new HashMap<>();
        int start = 0, res = 0;
        for (int end = 0; end < len; end++) {
            char chars = s.charAt(end);
            if (map.containsKey(chars)) {
                start = Math.max(start, map.get(chars) + 1);
            }
            map.put(chars, end);
            res = Math.max(res, end - start + 1);

        }
        return res;
    }

    /**
     解法三：滑动窗口 + 打桶查询
     字符可以由 ASCII 码表示，而且是唯一的，标准的ASCII码共有 128 位
     我们可以不用使用 Map 来判断重复，直接使用 ASCII 数组即可
        数组下标是 字符的 ASCII 码的值
        数组的值就是字符在 字符串 S 中的下标
     */
    public static int lengthOfLongestSubstring3(String s) {
        int len = s.length();
        int[] ascii = new int[128];
        int left = 0, res = 0;
        for (int right = 0; right < len; right++) {
            char chars = s.charAt(right);
            if (ascii[chars] != 0) {
                // 这里与 解法一中的逻辑是一样的
                left = Math.max(left, ascii[chars]);
            }
            // 这里 +1 提前记录遇到重复字符时，left边界的值
            // 要不然会因为数组的初始化值是 0 而产生问题
            ascii[chars] = right + 1;
            res = Math.max(res, right - left +1);
        }
        return res;
    }

    /**
     解法四：滑动窗口 + indexOf(Char ch, int startIndex)
     我们可以用String提供的 indexOf() 方法来对子串中重复的字符进行判重
        s.indexOf(Char ch, int startIndex) 返回从下标 startIndex 开始查找，第一个等于 ch 字符的下标
        当我们在使用滑动窗口的过程中，我们可以通过 indexOf 方法返回的位置，确实窗口内是否存在重复字符
        从窗口左边界 left 位置开始查找字符 chars 出现的位置 index
            1. 如果 index > right, 说明 [left, right] 窗口内没有重复字符
            2. 如果 index <= right，说明重复字符在窗口中, 则窗口就要变成 [index + 1, right]
     */
    public static int lengthOfLongestSubstring2(String s) {
        int len = s.length();
        int left = 0, res = 0;
        for (int right = 0; right < len; right++) {
            char chars = s.charAt(right);
            // 从 left 位置开始查找字符 chars 出现的位置 index
            //  1. 如果 index > right, 说明 [left, right] 窗口内没有重复字符
            //  2. 如果 index <= right，说明重复字符在窗口中, 则窗口就要变成 [index + 1, right]
            int index = s.indexOf(chars, left);
            if (index < right) {
                left = index + 1;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }


    public static void main(String[] args) {
        String s = "pwwkew";
        int length = lengthOfLongestSubstring4(s);
        System.out.println(length);
    }
}
