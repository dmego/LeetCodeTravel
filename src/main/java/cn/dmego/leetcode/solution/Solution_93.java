package cn.dmego.leetcode.solution;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 复原 IP 地址
 * @author dmego
 * @date 2022/03/01 15:28
 */
public class Solution_93 {

    ArrayList<String> result = new ArrayList<>();
    LinkedList<String> list = new LinkedList<>();
    public ArrayList<String> restoreIpAddresses (String s) {
        int len = s.length();
        if (len < 4 || len > 12) return result;
        backtracking(s, 0, len);
        return result;
    }

    public void backtracking(String s, int start, int len) {
        if (list.size() == 4) {
            if (start == len) {
                String ip = list.get(0) + "." +
                        list.get(1) + "." +
                        list.get(2) + "." +
                        list.get(3);
                result.add(ip);
            }
            return;
        }

        for (int i = start; i < len; i++) {
            String val = s.substring(start, i + 1);
            if (isValid(val)) {
                list.addLast(val);
                backtracking(s, i + 1, len);
                list.removeLast();
            }
        }
    }

    public boolean isValid(String val) {
        if (val.length() > 3) return false;
        if (val.length() > 1 && val.charAt(0) == '0') return false;
        int num = Integer.parseInt(val);
        return 0 <= num && num <= 255;
    }

}
