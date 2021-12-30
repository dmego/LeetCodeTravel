package cn.dmego.leetcode.solution;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 题目：
     请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。
     如果气温在这之后都不会升高，请在该位置用 0 来代替。

     例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
     你的输出应该是[1, 1, 4, 2, 1, 1, 0, 0]。

 分析：
    想要观测的最高气温 ----> 比当前气温要高
    至少需要等待的天数 -----> 相对于当前的天

 */
public class Solution_739 {

    /**
     * 思路一： 暴力解法
     * 两层 for 循环
     * 第一层循环遍历每一个温度作为当前温度
     * 第二层循环从当前温度的后一个开始遍历，判断是否大于当前温度，如果是则计算与当前温度所在索引的差
     * 差就是至少需要等待的天数
     *
     */
    public static int[] dailyTemperatures(int[] T) {
        int len = T.length;
        if (len == 0) {
            return new int[]{0};
        }
        int[] res = new int[len];
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (T[j] > T[i]) {
                    res[i] = j - i;
                    break;
                } else if (j == len - 1) {
                    // 如果当前温度 T[i] 之后的所有温度都是小的(温度不会升高)，则 res[i] = 0
                    res[i] = 0;
                }
            }
        }
        return res;
    }

    /**
     * 思路二：单调栈
     *  什么是单调栈？满足单调性的栈结构。
     *  单调栈在使用的过程中最重要的就是要保持栈结构中元素的单调性，并且栈顶元素往往与我们想要的结果最相关
     *
     *  定义一个单调栈，里面保持 气温 的单调性，栈顶元素是我们想要的气温
     *  从气温列表的尾部开始，循环处理每个气温
     *
     *  我们定义的单调栈是单调递增还是递减呢，这里可以从题目角度出发来思考
     *
     *  题目让我们求的是 至少需要等待的天数，也就是求 距离当前日期最近 且气温比当前高的 日期
     *  假设我们保证栈单调递增，栈顶元素的永远是最高气温，那么栈顶元素显然不满足我们的需求
     *  因为栈顶气温虽然比单前气温高，但是无法保证栈顶就是距离当前日期最近的
     *  如果我们保证栈单调递减，那么我们就可以用当前气温 与 栈顶气温 做比较
     *  来找出符合需求的日期了。
     *      当栈顶气温比当前气温大时，由于我们是从尾部循环遍历的，栈顶气温日期一定是离当前气温最近的日期
     *      当栈顶气温比当前气温小时，此时我们将栈顶出栈，新栈顶（因为栈是单调递减的，新栈顶一顶比刚出栈的栈顶气温高）继续与单前气温作比较，循环此操作
     *      直到新栈顶比当前气温大，也就是找到了我们想要的结果，
     *      或者当栈出栈至空后，表示当前气温之后没有更高的气温了，则结果就是 0
     *      当栈为空，或者当前气温比栈顶气温小时，当前气温入栈，保持栈的单调性
     */
    public static int[] dailyTemperatures2(int[] T) {
        int len = T.length;
        if (len == 0) {
            return new int[]{0};
        }
        int[] res = new int[len];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = len - 1; i >= 0; i--) {
            // 循环 保持栈的单调性
            // 注意：当前气温与栈顶气温相等时，是不满足条件的
            while (!deque.isEmpty() && T[deque.peek()] <= T[i]) {
                deque.pop();
            }
            res[i] = deque.isEmpty() ? 0 : deque.peek() - i;
            deque.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] T = {73,74,75,71,69,72,76,73};
        int[] res = dailyTemperatures2(T);
        System.out.println(Arrays.toString(res));
    }
}
