package cn.dmego.leetcode.solution.lc1_lc50;

/**
 字符串转换整数 (atoi)
 */
public class Solution_8 {

    /*
    ' ' 32 '+' 43 '-' 45 0~9 48~57
     */
    public static int myAtoi(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] chars = s.toCharArray();
        if (chars[0] != 32 && chars[0] != 43 && chars[0] != 45 && (chars[0] < 48 || chars[0] > 57)) return 0;
        int index = 0;
        // 去除前导空格
        if (chars[index] == 32) {
            for (index = 1; index < chars.length; index++) {
                if (chars[index] != 32) {
                    break;
                }
            }
        }
        if (index == chars.length) return 0;
        boolean positive;
        //判断正负号
        if (chars[index] == 45) positive = false;
        else if (chars[index] == 43 || chars[index] >=48 && chars[index] <= 57) positive = true;
        else return 0;

        // 循环判断每个字符是不是都是数字
        if (chars[index] == 45 || chars[index] == 43) index++;
        // 去除前面的0
        while (index < chars.length && chars[index] == 48) index++;
        int startIndex = index;
        int endIndex = chars.length;
        for (;index < chars.length; index++) {
            // 中途出现非数字，记录endIndex break
            if (chars[index] < 48 || chars[index] > 57){
                endIndex = index;
                break;
            }
        }
        if (startIndex == endIndex) return 0;
        // 构建整数
        String sub = s.substring(startIndex, endIndex);

        int result = 0;
        int limit = Integer.MAX_VALUE;
        int digit;
        int radix = 10;
        int multmin = limit / radix;
        int i = 0;
        while (i < sub.length()) {
            digit = Character.digit(sub.charAt(i++), radix);
            if (result > multmin) {
                return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * radix;
            if (positive && result >= Integer.MAX_VALUE - digit) {
                return Integer.MAX_VALUE;
            }
            if (!positive && -result <= digit + Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            result = result + digit;
        }
        return positive ? result : -result;
    }

    /**
         ""
         " "
         "-42"
         "--42"
         "w42"
         "+42"
         "-4-2"
         " + 413"
         "+-12"
         "+"
         "-"
         "18446744073709551617"
         "-91283472332"
         "-2147483647"
     */
    public static void main(String[] args) {
        String s = "2147483649";
        int res = myAtoi(s);
        System.out.println(res);
    }
}
