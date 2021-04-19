package cn.dmego.leetcode;

/**
 给你一个字符串 s，由若干单词组成，单词之间用空格隔开。返回字符串中最后一个单词的长度。如果不存在最后一个单词，请返回 0。

 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串
 */
public class Solution_58 {

    /**
       这里是一道简单题，我们看完题目，都会有一个思路：
       那就是倒着遍历字符串，然后计数，如果遇到 空格，说明最后一个单词已经遍历完了
       注意：如果字符串结尾就是空格，这种情况需要单独判断
     */
    public int lengthOfLastWord(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (count == 0) {
                    continue;
                } else {
                    break;
                }
            }
            count ++;
        }
        return count;
    }
}
