package cn.dmego.leetcode.solution.lc101_lc150;

/**
 *[125] 验证回文串
 * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
 */
public class Solution_125 {

    public boolean isPalindrome(String s) {
        // 去除首位空格，将字母全转为小写
        s = s.trim().toLowerCase();
        // 如果是空串，直接返回 true
        if (s.length() == 0)
            return true;
        // 定义首尾双指针
        int i = 0, j = s.length() - 1;
        while (i <= j) {
            // 如果首指针位置的字符不是字母或数字，i++, continue 继续遍历下一个
            char ci = s.charAt(i);
            if (!(('a' <= ci && ci <= 'z') || ('0' <= ci && ci <= '9'))) {
                i++;
                continue;
            }
            // 如果尾指针位置的字符不是字母或数字，j--, continue 继续遍历下一个
            char cj = s.charAt(j);
            if (!(('a' <= cj && cj <= 'z') || ('0' <= cj && cj <= '9'))) {
                j--;
                continue;
            }
            // 如果两个字符不相等。直接返回 false
            if (ci != cj) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
