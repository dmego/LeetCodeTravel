package cn.dmego.algorithm.sort;

/**
 * 选择排序
 */
public class SelectionSort {


    /*
     从第二个元素开始，称为：插入元素
     比较它与之前的元素，称为：比较元素
     假如 插入元素 下标为 i, 则从下标为 i-1 的 比较元素 开始比较
     如果 插入元素 < 比较元素，继续往前比较
     如果 插入元素 >= 比较元素，将 插入元素 插入 比较元素 后面

     */
    public int[] simpleInsertSort(int[] nums) {
        if (nums.length <= 1) return nums;
        for (int i = 1; i < nums.length; i++) {
            int insert = nums[i];
            for (int j = i - 1; j >= 0; j--) {
                
            }
        }

        return null;
    }


}
