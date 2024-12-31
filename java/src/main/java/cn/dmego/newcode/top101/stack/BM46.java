package cn.dmego.newcode.top101.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * 最小的K个数
 * @author dmego
 * @date 2022/03/17 09:04
 */
public class BM46 {

    // sort 排序
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        if (k == 0 || input.length == 0) return result;
        Arrays.sort(input);
        for (int i = 0; i < k && i < input.length; i++) {
            result.add(input[i]);
        }
        return result;
    }

    // 使用快排
    public ArrayList<Integer> GetLeastNumbers_Solution2(int [] input, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        if (k == 0 || input.length == 0) return result;
        quickSort(input, 0, input.length - 1);
        for (int i = 0; i < k && i < input.length; i++) {
            result.add(input[i]);
        }
        return result;
    }

    // 实现快排
    public void quickSort(int[] input, int start, int end) {
        if (start >= end) return;
        int index = partition(input, start, end);
        quickSort(input, start, index - 1);
        quickSort(input, index + 1, end);
    }

    public int partition(int[] input, int start, int end) {
        int privotIndex = new Random().nextInt((end - start + 1) / 2) + start;
        swap(input, privotIndex, start);
        int privot = input[start];
        // 单边循环法
        int mark = start; // 小于等于 privot 的区间边界
        for (int i = start + 1; i <= end; i++) {
            if (input[i] <= privot) {
                swap(input, i, ++mark);
            }
        }
        swap(input, start, mark);
        return mark;
    }

    public void swap(int[] input, int index1, int index2) {
        int temp = input[index1];
        input[index1] = input[index2];
        input[index2] = temp;
    }

}
