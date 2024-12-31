package cn.dmego.leetcode.solution.lc401_;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dmego
 * @date 2021/12/23 16:32
 */
public class Solution_784 {
    List<String> result = new ArrayList<>();

    public void backtracking(String s, int index, StringBuilder builder) {
        if (s.length() == index) {
            result.add(builder.toString());
            return;
        }
        char c = s.charAt(index);
        if (48 <= c && c <= 57) {
            builder.append(c);
            backtracking(s, index + 1, builder);
            builder.deleteCharAt(builder.length() -1);
        } else {
            for (int j = 0; j < 2; j++) {
                char nc = c;
                if (65 <= c && c <= 90) {
                    if (j == 1) nc += 32;
                }
                if (97 <= c && c <= 122) {
                    if (j == 1) nc -= 32;
                }
                builder.append(nc);
                backtracking(s, index + 1, builder);
                builder.deleteCharAt(builder.length() -1);
            }
        }
    }

    public List<String> letterCasePermutation(String s) {
        backtracking(s, 0, new StringBuilder());
        return result;
    }

    public static void main(String[] args) {
        Solution_784 solution = new Solution_784();
        List<String> c = solution.letterCasePermutation("C");
        System.out.println(c);
    }
}
