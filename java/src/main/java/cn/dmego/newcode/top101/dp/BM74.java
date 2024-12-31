package cn.dmego.newcode.top101.dp;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 数字字符串转为 IP 地址
 * @author dmego
 * @date 2022/05/15 16:06
 */
public class BM74 {

    // 结果集
    ArrayList<String> result = new ArrayList<>();
    LinkedList<String> list = new LinkedList<>();
    public ArrayList<String> restoreIpAddresses (String s) {
        int len = s.length();
        // 如果字符串长度< 4 或者 > 12 不满足条件
        if (len < 4 || len > 12) return result;
        backtracking(s, 0, len);
        return result;
    }

    // 回溯法
    public void backtracking(String s, int start, int len) {
        // 当满足条件[0, 255]的字符个数等于 4 个时，就可以构成IP地址了
        if (list.size() == 4) {
            // 如果刚好遍历到字符串 s 的结尾，说明 s 可以转为Ip
            if (start == len) {
                String ip = list.get(0) + "." +
                        list.get(1) + "." +
                        list.get(2) + "." +
                        list.get(3);
                result.add(ip);
            }
            // 返回递归结束
            return;
        }
        // 从 start 开始，横向遍历
        for (int i = start; i < len; i++) {
            String val = s.substring(start, i + 1);
            if (isValid(val)) {
                list.addLast(val);
                // 纵向递归
                backtracking(s, i + 1, len);
                list.removeLast();
            }
        }
    }

    // 判断字符 val 是否在 [0, 255] 之间
    public boolean isValid(String val) {
        // 大于 3 位，返回 false
        if (val.length() > 3) return false;
        // 大于 1 位，但是第一位是 0， 返回 false, 不能有前导 0
        if (val.length() > 1 && val.charAt(0) == '0') return false;
        // 转为 int 类型，并判断是否在 0 ~ 255 之间
        int num = Integer.parseInt(val);
        return 0 <= num && num <= 255;
    }
}
