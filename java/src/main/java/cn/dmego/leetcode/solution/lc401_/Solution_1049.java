package cn.dmego.leetcode.solution.lc401_;

/**
 * @author dmego
 * @date 2022/03/25 18:23
 */
public class Solution_1049 {

    /**
     理解题意：如何让两两相碰撞后，最后剩下的石头重量最小，转换为就是将石头分为两个组，让两个组重量接近，
     这样这两个组碰撞后，剩下的就是最小的容量

     转换为0-1背包问题：和 416 问题几乎一样，只是最后求解时。

     dp[j] 表示容量为 j 的背包最大能装的石头重量
     最后，dp[sum/2] 表示分为两个重量相近的子集后，每个子集的重量和。
     所有题目的结果就是 sum - dp[sum/2] - dp[sum/2]

     */
    public int lastStoneWeightII(int[] stones) {
        if (stones.length == 1) return stones[0];
        int sum = 0;
        for (int i = 0; i < stones.length; i++) {
            sum += stones[i];
        }
        int m = sum / 2;
        int[] dp = new int[m + 1];
        for (int i = 0; i < stones.length; i++) {
            for (int j = m; j >= 0; j--) {
                if (j - stones[i] >= 0) {
                    dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
                }
            }
        }
        return (sum - dp[m]) - dp[m];
    }

}
