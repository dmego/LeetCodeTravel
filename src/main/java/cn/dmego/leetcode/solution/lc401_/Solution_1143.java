package cn.dmego.leetcode.solution.lc401_;

/**
 * 最长公共子序列
 * @author dmego
 * @date 2022/02/14 10:38
 */
public class Solution_1143 {

    /**
     1.dp数组定义: dp[i][j] 表示 text1(0~i-1) text2(0~j-1) 长度内的最长公共子序列长度
     2.递推公式:
        当 nums[i - 1] == nums[j - 1] 时，dp[i][j] = dp[i - 1][j - 1] + 1;
        当 nums[i - 1] != nums[j - 1] 时，因为是子序列，不需要挨着，dp[i][j] 的值取决于前一个和上一个
        前一个是：dp[i - 1][j] 表示 text1(0~i-2) text2(o~i-1) 长度内的最长公共子序列
        后一个是：dp[i][j - 1] 表示 text1(0~i-1) text2(o~i-2) 长度内的最长公共子序列
        也就是dp[i - 1][j] 和 dp[i][j - 1] 取其中的最大值即可
     3.初始化: 都初始化为 0
     */
    public int longestCommonSubsequence(String text1, String text2) {
        char[] char1 = text1.toCharArray();
        char[] char2 = text2.toCharArray();
        int[][] dp = new int[char1.length + 1][char2.length + 1];
        for (int i = 1; i <= char1.length; i++) {
            for (int j = 1; j <= char2.length; j++) {
                if (char1[i - 1] == char2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[char1.length][char2.length];
    }
}
