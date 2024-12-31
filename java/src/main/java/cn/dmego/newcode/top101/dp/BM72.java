package cn.dmego.newcode.top101.dp;

/**
 连续子数组的最大和
 输入一个长度为n的整型数组array，数组中的一个或连续多个整数组成一个子数组，子数组最小长度为1。求所有子数组的和的最大值。
 数据范围:
 1 <= n <= 2 * 10^5
 -100 <= a[i] <= 100

 要求:时间复杂度为 O(n)O(n)，空间复杂度为 O(n)O(n)
 进阶:时间复杂度为 O(n)O(n)，空间复杂度为 O(1)O(1)

 ex: [1,-2,3,10,-4,7,2,-5]

 * @author dmego
 * @date 2022/04/20 15:15
 */
public class BM72 {

    /*
    1. dp 数组定义：dp[i] 表示以 i 下标结尾数组的子数组的最大和
    2. 初始化： dp[0] = array[0]
    3. 递推公式： dp[i] = Math.max(dp[i - 1] + arrays[i], arrays[i]);
    最后返回 dp[i] 的最大值
    */
    public int FindGreatestSumOfSubArray(int[] array) {
        int[] dp = new int[array.length];
        dp[0] = array[0];
        int res = dp[0];
        for (int i = 1; i < array.length; i++) {
            dp[i] = Math.max(dp[i - 1] + array[i], array[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

}
