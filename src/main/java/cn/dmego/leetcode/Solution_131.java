package cn.dmego.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 输入：s = "aab"
 输出：[["a","a","b"],["aa","b"]]

 首先读懂题意：题目求所有的切割方案，而不是求一组
 如何切割：
    例如先切割到 a 那么结下来可以切割的子串就是 ab --> 再切割 a 剩下的 子串就是 b
    先切割到 aa, 剩下可以切割的子串就是 b
    先切割到 aab, 剩下没有可以切割的子串

 上诉切割子串的方式满足回溯法求解
    横向遍历：横向切割子串 a aa aab , 切割的位置一步步后移
    纵向遍历：切割到a 后，纵向切割的子串就变成了 ab 所有纵向递归的启点就是 i + 1

 回溯递归如何结束
    切割到字符串末尾后，也就是找到了一组满足条件的结果

 如何判断回文：采用头尾双指针遍历法
 */
public class Solution_131 {
    List<List<String>> result = new ArrayList<>();
    LinkedList<String> list = new LinkedList<>();

    public void backtracking(String s, int index){
        // index 表示要切割子串的起点，超过 s 的长度表示已经到达子串最后了
        // index == s.length() -1 时，是整个字符串 s
        if (index >= s.length()) {
            result.add(new ArrayList<>(list));
        }
        // 横向遍历：
        for (int i = index; i < s.length(); i++) {
            // 判断 子串 index ~ i 是不是回文串
            if (isPalindrome(s, index, i)) {
                list.add(s.substring(index, i + 1));
            } else {
                continue;
            }
            backtracking(s, i + 1);
            list.removeLast();
        }
    }

    // 判断 字符串 s 中 start ~ end 之间的子串是不是回文
    public boolean isPalindrome(String s, int start, int end) {
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) return false;
            start++;
            end--;
        }
        return true;
    }

    public List<List<String>> partition(String s) {
        backtracking(s, 0);
        return result;
    }
}
