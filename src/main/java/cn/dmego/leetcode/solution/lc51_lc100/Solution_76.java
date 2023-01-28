package cn.dmego.leetcode.solution.lc51_lc100;

import java.util.HashMap;
import java.util.Map;

/**
 * [76] 最小覆盖子串
 *  给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 ""
 */
public class Solution_76 {

    /**
     滑动窗口：
     - 1.定义一个滑动窗口，先不断增加窗口 right 指针，直到窗口内字符能完全覆盖字符串 t
     - 2.再不断增加窗口 left 指针，缩小窗口大小，直到窗口内的子串是一个最优的覆盖子串。
     - 3.将 left 再移动一位，让窗口不满足条件，重复 1 和 2 的操作。直到 right 到达 s 字符串结尾。
     总的来说就是：通过第 1 步来找到一个 可行解，然后通过第 2 步来不断优化，直到找到 最优解。

     详细解法：
     - 定义滑动窗口的左右边界，left = right = 0, 并且窗口是左闭右开的 [left, right)。
       这样做是为了保证初始化时，窗口大小为 0，在遍历过程中，通过移动 right 和 left 来扩大缩小窗口大小
     - 如何来判断当前窗口下的子串能完全覆盖字符串 t ?
        - 定义两个 Map:
            - needs，存储字符串 t 中各个字符和这个字符的数量。
            - windows，存储当前窗口中，匹配字符串 t 中的字符和这个字符的数量。
        - 我们在扩大和缩小窗口的同时，不断更新 windows Map 中的值。
        - 定义一个 needsCut 变量，用来记录 windows Map 中有多少字符已经满足 needs 中的(数量)要求
        - 当 needsCut = needs.size() 时，说明当前窗口已经满足覆盖字符串 t 的要求了
     - 定义结果子串的起始下标 start 和长度 len。最后返回结果时，直接从字符串 s 中截取，我们在滑动窗口时，对这两个值进行更新
     - 通过 right 下标来遍历字符串 s
        - 如果当前遍历的字符 c 出现在 needs 中，说明字符 c 是一个满足条件字符。
            - 更新到 windows Map 中，也就是将 windows Map 中字符 c 的数量加 1
            - 如果 windows 中字符 c 的数量已经和 needs 中字符 c 的数量相等，说明字符 c 已经达到覆盖条件。将 needsCut + 1;
        - right++
        - 如果 needsCut == need.size(), 说明当前窗口已经满足覆盖字符串 t 的要求了，此时需要增加 left, 缩小窗口。优化覆盖子串
            - 如果 right - left < len, 说明 [left, right) 区间是一个更小的满足条件的结果子串，更新 start 和 len 值。
            - char c = s.charAt(left), 如果字符 c 出现在 needs 中
                - 如果 windows 中字符 c 的数量和 needs 中字符 c 的数量相等此时如果将字符 c 从当前窗口移除，
                 则字符 c 就不满足覆盖条件了，将 needsCut - 1;
            - 将字符 c 从当前窗口中移除
            - left++;
     - 最后返回 s 中 [start, start + len) 区间子串
     */
    public String minWindow(String s, String t) {
        // windows Map 存储 当前窗口中覆盖 t 中各字符及其个数
        Map<Character, Integer> windows = new HashMap<>();
        // needs Map 存储 字符串 t 中各字符及其个数
        Map<Character, Integer> needs = new HashMap<>();
        // 初始化 needs Map
        for (int i = 0; i < t.length(); i++) {
            // needs.getOrDefault(t.charAt(i), 0): 如果不存在，使用默认值 0
            needs.put(t.charAt(i), needs.getOrDefault(t.charAt(i), 0) + 1);
        }
        // 定义滑动窗口边界, 滑动窗口为左闭右开：[left, right)
        int left = 0, right = 0;
        // 表示当前覆盖 t 的字符总长度，当 needsCut == needs.size() 时，表示找到了一个覆盖子串
        int needsCut = 0;
        // 定义结果最小覆盖子串的起点下标，和最小覆盖子串的长度
        int start = 0, len = Integer.MAX_VALUE;

        while (right < s.length()) {
            // 待加入窗口的字符
            char c = s.charAt(right);
            // 如果字符 c 是字符串 t 中的一个字符
            if (needs.containsKey(c)) {
                // 将字符 c 添加到 windows Map 中, 并增加个数
                windows.put(c, windows.getOrDefault(c, 0) + 1);
                // 如果当前窗口 windows 中字符 c 的个数已经达到了 needs 中的数量，needsCut 值加一
                if (needs.get(c).intValue() == windows.get(c).intValue()) {
                    needsCut++;
                }
            }
            // 扩大窗口
            right++;

            // 如果已经找到了一个完全覆盖 t 的子串，移动 left 指针，优化这个子串
            while (needsCut == needs.size()) {
                // 更新最小覆盖子串的起始下标和长度
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                // 待移出窗口的字符
                c = s.charAt(left);
                // 如果字符 c 是字符串 t 中的一个字符
                if (needs.containsKey(c)) {
                    // 如果当前窗口 windows 中字符 c 的个数等于 needs 中的数量，needsCut 值减一
                    if(needs.get(c).intValue() == windows.get(c).intValue()) {
                        needsCut--;
                    }
                    windows.put(c, windows.getOrDefault(c, 0) - 1);
                }
                left++;
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }


    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        Solution_76 solution = new Solution_76();
        String window = solution.minWindow(s, t);
        System.out.println(window);

    }

}
