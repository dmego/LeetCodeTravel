package cn.dmego.leetcode.codetop.easy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * @author dmego
 * @date 2022/01/04 23:46
 */
public class LC_20 {

    public boolean isValid(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                if (deque.pollLast() != map.get(c)) return false;
            } else {
                deque.addLast(c);
            }
        }
        return deque.size() == 0;
    }

}
