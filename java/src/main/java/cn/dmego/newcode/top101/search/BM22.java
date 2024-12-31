package cn.dmego.newcode.top101.search;

/**
 * 比较版本号
 * @author dmego
 * @date 2022/03/03 09:12
 */
public class BM22 {

    public int compare (String version1, String version2) {
        int n = version1.length(), m = version2.length();
        int i = 0, j = 0;
        while (i < n || j < m) {
            String s1 = "0",  s2 = "0";
            if (i < n) {
                // 从 i 下标开始，找到第一个 . 的下标
                int ii = version1.indexOf(".", i);
                // 如果 ii = -1, 说明到了最后，ii = n
                if (ii == -1) ii = n;
                if (ii <= n) {
                    s1 = version1.substring(i, ii);
                    i = ii + 1;
                }
            }
            if (j < m) {
                int jj = version2.indexOf(".", j);
                if (jj == -1) jj = m;
                if (jj <= m) {
                    s2 = version2.substring(j, jj);
                    j = jj + 1;
                }
            }

            // 去除 s1 的前导 0，有可能有很多，
            int index = 0;
            while (index < s1.length() - 1 && s1.charAt(index) == '0') {
                index++;
            }
            s1 = s1.substring(index);

            // 去除 s2 的前导 0
            index = 0;
            while (index < s2.length() - 1 && s2.charAt(index) == '0') {
                index++;
            }
            s2 = s2.substring(index);

            // 如果 s2 和 s1 的长度不相等，可以直接根据长度判断大小
            if (s1.length() != s2.length()) {
                return s1.length() > s2.length() ? 1 : -1;
            } else {
                if (s1.compareTo(s2) > 0) return 1;
                else if (s1.compareTo(s2) < 0) return -1;
            }
        }
        return 0;
    }


    /**

     */
    public int compare3 (String version1, String version2) {
        // write code here
        if (version1 == null || version2 == null) return 0;
        // 将版本号字符串以 . 分割为字符串数字
        String[] str1 = version1.split("\\.");
        String[] str2 = version2.split("\\.");
        int len = Math.max(str1.length, str2.length);
        for (int i = 0; i < len; i++) {
            // 得到两个要比较的修订号，如果版本号没有指定某个下标处的修订号，则该修订号视为 0
            String s1 = i < str1.length ? str1[i] : "0";
            String s2 = i < str2.length ? str2[i] : "0";
            // 去除 s1 的前导 0，有可能有很多，
            int index = 0;
            while (index < s1.length() - 1 && s1.charAt(index) == '0') {
                index++;
            }
            s1 = s1.substring(index);

            // 去除 s2 的前导 0
            index = 0;
            while (index < s2.length() - 1 && s2.charAt(index) == '0') {
                index++;
            }
            s2 = s2.substring(index);

            // 如果 s2 和 s1 的长度不相等，可以直接根据长度判断大小
            if (s1.length() != s2.length()) {
                return s1.length() > s2.length() ? 1 : -1;
            } else {
                if (s1.compareTo(s2) > 0) return 1;
                else if (s1.compareTo(s2) < 0) return -1;
            }
        }
        return 0;
    }


}
