package cn.dmego.leetcode.solution.lc350_lc400;

/**
 * @author dmego
 * @date 2021/11/04 10:44
 */
public class Solution_367 {

    public static boolean isPerfectSquare(int num) {
        // 什么是完全平方数 x * x = num x 就是完全平方数
        int x = (int) Math.sqrt(num);
        return x * x == num;
    }

    public static boolean isPerfectSquare2(int num) {
        int left = 1, right = num;
        while (left <= right) {
            int mid = (right - left + 1) / 2 + left;
            long square = (long) mid * mid;
            if (square < num) left = mid + 1;
            else if (square > num) right = mid - 1;
            else return true;
        }
        return false;
    }




    public static void main(String[] args) {

        System.out.println(isPerfectSquare2(14));
        System.out.println(isPerfectSquare2(16));
        System.out.println(isPerfectSquare2(64));


    }

}
