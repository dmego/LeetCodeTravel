package cn.dmego.leetcode.solution;

/**
 * @author dmego
 * @date 2021/11/04 11:26
 */
public class Solution_69 {
    public static int mySqrt(int x) {
        if (x == 0 || x == 1) return x;
        int left = 1, right = x / 2;
        int res = 0;
        while (left <= right) {
            int mid = (right - left + 1) /2 + left;
            long square = (long) mid * mid;
            // 结果是整数，所以最后一个 mid * mid <= x 的 mid 就是答案
            if (square <= x) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        //System.out.println(mySqrt(8));
        //System.out.println(mySqrt(15));
        System.out.println(mySqrt(808909662));
    }
}
