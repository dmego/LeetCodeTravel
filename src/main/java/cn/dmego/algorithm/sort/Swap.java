package cn.dmego.algorithm.sort;

/**
 * 交换两个元素
 */
public class Swap {

    /**
     使用 temp 作为中间暂存元素
     */
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     利用加减运算
        a = a + b
        b = a - b = (a + b - b) = a
        a = a - b = (a + b - a) = b
     a 和 b 交换成功
     问题：
     */
    public static void swapCal(int[] nums, int i, int j) {

    }

    /**

     */
    public static void swapXor(int[] nums, int i, int j) {

    }


}
