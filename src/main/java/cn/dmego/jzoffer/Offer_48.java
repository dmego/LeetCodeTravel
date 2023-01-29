package cn.dmego.jzoffer;

/**
 * 剑指 Offer 48. 最长不含重复字符的子字符串
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度
 */
public class Offer_48 {

    /**
     同 leetCode 3 题
     滑动窗口 + String.indexOf()
     */
    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0;
        int res = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            // 从字符串 s 的 left 下标处开始，寻找字符 c 首次出现的下标 index
            int index = s.indexOf(c, left);
            // 如果 index 落在窗口 [left, right)，说明字符 c 已经在窗口出现过了
            if (index >= 0 && index < right) {
                // 将 left 移动到 index 的下一位
                left = index + 1;
            } else {
                // 否则更新 res 值
                res = Math.max(res, right - left + 1);
            }
            right++;
        }
        return res;
    }

}
