package cn.dmego.newcode.top101.dp;

/**
 * 最长公共子串
 * 给定两个字符串str1和str2,输出两个字符串的最长公共子串
 * 题目保证str1和str2的最长公共子串存在且唯一。
 * @author dmego
 * @date 2022/04/20 15:38
 */
public class BM66 {

    /*
    注意：题目要求的是最长公共子串是什么，而不是长度

     */
    public String LCS (String str1, String str2) {
        // dp[i][j] 表示 str1(0~i-1) str2(0~j-1) 之间的最长公共子串长度
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        // 最长公共子串的长度
        int maxLen = 0;
        // 最长公共子串的结尾下标
        int lastIndex = 0;
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLen) {
                        maxLen = dp[i][j];
                        lastIndex = i;
                    }
                }
            }
        }
        return str1.substring(lastIndex - maxLen, lastIndex);
    }

    public static void main(String[] args) {
        String str1 = "1AB2345CD";
        String str2 = "12345EF";
        BM66 bm = new BM66();
        String res = bm.LCS(str1, str2);
        System.out.println(res);
    }
}
