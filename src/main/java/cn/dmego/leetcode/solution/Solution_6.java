package cn.dmego.leetcode.solution;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dmego
 * @date 2021/11/10 10:53
 */
public class Solution_6 {

    public static String convert(String s, int numRows) {
        if (numRows < 2) return s;
        StringBuffer[] strArr = new StringBuffer[numRows];
        int i = 0, j = 0;
        boolean flag = true;// true 从 1~num, false从num~1
        while (i < s.length()) {
            if (j == 0 && !flag) {
                flag = true;
            } else if (j < numRows) {
                if (strArr[j] == null) strArr[j] = new StringBuffer();
                strArr[j].append(s.charAt(i));
                if (flag) j++;
                else j--;
                i++;
            } else if (j == numRows) {
                flag = false;
                j = numRows - 2;
            }
        }
        StringBuffer buff = strArr[0];
        for (int k = 1; k < numRows; k++) {
            if (strArr[k] != null) {
                buff.append(strArr[k]);
            }
        }
        return buff.toString();
    }


    public static String convert2(String s, int numRows) {
        // 只有一行，本身就是满足条件的结果
        if (numRows == 1) return s;
        List<StringBuffer> list = new ArrayList<>();
        int len = Math.min(numRows, s.length());
        // 给 list 初始化
        for (int i = 0; i < len; i++) list.add(new StringBuffer());
        boolean goDown = false; // 是否是自底往上遍历
        int currRow = 0; // 当前行
        for (char c : s.toCharArray()) {
            // 当前行结尾添加元素
            list.get(currRow).append(c);
            // 如果到达Z字的转折点，第一行和最后一行）则需要改变遍历的方向
            if (currRow == 0 || currRow == numRows - 1) goDown = !goDown;
            // 如果是往下遍历：goDown = true; 下一行就是 currRow + 1;
            // 如果往上遍历 goDown = false; 下一行就是 currRow - 1
            currRow += goDown ? 1 : -1;
        }
        StringBuilder result = new StringBuilder();
        for(StringBuffer buff : list) result.append(buff);
        return result.toString();
    }

    public static void main(String[] args) {
        String s = "PAYPALISHIRING"; // ""
        int numRows = 4;
        String convert = convert2(s, numRows);
        System.out.println(convert);
        System.out.println("PINALSIGYAHRPI");


    }
}
