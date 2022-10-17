package cn.dmego.leetcode.solution.lc251_lc300;

/**
 * 只出现一次的数字 III
 *
 * 给你一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案
 *
 * @author dmego
 * @date 2022/10/11 10:59
 */
public class Solution_260 {

    // 解法一：使用 HashMap 统计所有数字出现的次数，然后遍历找到只出现过一次的那两个数字

    /*
      解法二：位运算,
      1. 依据 136 题解法，先将数组中所有元素进行异或运算，得到 res = a ^ b
      2. 将数组所有元素进行异或，只能得到 a ^ b 的结果值 res, 不能区分出 a 和 b
      3. 如果能依据 a 和 b 的某个条件不同，将数组分为两组，则就能各种求出 a 和 b 了
      4. 异或运算的规则是：相同的值异或结果为 0， 不同的值异或结果为 1 (1 ^ 0 = 1, 1 ^ 1 = 0, 0 ^ 0 = 0)
      5. 所以我们可以找到 res 二进制中某一位值是 1 的位，在这一位上， a 和 b 的二进制值就不一样，所以可以根据这一位将数组进行划分
      6. 根据 ((res >> i) & 1) == 1 判断 第 i 位是否为 1
      7. 找到之后，同样根据这个运算 ((nums[i] >> cot) & 1) == 1 将 nums[i] 分为两组，
      8. 这两组元素各种进行异或运算的结果就是 a 和 b 的值
     */
    public int[] singleNumber(int[] nums) {
        if (nums.length == 2)
            return nums;

        // res 是 a ^ b 的结果
        int res = 0;
        for (int num : nums) {
            res = res ^ num;
        }
        // 找的 a 和 b 从左起，第一个不相等的二进制位
        // ^ 异或操作符, 不相同的值，异或结果为 1
        int cot = 0;
        while (cot < 32) {
            if (((res >> cot) & 1) == 1) {
                break;
            }
            cot++;
        }

        int a = 0, b = 0;
        for (int num : nums) {
            if (((num >> cot) & 1) == 1) {
                a = a ^ num;
            } else {
                b = b ^ num;
            }
        }
        return new int[] { a, b };
    }
}
