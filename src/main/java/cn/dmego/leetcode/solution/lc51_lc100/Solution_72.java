package cn.dmego.leetcode.solution.lc51_lc100;

/**
 * 编辑距离
 * @author dmego
 * @date 2022/04/22 09:55
 */
public class Solution_72 {

    /**
     - dp 数组的定义：dp[i][j] 表示 word1(0~i-1) 与 word2(0~j-1) 达到相等需要做的最少操作
     - 初始化：
     - dp[i][0] 表示 word(0~i-1) 变成空串的最少操作(编辑距离)，很明显就是删除所有的字符，dp[i][0] = i
     - dp[0][j] 同样的道理，变成空串需要删除所有的字符，dp[0][j] = j
     - 递推公式：
     - 当 word1[i - 1] == word2[j - 1], 我们在这一步不需要做任何操作就能达到相等，所以 dp[i][j] 的值等于
     word1[i - 1] 和 word2[j - 1] 没有比较之前的值，也就是 dp[i - 1][j - 1], 即 dp[i][j] = dp[i - 1][j - 1];
     - 当 word1[i - 1] != word2[j - 1] 时，此时我们需要做一些操作，这里的操作有三种：删除一个字符，增加一个字符，替换一个字符。
     - 删除一个元素：
     - 删除 word1[i - 1], 那么就是 word1(0~i-2) 与 word2(0~j-1) 达到相等需要做的最少操作, 加上当前的一个删除操作。dp[i][j] = dp[i - 1][j] + 1;
     - 删除 word1[j - 1], 同理，dp[i][j] = dp[i][]j - 1] + 1
     - 添加一个元素：
     - 在 word1[i - 2] 前添加一个元素，与 word2[j - 1] 相等。dp[i][j] 等于没添加之前 word1(0~i-2) 与 word2(j-1) 达到相等
     要做的最少操作，再加上当前一个添加操作，dp[i][j] = dp[i - 1][j] + 1;
     - 在 word2[j - 2] 前添加一个元素，与 word1[i - 1] 相等。同理的。dp[i][j] = dp[i][j - 1] + 1;
     - 替换一个元素： 将 word1[i - 1] 替换为 word2[j - 1], 或者反过来。这种情况 dp[i][j] = dp[i - 1][j - 1] + 1;
     - dp[i][j] 取上面三种情况的最小值即可
     - 遍历顺序：从左往右
     - 最后结果：dp[word1.length()][word2.length()];
     */
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++)
            dp[i][0] = i;
        for (int j = 0; j <= word2.length(); j++)
            dp[0][j] = j;
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}
