package cn.dmego.leetcode.solution.lc251_lc300;

/**
 * [258] 各位相加
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
 */
public class Solution_258 {

    public int addDigits(int num) {
        if (0 <= num && num <= 9)
            return num;
        int n1 = num / 10;
        int n2 = num % 10;
        return addDigits(n1 + n2);
    }
}
