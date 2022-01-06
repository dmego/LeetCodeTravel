package cn.dmego.leetcode.codetop.medium;

import java.util.Arrays;
import java.util.Random;

/**
 * 排序数组（快排）
 * @author dmego
 * @date 2022/01/04 20:34
 */
public class LC_912 {
    /**
     快排思想: 分治法，交换排序
     取主元 pivot --》随机主元==》移动到数组头部
     交换位置：
        双边循环法：
            头尾两个指针和 pivot 进行大小比较。然后交换位置
        单边循环法：
            单个指针和 pivot 进行大小比较，然后交换位置
     */

    public void quickSort(int[] nums, int startIndex, int endIndex) {
        if (startIndex >= endIndex) return;
        int index = partition2(nums, startIndex, endIndex);
        quickSort(nums, startIndex, index - 1);
        quickSort(nums, index + 1, endIndex);
    }

    // 单边循环法
    public int partition1(int[] nums, int startIndex, int endIndex) {
        int pivotIndex = new Random().nextInt(endIndex - startIndex + 1) + startIndex;
        int pivot = nums[pivotIndex];
        // 将主元交换到开始位置
        swap(nums, pivotIndex, startIndex);
        int mark = startIndex; // 小于等于主元的区域边界
        for (int i = startIndex + 1; i <= endIndex; i++) {
            // 如果遍历到小于 pivot 的元素，将其移动到区域内。
            if (nums[i] <= pivot) {
                swap(nums, mark + 1, i);
                mark++;
            }
        }
        // 最后还需要将主元移动到区域边界上
        swap(nums, startIndex, mark);
        return mark;
    }

    // 双边循环法
    public int partition2(int[] nums, int startIndex, int endIndex) {
        int pivotIndex = new Random().nextInt(endIndex - startIndex + 1) + startIndex;
        int pivot = nums[pivotIndex];
        // 将主元交换到开始位置
        swap(nums, pivotIndex, startIndex);
        int start = startIndex, end = endIndex;
        while (start < end) {
            // 先移动右边
            while (start < end && nums[end] > pivot) {
                end--;
            }
            while (start < end && nums[start] <= pivot) {
                start++;
            }
            // 交换时还需要判断
            if (start < end) {
                swap(nums, start, end);
            }
        }
        swap(nums, startIndex, start);
        return start;
    }

    public void swap(int[] nums, int index1, int index2) {
        int tmp = nums[index2];
        nums[index2] = nums[index1];
        nums[index1] = tmp;
    }

    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10,10,9,9,8,7,5,6,4,3,4,2};
        LC_912 lc = new LC_912();
        int[] array = lc.sortArray(nums);

        System.out.println(Arrays.toString(array));


    }

}
