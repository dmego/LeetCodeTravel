package cn.dmego.leetcode.solution;

/**
 * 整数拆分
 * @author dmego
 * @date 2022/03/23 10:17
 */
public class Solution_343 {

    /**
     dp 数组：dp[i] 表示 整数 i 拆分后的最大化乘积
     递推公式：
     首先看一下 dp[i] 如何进行拆分，先假设拆分为两个数 j, (i - j), 乘积就是 j * (i - j)
     如果是拆分两个数以上呢？我们可以换一个角度：先拆分一个数 j, 剩下的 (i - j) 再拆分，这样的话
     dp[i] = j * dp[i - j]
     所有 dp[i] = Math.max(dp[i], j * (i - j), j * dp[i - j]), 注意也要和 dp[i] 比较，因为拆分是从
     1 ~ i 遍历的，要找到最大的 dp[i]
     初始化：
     n >= 2; 因为 0 和 1 是无法拆分的，dp[2] = 1 * 1 = 1; 求 dp[3] 时也只需和 dp[2] 有关
     遍历方向：
     双层 for 循环，第一层 3 ~ n, 第二层 1 ~ i

     */
    public int integerBreak(int n) {
        if (n == 2) return 1;
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(Math.max(dp[i], j * (i - j)), j * dp[i - j]);
            }
        }
        return dp[n];
    }


}
