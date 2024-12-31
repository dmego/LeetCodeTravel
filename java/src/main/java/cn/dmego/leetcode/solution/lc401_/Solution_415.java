package cn.dmego.leetcode.solution.lc401_;

/**
 * [415] 字符串相加
 * 输入：num1 = "456", num2 = "77"
 * 输出："533"
 */
public class Solution_415 {

    /**
     - 遍历两个字符串，从后往前遍历。（高位在前，低位在后）
     - 定义一个进位变量 carry，和一个 StringBuffer 的结果变量 res
     - 每次遍历时，更新 carry 值和 res 值
     - 遍历结果后，要判断 carry 的值，最后一个进位
     - 因为最后结果是 高位在前，低位在后，所以需要将 res 进行反转
     */
    public String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        // 进位
        int carry = 0;
        int index1 = num1.length() - 1, index2 = num2.length() - 1;
        while (index1 >= 0 || index2 >= 0) {
            int i1 = 0, i2 = 0;
            // 字符串 - '0' 得到 字符串转 int 类型的值
            if (index1 >= 0) {
                i1 = num1.charAt(index1) - '0';
            }
            if (index2 >= 0) {
                i2 = num2.charAt(index2) - '0';
            }
            int num = (i1 + i2 + carry) % 10;
            res.append(num);
            carry = (i1 + i2 + carry) / 10;
            index1--;
            index2--;
        }
        // 遍历结束要考虑 进位的值
        if (carry == 1) {
            res.append(carry);
        }
        // 结果要反转一下，最高位在左边，低位在右边
        res.reverse();
        return res.toString();
    }
}
