package cn.dmego.leetcode.solution.lc1_lc50;

/**
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 *
 * 如果反转后整数超过 32 位的有符号整数的范围 [−2^31, 2^31 − 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 */
public class Solution_7 {

    public static int reverse(int x) {
        if (x == 0) return 0;
        boolean negative = x < 0;
        String str = String.valueOf(x);
        int start = negative ? 1: 0;
        int multmin = Integer.MIN_VALUE / 10;
        int i = str.length() - 1;
        int result = 0;
        while (i >= start) {
            int digit = Character.digit(str.charAt(i--), 10);
            if (result > -multmin) {
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            result = result * 10;
            if (negative &&  Integer.MIN_VALUE + result + digit > 0) {
                return Integer.MIN_VALUE;
            }
            if (!negative && Integer.MAX_VALUE - result - digit < 0) {
                return Integer.MAX_VALUE;
            }
            result = result + digit;
        }
        return negative ? -result : result;
    }

    public static void main(String[] args) {

        int i = 1534236469;
        int reverse = reverse(i);
        System.out.println(reverse);
    }

}
