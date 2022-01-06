package cn.dmego.leetcode.codetop.easy;

/**
 * 字符串相加
 * @author dmego
 * @date 2022/01/04 22:40
 */
public class LC_415 {

    public String addStrings(String num1, String num2) {
        StringBuilder builder = new StringBuilder();
        int mark = 0; // 进位
        int index1 = num1.length() - 1, index2 = num2.length() - 1;
        while (index1 >= 0 || index2 >= 0) {
            int x = index1 >= 0 ? num1.charAt(index1--) - '0' : 0;
            int y = index2 >= 0 ? num2.charAt(index2--) - '0' : 0;
            int num = (x + y + mark) % 10;
            mark = (x + y + mark) / 10;
            builder.append(num);
        }
        if (mark != 0) {
            builder.append(mark);
        }
        builder.reverse();
        return builder.toString();
    }

}
