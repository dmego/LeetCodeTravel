package cn.dmego.leetcode.solution.lc101_lc150;

import java.util.Arrays;

/**
 * [135] 分发糖果
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 * 你需要按照以下要求，给这些孩子分发糖果：
 *  每个孩子至少分配到 1 个糖果。
 *  相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 */
public class Solution_135 {
    /**
     贪心思想：
     - 最优子结构：规模较大的问题可以由规模较小的子问题组成，规模较大问题的解只由其中一个规模较小问题的解决定
     - 无后效性：后面阶段的求解不会修改前面阶段已经求出的结果
     - 贪心选择性质：从局部最优解中能得到全局最优解
     */

    /**
     贪心法：
     - 题目的要求是：每个孩子至少分配一个糖果，相邻两个孩子评分高的会获得更多糖果，评分相同没有要求。
     - 我们可以根据这个大规则分解成两个子规则：
        - 左规则：从左往右遍历每个孩子，如果 ratings[i - 1] < ratings[i] 那么 i 孩子的糖果比 i - 1 孩子多
        - 右规则：从右往左遍历每个孩子，如果 ratings[i + 1] < ratings[i] 那么 i 孩子的糖果比 i + 1 孩子多
     - 我们先求满足左规则的结果，然后在左规则基础上，求满足右规则结果。就好像我们先 "向左看齐"，然后再 "向右看齐"，
        之后每个人不管 "向左，向右" 都是满足规则的。
     - 定义一个结果数组 candy[], candy[i] 表示 i 下标孩子分到的糖果数。开始时都初始化为 1
     - 因为是求需要准备的最少糖果，所以每次遍历的时候，如果当前孩子比前一个多，直接 + 1
     - 根据左右规则，candy[i] 的计算规则如下：
        - 先左规则：if(ratings[i - 1] < ratings[i]) candy[i] = candy[i - 1] + 1
        - 再右规则：if(ratings[i + 1] < ratings[i]) candy[i] = Math.max(candy[i], candy[i + 1] + 1)
        - 因为需要在满足左规则基础上，再满足右规则，所以需要取两者最大值。
     - 最后将 candy[] 数组求和就是最终的最少结果。
     */
    public int candy(int[] ratings) {
        int[] candy = new int[ratings.length];
        // 初始化每个孩子分 1 个糖果
        Arrays.fill(candy, 1);
        int res = 0;
        // 先求满足左规则
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i - 1] < ratings[i]) {
                candy[i] = candy[i - 1] + 1;
            }
        }
        // 在左规则基础上，求满足右规则
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i + 1] < ratings[i]) {
                candy[i] = Math.max(candy[i], candy[i + 1] + 1);
            }
            res += candy[i];
        }
        // 加上最后一个小孩糖果
        res += candy[candy.length - 1];
        return res;
    }


    public static void main(String[] args) {
        int[] ratings = {1, 0, 2};
        Solution_135 solution = new Solution_135();
        int res = solution.candy(ratings);
        System.out.println(res);
    }


}
