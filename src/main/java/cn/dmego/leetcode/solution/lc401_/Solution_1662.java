package cn.dmego.leetcode.solution.lc401_;

/**
 * 1662. 检查两个字符串数组是否相等
 */
public class Solution_1662 {

    /*
       暴力解法：将每个数组里面的字符串进行拼接，最后比较两个字符串是否相等
     */
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuffer buff1 = new StringBuffer();
        StringBuffer buff2 = new StringBuffer();
        for (int i = 0; i < word1.length; i++) {
            buff1.append(word1[i]);
        }
        for (int i = 0; i < word2.length; i++) {
            buff2.append(word2[i]);
        }
        return buff1.toString().equals(buff2.toString());
    }

    /*
       双指针解法：
       定义 m 为 word1 中第 m 个字符串，n 为 word2 中第 n 个字符串；
       定义 i 为 word1[m] 字符串中第 i 个字符，j 为 word2[n] 字符串中第 j 个字符
       我们从 word1[0] 的第 0 个字符和 word2[0] 的第 0 个字符开始进行比较。
        当 word1[m].charAt(i) != word2[n].charAt(j) 时，直接 return false;
        当 i 遍历到 word1[m] 字符串最后时，i = word1[m].length(), 此时 m++, i = 0;
        当 j 遍历到 word2[n] 字符串最后时，j = word2[n].length(), 此时 n++, j = 0;

     */
    public boolean arrayStringsAreEqual2(String[] word1, String[] word2) {
        int m = 0, n = 0;
        int i = 0, j = 0;
        while (m < word1.length && n < word2.length) {
            if (word1[m].charAt(i) != word2[n].charAt(j)) {
                return false;
            } else {
                i++;
                j++;
            }
            if (i == word1[m].length()) {
                m++;
                i = 0;
            }
            if (j == word2[n].length()) {
                n++;
                j = 0;
            }
        }
        // 如果相等，那么最后一次比较的一定是两个数组最后一个字符串的最后一个字符，如果也相等的话
        // 就会有 m == word1.length && n == word2.length;
        return m == word1.length && n == word2.length;
    }

    public static void main(String[] args) {

        Solution_1662 s = new Solution_1662();

        String[] word1 = {"abc","d","defg"};
        String[] word2 = {"abcddef"};
        boolean res = s.arrayStringsAreEqual2(word1, word2);
        System.out.println(res);

    }
}
