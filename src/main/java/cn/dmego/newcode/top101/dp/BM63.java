package cn.dmego.newcode.top101.dp;

/**
 * 跳台阶
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 * @author dmego
 * @date 2022/04/20 17:49
 */
public class BM63 {

    /**
     - dp 数组的定义： dp[i] 表示跳上 i 级台阶的跳法数
     - 初始化：dp[1] = 1; dp[2] = 2; 因为一次可以跳一阶，也可以跳两阶;
     - 递推公式：dp[i] = dp[i - 1] + [i - 2] 跳到 i 级，可以由 i - 1级跳一阶。也可以由i - 2 级跳两阶
     - 空间优化： 用两个变量实现
     */
    public int jumpFloor(int target) {
        if (target == 1 || target == 2) return target;
        int a = 1, b = 2;
        for (int i = 3; i <= target; i++) {
            if (i % 2 == 1) a = a + b;
            else b = a + b;
        }
        return target % 2 == 1 ? a : b;
    }
}
