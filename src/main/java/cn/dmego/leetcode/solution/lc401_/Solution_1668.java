package cn.dmego.leetcode.solution.lc401_;

/**
 * 最大重复子字符串
 */
public class Solution_1668 {

    /*
       解法一：双层 for 循环，暴力解法
     */
    public int maxRepeating(String sequence, String word) {
        int len = word.length();
        int lens = sequence.length();
        int index = 0;
        int res = 0;
        while (index < lens) {
            int ress = 0;
            int iindex = index;
            // 从 iindex 下标往后查找连续的重复子字符串
            while (iindex + len <= lens && sequence.substring(iindex, iindex + len).equals(word)) {
                // 找找后重复值+1
                ress++;
                // iindex 下标往后移动 word 长度
                iindex += len;
            }
            // 如果以 index 下标开始查找的最大重复子字符串结束后，更新 res 结果的值
            res = Math.max(res, ress);
            // index ++， 下一轮循环继续查找以index+1 下标开始的最大重复字符串的重复值
            index++;
        }
        return res;
    }

    /*
      解法二：二分查找
      首先要知道最大重复值一定是 sequence.length() / word.length() 的值,假设为 m。再大，构成的字符串就不是 sequence 的子串了。
      我们从 [1, m] 区间进行二分。每次二分时，通过 for 构建出 word 重复的字符串 sub，重复的次数就是 mid
      如果 sub 是 sequence 的子串，说明至少可以重复 mid 次，那么可以继续二分，将 l = mid + 1;
      否则，说明不能重复 mid 次，将 r = mid - 1;
      最后返回 r 的值。
     */
    public int maxRepeating2(String sequence, String word) {
        int r = (sequence.length() / word.length());
        int l = 0;
        while (l <= r) {
            int mid = (l + r) >> 1;
            StringBuffer buff = new StringBuffer();
            for (int i = 0; i < mid; i++) {
                buff.append(word);
            }
            if (sequence.contains(buff.toString())) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        String s1 = "aaabaaaabaaabaaaabaaaabaaaabaaaaba";
        String s2 = "aaaba";
        Solution_1668 s = new Solution_1668();
        int res = s.maxRepeating2(s1, s2);
        System.out.println(res);

    }

}
