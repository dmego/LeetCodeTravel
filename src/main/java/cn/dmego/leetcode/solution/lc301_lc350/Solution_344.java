package cn.dmego.leetcode.solution.lc301_lc350;

/**
 * [344] 反转字符串
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 */
public class Solution_344 {
    /**
     使用首尾双指针，交换首尾位置字符
     */
    public void reverseString(char[] s) {
        if (s.length <= 1) return;
        int left = 0, right = s.length - 1;
        while (left < right) {
            swap(s, left, right);
            left++;
            right--;
        }
    }

    // 交换 a, b 下标的字符
    public void swap(char[] c, int a, int b) {
        char tmp = c[a];
        c[a] = c[b];
        c[b] = tmp;
    }
}
