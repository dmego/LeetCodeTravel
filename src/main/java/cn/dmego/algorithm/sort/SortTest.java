package cn.dmego.algorithm.sort;

import java.util.Arrays;

public class SortTest {

    public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {
        assert rangeL <= rangeR;
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++)
            arr[i] = (int) (Math.random() * (rangeR - rangeL + 1) + rangeL);
        return arr;
    }

    /*
     * 1 <= nums.length <= 5 * 10^4
     * -5 * 10^4 <= nums[i] <= 5 * 10^4
     */
    public static void main(String[] args) {
        Integer[] array = generateRandomArray(10, 2, 40);
        System.out.println("before: " + Arrays.toString(array));

        InsertSort insertSort = new InsertSort();
    }
}
