package cn.dmego.newcode.top101.dp;

import java.util.Arrays;

/**
 * 兑换零钱（一）
 * @author dmego
 * @date 2022/04/06 11:25
 */
public class BM70 {

    /**
     根据题意可以知道这是一个完全背包问题
     1. dp 数组定义：dp[j] 表示凑成 j 面值使用的最少货币张数是 dp[j]
     2. 递推公式：dp[j] 的来源只有一个 dp[j - arr[i]], 而 dp[j - arr[i]] 表示凑成 j - arr[i] 面值使用的最少货币数
        那么 dp[j] 只需要在他基础上加上一张就行了，dp[j] = dp[j - arr[i]] + 1
        因为是求使用的最小张数，所以：dp[j] = min(dp[j], dp[j - arr[i] + 1)
     3. 初始化：当我们要求最小值时，为了让初始值不影响结果，需要将所有值初始化为 MAX_VALUE, 另外对于 dp[0] 需要初始化为 0。表示凑成 0
        金额需要的最小张数为 0
     4. 遍历的顺序：遍历的顺序与标准完全背包一样，因为不是求排列组合问题，先遍历物品还是先遍历背包对结果没有影响

     */
    public int minMoney (int[] arr, int aim) {
        if (arr.length == 0) return -1;
        int[] dp = new int[aim + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        // 先遍历物品
        for (int i = 0; i < arr.length; i++) {
            // 遍历背包
            for (int j = arr[i]; j <= aim; j++) {
                // 注意：只有 dp[j - arr[i]] 有求解值时，才在它基础上 + 1
                if (dp[j - arr[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - arr[i]] + 1);
                }
            }
        }
        // 最后如果 dp[aim] = MAX, 说明没有结果，返回 -1，否则返回 dp[aim]
        return dp[aim] == Integer.MAX_VALUE ? -1 : dp[aim];
    }

}
