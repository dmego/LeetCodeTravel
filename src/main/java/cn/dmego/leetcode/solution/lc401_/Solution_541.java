package cn.dmego.leetcode.solution.lc401_;

/**
 * 反转字符串 II
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 */
public class Solution_541 {

    public String reverseStr(String s, int k) {
        int mod = s.length() / k; // mod 为偶数，最后的 les长度需要反转，mod 为奇数，less 长度直接拼接就行
        int les = s.length() % k;

        StringBuffer result = new StringBuffer(); // 存储结果
        StringBuffer reverse = new StringBuffer(); // 用来反转字符串

        for (int i = 1; i <= mod; i++) {
            String str = s.substring((i - 1) * k, i * k);
            if (i % 2 == 1) {
                str = reverse.append(str).reverse().toString();
                reverse.delete(0, k);
            }
            result.append(str);
        }
        String str = s.substring(mod * k);
        if (mod % 2 == 0 && les != 0) {
            result.append(reverse.append(str).reverse());
        } else {
            result.append(str);
        }
        return result.toString();
    }
}