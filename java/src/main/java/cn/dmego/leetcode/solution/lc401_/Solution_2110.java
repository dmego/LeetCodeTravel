package cn.dmego.leetcode.solution.lc401_;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author dmego
 * @date 2021/12/24 22:21
 */
public class Solution_2110 {

    public long getDescentPeriods(int[] prices) {
        int days = prices.length;
        int i = 0, j = i;
        int old = i;
        while (i < prices.length) {
            int count = 1;
            while (j+1 < prices.length && prices[j] - prices[j+1] == 1) {
                count++;
                j++;
            }
            for (int k = i; k < j; k++) {
                days += count;
                count--;
            }
            i = j;
        }
        return days;
    }

    public static void main(String[] args) {

    }
}
