package cn.dmego.jzoffer;

/**
 * 剑指 Offer 17. 打印从1到最大的n位数
 */
public class Offer_17 {

    /**
      解法 1：
      当 n = 1 时，打印最大值是 9
      当 n = 2 时，打印最大值是 99
      当 n = 3 时，打印最大值是 999
      能够推出，打印最大值是 10^n - 1
      从 1 循环到 10^n - 1，就是结果
     */
    public int[] printNumbers(int n) {
        int size = (int)(Math.pow(10, n) - 1);
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = i + 1;
        }
        return res;
    }

    /**

     */
    public String printNumbersBig(int n) {

        return null;
    }

    public int[] printNumbers2(int n) {
        return null;
    }



}
