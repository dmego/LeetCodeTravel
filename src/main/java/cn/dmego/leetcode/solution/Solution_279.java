package cn.dmego.leetcode.solution;

import java.util.Arrays;

/**
 * 完全平方数
 * @author dmego
 * @date 2022/04/07 09:56
 */
public class Solution_279 {

    /*
    物品：1(1^2) 4(2^2) 9(3^3) 16(4^4) ...
    背包：n

    dp[j] 表示凑成和整数 j 需要的完全平方数的最少数量
    dp[0] = 0;

    和 兑换零钱(一) 解题一样
    dp[j] = Math.min(dp[j], dp[j - i*i] + 1)
    */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        int max = Integer.MAX_VALUE;
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = i * i; j <= n; j++) {
                if (dp[j - i * i] != max) {
                    dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
                }
            }
        }
        return dp[n];
    }

}
