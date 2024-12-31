package cn.dmego.algorithm.dp.backpack;

/**
 * 完全背包
 * @author dmego
 * @date 2022/04/03 11:25 AM
 */
public class CompleteBackpack {



    /**
     有 n 种物品，每种物品的个数有无数个，每种物品的价值是 values[i], 每种物品的重量是 weight[i]， 现有一个容量为 m 的背包
     求这个背包能装下的最大物品价值(每种物品能装多个)

     和 0 - 1 背包基本类似，但是遍历的顺序不一样？

     dp数组定义：dp[j] 表示容量为 j 的背包能装下的最大物品价值 dp[j]
     递推公式：dp[j] = Math.max(dp[j], dp[j - weight[i]] + values[i])
     遍历顺序，每种物品可以放多个，遍历物品时，从前往后，可以使用本层遍历的值

     */
    public static void completeBackpack(int n, int m, int[] weight, int[] values) {
        int[] dp = new int[m + 1];
        for (int i = 0; i < n; i++) {
            for (int j =  weight[i]; j <= m; j++) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + values[i]);
            }
        }
        for (int j = 0; j <= m; j++) {
            System.out.print(dp[j] + " ");
        }
    }




    public static void main(String[] args) {
        int n = 3;
        int m = 4;
        int[] weight = {1, 3, 4};
        int[] values = {15, 20, 30};
        completeBackpack(n, m, weight, values);
    }
}
