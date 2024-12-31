package cn.dmego.leetcode.solution.lc401_;

/**
 * 509 斐波那契数列
 */
public class Solution_509 {

    /*
     F(0) = 0，F(1) = 1
     F(n) = F(n - 1) + F(n - 2)，其中 n > 1

     定义dp数组：dp[i] 表示 F(i) 的值
     递推公式：dp[i] = dp[i - 1] + dp[i - 2] i >= 2
     初始化值：dp[0] = 0, dp[1] = 1
     */
    public int fib(int n) {
        if (n == 0) return 0;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // 优化空间，将二维数组使用两个int代替
    public int fib2(int n) {
        if (n == 0) return 0;
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                a = a + b;
            } else {
                b = a + b;
            }
        }
        return n % 2 == 0 ? a : b;
    }


}
