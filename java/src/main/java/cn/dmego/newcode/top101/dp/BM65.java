package cn.dmego.newcode.top101.dp;

import java.util.HashMap;

/**
 * 最长公共子序列（二）
 * 给定两个字符串str1和str2，输出两个字符串的最长公共子序列。
 * 如果最长公共子序列为空，则返回"-1"。目前给出的数据，仅仅会存在一个最长的公共子序列
 * @author dmego
 * @date 2022/04/20 16:17
 */
public class BM65 {

    public String LCS (String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) return "-1";
        // dp[i][j] 表示 s1(0~i-1) s2(0~j-1) 长度内的最长公共子序列长度
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        // key: ij, value: 子序列
        HashMap<String, String> map = new HashMap<>();
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                String key = i + "-" + j;
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    String oldKey = (i - 1) + "-" + (j - 1);
                    if (map.containsKey(oldKey)) {
                        map.put(key, map.get(oldKey) + s1.charAt(i - 1));
                    } else {
                        map.put(key, String.valueOf(s1.charAt(i - 1)));
                    }
                } else {
                    if (dp[i - 1][j] > dp[i][j - 1]) {
                        dp[i][j] = dp[i - 1][j];
                        if (map.get((i - 1) + "-" + j) != null) {
                            map.put(key, map.get((i - 1) + "-" + j));
                        }

                    } else {
                        dp[i][j] = dp[i][j - 1];
                        if(map.get(i + "-" + (j - 1)) != null) {
                            map.put(key,map.get(i + "-" + (j - 1)));
                        }
                    }
                }
            }
        }
        String key = s1.length() + "-" + s2.length();
        return map.get(key) == null ? "-1" : map.get(key);
    }

    public static void main(String[] args) {
        BM65 bm = new BM65();
        //String res = bm.LCS("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");
        String res = bm.LCS("111", "222");
        System.out.println(res);
    }


}
