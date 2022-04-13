package cn.dmego.leetcode.solution;

import java.util.List;

/**
 * 单词拆分
 * @author dmego
 * @date 2022/04/09 11:36
 */
public class Solution_139 {

    public boolean wordBreak(String s, List<String> wordDict) {
        // dp[i] 表示长度为 i 的字符串能由 wordDict 中的字符拼接组成，dp[i] = true
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                String word = s.substring(j, i);
                if (wordDict.contains(word) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
