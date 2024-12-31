package cn.dmego.newcode.top101.search;

/**
 *
 * 旋转数组找最小值
 * (存在重复元素)
 * @author dmego
 * @date 2022/03/04 08:45
 */
public class BM21 {

    /**
     在一个非降序数组上，如果在中间某一点进行了旋转，那么一定只会有下面两种情况
     - 数组呈现两段升序状态，最小值在旋转点，并且数组的头元素要大于数组的尾元素
     - 旋转后还是原数组，最小值就是第一个元素

     我们可以利用二分查找法更快速的定位最小值点，我们以尾元素 array[last] 为参照元素
     根据 array[mid] 与 array[last] 的大小来寻找 最小值的位置，也就是旋转点的位置
     - array[mid] > array[last] 说明 mid 落在前半段升序数组上，结果值在 [mid + 1, right] 所以：left = mid + 1
     - array[mid] < array[last] 说明 mid 落在后半段升序数组上，此时结果值应该在 [left, mid] 因为 array[mid] 也有可能就是最小值 所以
     right = mid
     - array[mid] = array[last] 这种情况下，不能确定结果值在哪边，只能逐步缩小范围，所以让 right = right - 1;

     */
    public int minNumberInRotateArray(int [] array) {
        int left = 0, right = array.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (array[mid] > array[right]) {
                left = mid + 1;
            } else if (array[mid] < array[right]) {
                right = mid;
            } else {
                right--;
            }
        }
        return array[left];
    }

    public static void main(String[] args) {
        BM21 bm = new BM21();
//        int[] array = new int[]{4,5,1,2,3};
        int[] array = new int[]{1,0,1,1,1};
        int min = bm.minNumberInRotateArray(array);
        System.out.println(min);
    }

}
