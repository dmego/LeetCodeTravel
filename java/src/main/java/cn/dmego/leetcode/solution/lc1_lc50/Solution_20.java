package cn.dmego.leetcode.solution.lc1_lc50;

import java.util.Stack;

/**
 * 有效的括号
 */
public class Solution_20 {

    //  入栈：{[(    出栈：)]}
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return false;
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ')') {
                if (stack.isEmpty() || stack.pop() != '(')
                    return false;
            } else if (chars[i] == ']') {
                if (stack.isEmpty() || stack.pop() != '[')
                    return false;
            } else if (chars[i] == '}') {
                if (stack.isEmpty() || stack.pop() != '{')
                    return false;
            } else {
                stack.push(chars[i]);
            }
        }
        return stack.isEmpty();
    }

}
