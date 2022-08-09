package cn.dmego.leetcode.solution.lc401_;

/**
 * 两个字符串的删除操作
 * @author dmego
 * @date 2022/04/21 10:34
 */
public class Solution_583 {

    /**
    解法一：最长公共子序列的思路
    求两个字符串要想达到相同，所需要进行删除的最少字符数
    换一种思路就是，求出两个字符串的最长公共子序列的长度 len，这个长度就是最终两个字符串做删除之后达到相同时的长度
    那么需要删除的字符数就是两个字符串的长度分别减去 len ，然后相加就是结果
    */
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        int len = dp[word1.length()][word2.length()];
        return (word1.length() - len) + (word2.length() - len);
    }

    /**
    解法二：不同的子序列思路
    1. dp 数组的定义：dp[i][j] 表示以下标 i-1 结尾的 word1 和 以下标 j-1 结尾的 word2 要达到相等，需要进行最少的删除操作
    2. 初始化：
        dp[i][0] 表示下标为 i-1 结尾的 word1 成为 空串要进行的最少删除操作，dp[i][0] = i, 都删除后就是空串。
        dp[0][j] 同样的道理，dp[0][j] = j
    3. 递推公式：
        当 word1[i-1] = word2[j-1] 时，不需要进行删除，dp[i][j] = dp[i - 1][j - 1];
        当 word1[i-1] != word2[j-1] 时，有 3 种情况：
            删除 word1[i-1]: dp[i][j] = dp[i - 1][j] + 1
            删除 word2[j-1]: dp[i][j] = dp[i][j - 1] + 1
            word1[i-1] 和 word2[j-1] 都删除：dp[i][j] = dp[i - 1][j - 1] + 2
            要求最小值，去这三种情况的最小值即可
    */
    public int minDistance2(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++)
            dp[i][0] = i;
        for (int j = 0; j <= word2.length(); j++)
            dp[0][j] = j;
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 2);
                }
            }
        }
        return dp[word1.length()][word2.length()];

    }

}
