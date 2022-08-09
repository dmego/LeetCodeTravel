package cn.dmego.leetcode.solution.lc151_lc200;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * 翻转字符串里的单词
 * @author dmego
 * @date 2022/01/26 09:30
 */
public class Solution_151 {

    /**
        使用 List 保存
     */
    public String reverseWords(String s) {
        List<String> list = new ArrayList<>();
        int k = 0;
        boolean flag = false;
        for (int j = 0; j < s.length(); j++) {
            if (s.charAt(j) == ' ') {
                if (!flag) {
                    list.add(s.substring(k, j));
                }
                flag = true;
            } else {
                if (flag) {
                    k = j;
                }
                flag = false;
            }
            // 最后一个单词需要保存
            if (j == s.length() -1 && !flag) {
                list.add(s.substring(k, j + 1));
            }
        }
        StringBuilder buffer = new StringBuilder();
        for (int i = list.size() - 1; i >= 0; i--) {
            buffer.append(list.get(i)).append(" ");
        }
        return buffer.toString().trim();
    }

    /**
        使用 API
     */
    public String reverseWords2(String s) {
        // 去除首位空格
        s = s.trim();
        // s.split("\\s") 使用正则匹配连续空格，作为分隔符
        List<String> list = Arrays.asList(s.split("\\s+"));
        // 翻转列表 list
        Collections.reverse(list);
        // 使用 join 将字符串数组拼接成字符串，使用 " " 连接
        return String.join(" ", list);
    }

    /**
     使用双端队列
     */
    public String reverseWords3(String s) {
        // 去除首位空格
        s = s.trim();
        Deque<String> deque = new ArrayDeque<>();
        int i = 0;
        boolean flag = false;
        for (int j = 0; j < s.length(); j++) {
            if (s.charAt(j) == ' ') {
                if (!flag) {
                    deque.addFirst(s.substring(i, j));
                    flag = true;
                }
            } else {
                if (flag) {
                    i = j;
                    flag = false;
                }
            }
            if (j == s.length() -1 && !flag) {
                deque.addFirst(s.substring(i, j + 1));
            }
        }
        return String.join(" ", deque);
    }

    /**
     快慢指针：从后往前遍历
     */
    public String reverseWords4(String s) {
        // 先去除首尾空格
        s = s.trim();
        int i = s.length() - 1;
        boolean flag = false;
        StringBuilder buffer = new StringBuilder();
        for (int j = s.length() - 1; j >=0; j--) {
            if (s.charAt(j) == ' ') {
                if (flag) {
                    buffer.append(s, j + 1, i + 1).append(" ");
                    flag = false;
                }
            } else {
                if (!flag) {
                    i = j;
                    flag = true;
                }
            }
        }
        if (flag) {
            buffer.append(s, 0, i + 1);
        }
        return buffer.toString();
    }


    public static void main(String[] args) {
        Solution_151 so = new Solution_151();
        String s = "  this is   the blue  ";
        System.out.println(so.reverseWords4(s));
    }


}
