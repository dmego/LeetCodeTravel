package cn.dmego.leetcode.solution;

/**
 * 一和零
 * @author dmego
 * @date 2022/03/29 9:30 AM
 */
public class Solution_474 {

    /**
     化简为 0-1 背包问题：
     m 和 n 是背包，有两个维度，物品就是 strs 里面字符串里的 0 和 1

     dp 数组的定义：dp[i][j] 表示最多有 i 个 0 和 j 个 1 的最大子集的大小
     递推公式：dp[i][j] 可由上一个字符串推出，假设上一个字符串有 zero 个 0， one 个 1
     则：dp[i][j] = dp[i - zero][j - one] + 1;
     初始化：dp[0][0] = 0, 所有的都先初始化为 0
     遍历：字符串是物品 从前往后遍历，m 和 n 是背包，从后往前遍历

     */
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            // 统计 0 和 1 的数量
            int zero = 0, one = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0') zero++;
                else one++;
            }

            for (int i = m; i >= zero; i--) {
                for (int j = n; j >= one; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zero][j - one] + 1);
                }
            }
        }

        return dp[m][n];
    }

}
