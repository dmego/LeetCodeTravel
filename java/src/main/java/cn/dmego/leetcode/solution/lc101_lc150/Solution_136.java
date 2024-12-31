package cn.dmego.leetcode.solution.lc101_lc150;

/**
 * 只出现一次的数字
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 
 * @author dmego
 * @date 2022/10/11 10:50
 */
public class Solution_136 {

    // 解法一：利用 HashMap 统计所有元素出现的次数，然后遍历找到只出现一次的数字

    /**
     解法二：利用 异或运算特性
      X ^ X = 0, 0 ^ X = X
     */
    public int singleNumber(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res = res ^ nums[i];
        }
        return res;
    }

}
