package cn.dmego.leetcode.solution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 单词拆分II
 *
 * @author dmego
 * @date 2022/6/24 09:27
 */
public class Solution_140 {
    List<String> result = new ArrayList<>();
    LinkedList<String> str = new LinkedList<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        backtracking(0, s, wordDict);
        return result;
    }

    public void backtracking(int index, String s, List<String> wordDict) {
        if (index == s.length()) {
            result.add(String.join(" ", str));
            return;
        }

        // 横向遍历单词，取出每个单词，判断 s 从 index 下标开始有没有子串匹配当前单词
        for (String dict : wordDict) {
            // 判断 s 从 index 开始往后，能不能找到一个子串匹配字典里面的单词
            int len = dict.length();
            if (index + len <= s.length()) {
                String word = s.substring(index, index + len);
                if (word.equals(dict)) {
                    str.addLast(word);
                    // 纵向递归字符串 s ，从 index + len 下标开始
                    backtracking(index + len, s, wordDict);
                    str.removeLast();
                }
            }
        }
    }
}
