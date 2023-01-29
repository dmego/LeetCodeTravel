package cn.dmego.jzoffer2;

/**
 * 剑指 Offer 46. 把数字翻译成字符串
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
 * 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法
 */
public class JZ46 {

    /*
     理解翻译的规则：0-a, 1-b, 2-c,... 25-z
     题目要求计算一个数字，利用这种规则，翻译成字符串，有多少种翻译方法？
     dp 数组定义：dp[i] 表示在数组字符串中，以下标 i 结尾的数字翻译成字符串有多少种翻译方法
     dp 数组初始化：
        当 i = 0 时，只有一个数字，所以 dp[0] = 1;
        当 i = 1 时，有 2 个数字，此时就需要分情况讨论了
            当 nums[0] == 0 时，只有一种翻译方法，dp[1] = 1;
            当 nums[0] == 1 时，nums[0]nums[1] 组成的两位数，也能组成字符串，所以 dp[1] = 2;
            当 nums[0] == 2 时，只有当 nums[1] <= 5, 组成的两位数才小于等于 25，此时 dp[1] = 2, 其他情况 dp[1] = 1;
            当 nums[0] > 2, 也只有一种翻译方法，dp[1] = 1;

     递推公式：当我们算出 dp[0] 和 dp[1] 后，dp[2] 也能根据前两个结果求出
        当 nums[i - 1]nums[i] 组成的两位数不在[10, 25] 之间，只有一种翻译方式：dp[i] = dp[i - 1]
        当 nums[i - 1]nums[i] 组成的两位数在[10, 25] 之间，就有两种翻译方式，一位一位翻译时，翻译种数就是 dp[i - 1]
            当把 nums[i-1]nums[i] 按两位来翻译时，翻译种数就是 dp[i - 2]
            所以 dp[i] = dp[i - 1] + dp[i - 2] (可以对比一下跳台阶的递推公式)

     */
    public int translateNum(int num) {
        // 0 ~ 9 的个位数，只有一种翻译方式
        if (0 <= num && num <= 9) return 1;
        String nums = String.valueOf(num);
        int[] dp = new int[nums.length()];
        dp[0] = 1;
        int value = Integer.parseInt(nums.substring(0, 2));
        if (10 <= value && value <= 25) {
            dp[1] = 2;
        } else {
            dp[1] = 1;
        }

        for (int i = 2; i < nums.length(); i++) {
            int val = Integer.parseInt(nums.substring(i - 1, i + 1));
            if (10 <= val && val <= 25) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[nums.length() - 1];
    }

}
