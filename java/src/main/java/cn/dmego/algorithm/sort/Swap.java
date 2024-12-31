package cn.dmego.algorithm.sort;

/**
 * 交换两个元素
 * 在测试过程中，性能耗时：SwapTemp < swapCal < swapXor
 * 使用 temp 作为中间变量交换性能最好
 */
public class Swap {

    /**
     使用 temp 作为中间变量交换
     */
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     利用加减运算交换
        a = a + b
        b = a - b = (a + b - b) = a
        a = a - b = (a + b - a) = b
     a 和 b 交换成功
     问题：当 a + b > Integer.MAX_VALUE, 有越界问题
     */
    public static void swapCal(int[] nums, int i, int j) {
        nums[i] = nums[i] + nums[j];
        nums[j] = nums[i] - nums[j];
        nums[i] = nums[i] - nums[j];
    }

    /**
     利用异或运算交换(a ^ a = 0, a ^ 0 = a)
        a = a ^ b
        b = a ^ b = (a ^ b) ^ b = a ^ 0 = a
        a = a ^ b = (a ^ b) ^ a = b ^ 0 = b
     */
    public static void swapXor(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

}
