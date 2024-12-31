package cn.dmego.newcode.top101.dp;

/**
 * BM69 把数字翻译成字符串
 * 有一种将字母编码成数字的方式：'a'->1, 'b->2', ... , 'z->26'。
 *
 * 现在给一串数字，返回有多少种可能的译码结果
 */
public class BM69 {
    /*
         理解翻译的规则：1-a, 2-b, 3-c,... 26-z
         题目要求计算一个数字，利用这种规则，翻译成字符串，有多少种翻译方法？
         dp 数组定义：dp[i] 表示在数组字符串中，以下标 i 结尾的数字翻译成字符串有多少种翻译方法
         dp 数组初始化：
            当 i = 0 时，只有一个数字，所以 dp[0] = 1;
            当 i = 1 时，有 2 个数字，此时就需要分情况讨论了
                当 nums[0] == 0 时，无法被翻译
                当 nums[0] == 1 时，nums[0]nums[1] 组成的两位数，也能组成字符串，所以 dp[1] = 2;
                当 nums[0] == 2 时，只有当 nums[1] <= 6, 组成的两位数才小于等于 26，此时 dp[1] = 2, 其他情况 dp[1] = 1;
                当 nums[0] > 2, 也只有一种翻译方法，dp[1] = 1;

         递推公式：当我们算出 dp[0] 和 dp[1] 后，dp[2] 也能根据前两个结果求出
            当 nums[i - 1]nums[i] 组成的两位数不在[11, 19] 和 [21, 26] 之间，只有一种翻译方式：dp[i] = dp[i - 1]
            当 nums[i - 1]nums[i] 组成的两位数在[11, 19] 或者 [21, 26] 之间，就有两种翻译方式，一位一位翻译时，翻译种数就是 dp[i - 1]
                当把 nums[i-1]nums[i] 按两位来翻译时，翻译种数就是 dp[i - 2]
                所以 dp[i] = dp[i - 1] + dp[i - 2] (可以对比一下跳台阶的递推公式)

            注意：当 nums[i] = 0 时，如果 nums[i - 1] != 1 或者 2，无法翻译，结果是 0
         */
    public int solve (String nums) {
        // 如果数字字符串以 0 开头，无法被翻译，直接返回 0
        if (nums.charAt(0) == '0') return 0;
        // 字符串长度为 1，只有一种翻译结果
        if (nums.length() == 1) return 1;
        int[] dp = new int[nums.length()];
        dp[0] = 1;
        int value = Integer.parseInt(nums.substring(0, 2));
        // 除了 10，20 之外，其他的以 0 结尾的两位数都不能被翻译
        if (nums.charAt(1) == '0' && !(value == 10 || value == 20)) {
            return 0;
        }
        // 当两位数在 [11, 29] 或者 [21, 26] 之间时，这两位数才能被翻译成一个字母
        if ((11 <= value && value <= 19) || (21 <= value && value <= 26)) {
            dp[1] = 2;
        } else {
            dp[1] = 1;
        }

        for (int i = 2; i < nums.length(); i++) {
            int val = Integer.parseInt(nums.substring(i - 1, i + 1));
            if (nums.charAt(i) == '0' && !(val == 10 || val == 20)) {
                return 0;
            }
            if ((11 <= val && val <= 19) || (21 <= val && val <= 26)) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[nums.length() - 1];
    }

    public static void main(String[] args) {
        BM69 bm = new BM69();
        int res = bm.solve("160");
        System.out.println(res);
    }


}
