package cn.dmego.algorithm.dp.backpack;

/**
 * 0-1 背包
 * 将哪些物品装进背包让价值最大
 * @author dmego
 * @date 2022/03/25 11:05
 */
public class ZeroOneBackpack {

    /**
     * 有 n 件物品，每件物品的重量是 weight[i], 每件物品的价值是 values[i], 问一个容量为 m 的背包将哪些物品装进可让价值最大
     *
     * 使用动态规划五步骤解题：
     *
     * 1. dp 数组的定义：设 dp[i][j] 表示将下标从 0 到 i 的物品放进容量为 j 的背包能有的最大价值。
     *
     * 2. 递推公式：
     *  先明确一下 dp 数组的定义，将下标从 0 到 i 的物品放进容量为 j 的背包
     *    - 首先来说，物品不是一个一个单独放的，也不是乱序放的，我们是先
     *      放下标为 0 的物品，然后放下标为 1 的物品，这其中还有涉及到能不能放下，也有可能背包剩余容量比 weight[i] 小，还放不下。
     *    - 对于背包容量，是每次循环不断增大的，从 0 一步步扩容到 m
     *  可以看出：dp[i][j] 的结果与 dp[i - 1][j] 有很大的关系，dp[i - 1][j] 表示的是将 0~i-1下标的物品放进 j 容量背包的最大价值。
     *  对于下标为 i 的物品，我们有两种选择：
     *     - 如果我们不放下标为 i 的物品：dp[i][j] = dp[i - 1][j]
     *     - 如果我们放下标为 i 的物品, 为了保证 j 容量的背包能装下 i, dp[i][j] = dp[i][j - weight[i]] + values[i], 其中 j - weight[i]
     *       表示为放下 下标为 i 的物品的背包最大可使用容量， 将前 0~i-1 物品放进 j - weight[i] 这个最大可使用容量的背包的最大价值就是
     *       dp[i - 1][j - weight[i]]; dp[i][j] 就是再它的基础在，再加上 i 自己的价值：dp[i - 1][j - weight[i]] + values[i]
     *  经过上面发分析，可以总结出：dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight[i]] + values[i])
     *
     * 3. dp 数组的初始化：
     *  因为是二维数组，初始化由两个方向:
     *     dp[i][0]: 当背包容量为 0 时，无法将任何物品放进去，所有最大价值都是 0
     *     dp[0][j]: 当只有一个下标为 0 的物品时
     *      如果背包容量 j < weight[0], 无法将下标为 0 物品放进去，dp[0][j] = 0;
     *      如果背包容量 j >= weight[0], 则可以装下 0 物品，dp[0][j] = values[0];
     *
     * 4. 遍历的顺序，有两种，先遍历物品，还是先遍历背包容量，
     *    我们将二维数组使用表格展现，可以发现第一个能求的都是 dp[1][1]
     *      对于先遍历物品：下一个要求的是：dp[2][1], 可以求出
     *      对于先遍历容量：下一个要求的是：dp[1][2], 同样也可以求出
     *    所以两个方向都可以
     *
     *
     *
     * @param n 物品件数
     * @param m 背包的容量
     * @param weight 每件物品的重量
     * @param values 每件物品的价值
     */
    public static void zeroOneBackpack(int n, int m, int[] weight, int[] values) {
        // dp 数组定义
        int[][] dp = new int[n][m + 1];
        // 初始化
        for (int i = 0; i < n; i++) dp[i][0] = 0;
        for (int j = 0; j <= m; j++) {
            if (weight[0] > j) dp[0][j] = 0;
            else dp[0][j] = values[0];
        }

        // 遍历物品
        for (int i = 1; i < n; i++) {
            // 遍历背包容量，注意容量边界等于 m
            for (int j = 1; j <= m; j++) {
                // 如果容量能放下 下标为 i 的物品
                if (j - weight[i] >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + values[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }


    /**
     dp 数组优化，由2维数组改成1维滚动数组

     1. dp 数组的含义：
      dp[j] 表示容量为 j 的背包最大能装下的物品价值
     2. 递推公式：
      首先还是理解一下 dp[j] 的定义，以及如何使用 dp[j] 来实现滚动数组。
         - dp[j] 表示容量为 j 的背包最大能装下的物品价值，dp[j] 与它的上一个有关，上一个是 dp[j - weight[i]]，
         结合二维数组的分析，dp[j] = Math.max(dp[j], dp[j - weight[i]] + values[i])

     3.初始化：dp[0] = 0

     4. 遍历的顺序：
       我们在二维数组时，不管是遍历物品还是遍历背包容量都是从前往后的，因为每一层遍历都依赖于上一层遍历的结果, 而且结果都是独立的
       而对于一维数组，它滚动的就是每一层的结果，根据我们的递推公式 dp[j] 的求解是依赖于 dp[j - weight[i]]
       当只放下标为 0 的物品时，原则上是 dp[j] 只与它本地的容量有关，因为只有一件物品，只有放或不放两个选择。如果是正序遍历。
       根据递推公式，dp[j] 就会 + dp[j - x] 的值，而 j - x < j, 会先遍历，这样的话 dp[j] 就会多加装物品，而我们前提是每个物品只能用一次
       其实本质上还是 dp[j - weight[i]] 需要是上一层的结果，而不能是本次循环下更新的值。
       如果我们遍历背包容量时采用从后往前遍历，就能实现，遍历dp[j]时，dp[j -weight[i]] 一定还是上一层的值。
     *
     * @param n 物品数量
     * @param m 背包容量
     * @param weight 每件物品的重量
     * @param values 每件物品的价值
     */
    public static void zeroOneBackpack_scrollArray(int n, int m, int[] weight, int[] values) {
        // 定义 dp 数组
        int[] dp = new int[m + 1];
        // 初始化
        dp[0] = 0;
        // 遍历物品
        for (int i = 0; i < n; i++) {
            // 从后往前遍历背包容量
            for (int j = m; j >= 0; j--) {
                if (j - weight[i] >= 0) {
                    dp[j] = Math.max(dp[j], dp[j - weight[i]] + values[i]);
                }
            }
        }

        for (int j = 0; j <= m; j++) {
            System.out.print(dp[j] + " ");
        }
    }

    public static void main(String[] args) {
        int n = 3;
        int[] weight = {1, 3, 4};
        int[] values = {15, 20, 30};
        int m = 4;
        zeroOneBackpack(n, m, weight, values);
        System.out.println("---------------------------------");
        zeroOneBackpack_scrollArray(n, m, weight, values);
    }


}
