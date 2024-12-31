package cn.dmego.leetcode.solution.lc1_lc50;

/**
 * @author dmego
 * @date 2022/01/11 09:28
 */
public class Solution_12 {

    /**
     * I  1
     * II 2
     * III 3
     * IV 4
     * V  5
     * IX 9
     * X  10
     * XL 40
     * L  50
     * XC 90
     * C  100
     * CD 400
     * D  500
     * CM 900
     * M  1000
     */
    public String intToRoman(int num) {
        StringBuilder buffer = new StringBuilder();
        int m = num / 1000;
        num = num - (1000 * m);
        if (m > 0) {
            for (int i = 0; i < m; i++) {
                buffer.append("M");
            }
        }
        int cm = 0;
        if (num - 900 >= 0) {
            cm = 1;
            num = num - 900;
        }
        if (cm == 1) {
            buffer.append("CM");
        }
        int d = num / 500;
        num = num - (500 * d);
        if (d > 0) {
            for (int i = 0; i < d; i++) {
                buffer.append("D");
            }
        }
        int cd = 0;
        if (num - 400 >= 0) {
            cd = 1;
            num = num - 400;
        }
        if (cd == 1) {
            buffer.append("CD");
        }
        int c = num / 100;
        num = num - (100 * c);
        if (c > 0) {
            for (int i = 0; i < c; i++) {
                buffer.append("C");
            }
        }
        int xc = 0;
        if (num - 90 >= 0) {
            xc = 1;
            num = num - 90;
        }
        if (xc == 1) {
            buffer.append("XC");
        }
        int l = num / 50;
        num = num - (50 * l);
        if (l > 0) {
            for (int i = 0; i < l; i++) {
                buffer.append("L");
            }
        }
        int xl = 0;
        if (num - 40 >= 0) {
            xl = 1;
            num = num - 40;
        }
        if (xl == 1) {
            buffer.append("XL");
        }
        int x = num / 10;
        num = num - (x * 10);
        if (x > 0) {
            for (int i = 0; i < x; i++) {
                buffer.append("X");
            }
        }
        int ix = 0;
        if (num - 9 >= 0) {
            ix = 1;
            num = num - 9;
        }
        if (ix == 1) {
            buffer.append("IX");
        }
        int v = num / 5;
        num = num - (v * 5);
        if (v > 0) {
            for (int i = 0; i < v; i++) {
                buffer.append("V");
            }
        }
        int iv = 0;
        if (num - iv >= 0) {
            iv = 1;
            num = num - 4;
        }
        if (iv == 1) {
            buffer.append("IV");
        }
        int iii = num / 3;
        num = num - (iii * 3);
        if (iii == 1) {
            buffer.append("III");
        }
        int ii = num / 2;
        if (ii == 1) {
            buffer.append("II");
        }
        num = num - (ii * 2);
        int i = num;
        if (i == 1) {
            buffer.append("I");
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        int num = 3;
        Solution_12 s = new Solution_12();
        String s1 = s.intToRoman(num);
        System.out.println(s1);
        System.out.println("MCMXCIV");
    }

}
