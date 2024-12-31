package cn.dmego.leetcode.solution.lc151_lc200;

/**
 * 比较版本号(版本号)
 *
 * 1 <= version1.length, version2.length <= 500
 * version1 和 version2 仅包含数字和 '.'
 * version1 和 version2 都是 有效版本号
 * version1 和 version2 的所有修订号都可以存储在 32 位整数 中
 * @author dmego
 * @date 2022/03/03 10:03
 */
public class Solution_165 {

    /**
     解法一：将版本号以 . 分割成数组，遍历数组，将每个修订号转成 int 类型
     然后进行比较, 例如："000" -> 0; "001" -> 1
     */
    public int compareVersion(String version1, String version2) {
        String[] str1 = version1.split("\\.");
        String[] str2 = version2.split("\\.");
        for (int i = 0; i < str1.length || i < str2.length; i++) {
            int x1 = 0, x2 = 0;
            if (i < str1.length) {
                x1 = Integer.parseInt(str1[i]);
            }
            if (i < str2.length) {
                x2 = Integer.parseInt(str2[i]);
            }
            if (x1 > x2) return 1;
            else if (x1 < x2) return -1;
        }
        return 0;
    }

    /**
     解法二：使用双指针，边遍历修订号，边比较
     */
    public int compareVersion2(String version1, String version2) {
        int n = version1.length(), m = version2.length();
        int i = 0, j = 0;
        while (i < n || j < m) {
            int x1 = 0, x2 = 0;
            if (i < n) {
                // 从 i 下标开始，找到第一个 . 的下标
                int ii = version1.indexOf(".", i);
                // 如果 ii = -1, 说明到了最后，ii = n
                if (ii == -1) ii = n;
                if (ii <= n) {
                    x1 = Integer.parseInt(version1.substring(i, ii));
                    i = ii + 1;
                }
            }
            if (j < m) {
                int jj = version2.indexOf(".", j);
                if (jj == -1) jj = m;
                if (jj <= m) {
                    x2 = Integer.parseInt(version2.substring(j, jj));
                    j = jj + 1;
                }
            }
            if (x1 != x2) {
                return x1 > x2 ? 1 : -1;
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        Solution_165  s = new Solution_165();
        String v1 = "1.1.0.0.0";
        String v2 = "1.1";
        int res = s.compareVersion2(v1, v2);
        System.out.println(res);
    }



}
