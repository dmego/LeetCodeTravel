package cn.dmego.leetcode.codetop.easy;

/**
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 *
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * @author dmego
 * @date 2022/01/14 09:32
 */
public class LC_69 {
    /**
     如何理解：n^2 = x, 求 n，并且只保留整数部分
     二分查找：1^2 < x(n^2) < n^2

     */
    public int mySqrt(int x) {
        if (x == 0 || x == 1) return x;
        int left = 1, right = x / 2;
        // 记录结果
        int res = 0;
        while (left <= right) {
            int mid = (right + left) / 2;
            // 原来逻辑是 mid * mid <= x 但是有可能会溢出，改用除法
            if (mid <= x / mid) {
                // 如果 mid * mid <= x，那么 mid 有可能就是结果
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LC_69 lc = new LC_69();
        System.out.println(lc.mySqrt(4));
        System.out.println(lc.mySqrt(8));
        System.out.println(lc.mySqrt(808909662));

    }
}
