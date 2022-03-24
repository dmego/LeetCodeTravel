package cn.dmego.leetcode.solution;

/**
 * 不同的二叉搜索树
 * @author dmego
 * @date 2022/03/24 13:47
 */
public class Solution_96 {

    /**
     题目求以 1 ~ n 为节点值组成二叉搜索树有多少种?
     我们可以先举几个例子：
     当 n = 0 时，无法构成，result = 0;
     当 n = 1 时，能构成一棵以 1 为根的，result = 1;
     当 n = 2 时，根节点有两种选择，1 和 2，result = 2;
     当 n = 3 时，根节点有 1，2，3 三种选择。当我们以 1 为根节点时，左右子树只有两种选择 1 和 2
     这相当于 n = 2时的结果。

     所以，根据上面的举例分析，我们可以设 G(n) 表示以 1~n为节点值组成的二叉树搜索树的种树， f(i) 表示以 i 为根节点的二叉搜索树种数
     G(n) = f(1) + f(2) + ... f(n)
     而当以 i 为根节点时，其左子树节点个数为 i - 1 (1 ~ i - 1), 右子树个数为 n - i (i + 1 ~ n)
     f(i) = G(i - 1) * G(n - i)
     所以 G(n) = G(0) * G(n - 1) + G(1) * (n - 2) + ... + G(n - 1) * G(0) 这是一个卡通兰数公式
     根据这个公式，我可以使用动态规划的解法来解决该问题
     dp数组：设 dp[i] 表示 以 1 ~ i 为节点可以组成的二叉树种数
     递推公式：dp[i] = dp[i] + dp[j - 1] * dp[i - j]
     初始化值：dp[0] = 0, dp[1] = 1
     遍历顺序：两层循环，第一层从 2 ~ n; 第二层从 1 ~ i

     */
    public int numTrees(int n) {
        if (n == 0 || n == 1)
            return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

}
