package cn.dmego.newcode.solution;

/**
 * 编辑距离2
 * 给定两个字符串str1和str2，再给定三个整数ic，dc和rc，分别代表插入、删除和替换一个字符的代价，
 * 请输出将 str1 编辑成 str2 的最小代价。
 *
 * 数据范围：0 ≤ ∣str1∣,∣str2∣ ≤ 5000， 0 ≤ ic , dc , rc ≤ 10000
 * 要求：空间复杂度 O(n)，时间复杂度 O(n^2)
 * @author dmego
 * @date 2022/04/22 17:52
 */
public class NC35 {

    /**
     注意：是将 str1 编辑成 str2, str2 不变，只能编辑 str1
     1. dp 数组的定义：dp[i][j] 表示将 str1(0~i-1) 编辑为 str2(0~j-1) 的最小代价
     2. 初始化：
        dp[i][0] 表示 str1(0~i-1) 变成空串的最小代价，删除 str1的字符， dp[i][0] = i * dc
        dp[0][j] 表示空串的 str1 变成 str2(0~j-1) 的最小代价,增加 str1的字符，dp[0][j] = j * ic
    3. 递推公式：
        当 str1[i - 1] == str2[j - 1] 时，本次不需要做任何操作，dp[i][j] = dp[i - 1][j - 1];
        当 str1[i - 1] != str2[j - 1] 时，为了让 str1 变成 str2, str1 可以做下面三种操作
            删除 str1[i - 1], dp[i][j] = dp[i - 1][j] + dc
            在 str1[i - 2] 前面添加一个元素(str2[j - 1]), dp[i][j] = dp[i][j - 1] + ic
            将 str1[i - 1] 替换成 str2[j - 1], dp[i][j] = dp[i - 1][j - 1] + rc
        取三种情况的最小值即可
     */
    public int minEditCost (String str1, String str2, int ic, int dc, int rc) {
        int l1 = str1.length();
        int l2 = str2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];
        for (int i = 0; i <= l1; i++) dp[i][0] = i * dc;
        for (int j = 0; j <= l2; j++) dp[0][j] = j * ic;
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j<= l2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int del = dp[i - 1][j] + dc;
                    int ins = dp[i][j - 1] + ic;
                    int rep = dp[i - 1][j - 1] + rc;
                    dp[i][j] = Math.min(del, Math.min(ins, rep));
                }
            }
        }
        return dp[l1][l2];
    }

    public static void main(String[] args) {
        // "abc","adc",5,3,100
        NC35 nc = new NC35();
        int res = nc.minEditCost("abc", "adc", 5,3,100);
        System.out.println(res);
    }

}
