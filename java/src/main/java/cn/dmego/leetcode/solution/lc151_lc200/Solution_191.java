package cn.dmego.leetcode.solution.lc151_lc200;

/**
 * [191] 位1的个数
 */
public class Solution_191 {

    /**
      利用位运算：n & (n - 1) 能够消除 n 的二进制中最后一位 1
      因为 n - 1 能够是将最后一位 1 置 0，后面的 所有 0 置 1
      又有 1 & 0 = 0 所以  n & (n - 1) 能将最后一位 1 变成 0
     */
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Solution_191 sol = new Solution_191();
        int n = -3;
        int res = sol.hammingWeight(n);
        System.out.println(res);
    }

}
