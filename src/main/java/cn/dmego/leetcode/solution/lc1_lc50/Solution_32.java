package cn.dmego.leetcode.solution.lc1_lc50;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * [32] 最长有效括号
 * 给你一个只包含 '(' 和 ')'的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 */
public class Solution_32 {

    /**
     dp 数组定义：dp[i] 表示以 s[i - 1] 结尾的最长有效子串长度
     dp 初始化：dp[0] = 0;
     使用栈来判断括号是否有效：Deque<Integer> 栈存储 '(' 括号的下标(i - 1)
     遍历字符串 s, 当 s[i - 1] == ')' 时，直接添加到栈顶
     当 s[i - 1] == '(' 时，并且栈不为空
        此时 s[i - 1] 与栈顶 s[deque.peekFirst()] 区间位置是匹配字符串
        dp[deque.peekFirst()] 表示以 s[deque.peekFirst() - 1] 下标结尾的最长有效括号
        所以 dp[i] = dp[deque.peekFirst()] + s[deque.peekFirst(), i - 1]
        也就是 dp[i] = dp[deque.peekFirst()] + (i - deque.peekFirst())
     */
    public int longestValidParentheses(String s) {
        if (s == null || s.length() <= 1) return 0;
        // dp[i] 表示以 s[i - 1] 字符结尾的最长有效子串长度
        int[] dp = new int[s.length() + 1];
        // deque 保存 '(' 的下标
        Deque<Integer> deque = new ArrayDeque<>();
        int res = 0;
        for (int i = 1; i <= s.length(); i++) {
            char val = s.charAt(i - 1);
            // 如果 s[i - 1] 是右括号 并且 队列不为空
            if (val == ')' && !deque.isEmpty()) {
                dp[i] = dp[deque.peekFirst()] + (i - deque.peekFirst());
                // 匹配到的左括号出栈
                deque.pollFirst();
            } else if (val == '(') {
                // 如果 s[i] 是左括号，把下标 i - 1 入栈
                deque.addFirst(i - 1);
            }
            res = Math.max(dp[i], res);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution_32 solution = new Solution_32();
        String s1 = ")()(())(";
        String s2 = "(((())))()()";
        String s3 = ")()())";
        String s4 = "(())(()((())((()))((((()()()()()";
        int r1 = solution.longestValidParentheses(s4);
        System.out.println(r1);


    }
}
