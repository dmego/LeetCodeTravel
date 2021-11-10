package cn.dmego.leetcode;

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
        if (numRows == 1) return s;
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());
        int curRow = 0; // 当前行
        boolean toUp = false; // 是否开始往上遍历（Z字从下往上折返）
        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            // 到达第 1 行 需要往下折返；到达最后一行，需要往上折返
            if (curRow == 0 || curRow == numRows - 1) toUp = !toUp;
            // 根据遍历的方向修改下一行的值，往下折返 curRow++; 往上折返 curRow--;
            curRow += toUp ? 1 : -1;
        }
        StringBuilder buff = new StringBuilder();
        for (StringBuilder row : rows) buff.append(row);
        return buff.toString();
    }

    public static void main(String[] args) {
        String s = "PAYPALISHIRING"; // ""
        int numRows = 4;
        String convert = convert2(s, numRows);
        System.out.println(convert);
        System.out.println("PINALSIGYAHRPI");


    }
}
