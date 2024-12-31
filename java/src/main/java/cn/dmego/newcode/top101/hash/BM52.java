package cn.dmego.newcode.top101.hash;

import java.util.Arrays;

/**
 * @author dmego
 * @date 2022/03/09 10:41
 */
public class BM52 {

    public int[] FindNumsAppearOnce (int[] array) {
        // 先排序
        Arrays.sort(array);
        // 定义结果返回数组
        int[] res = new int[2];
        int f = 0;
        for (int i = 0; i < array.length;) {
            if (i == array.length - 1) {
                res[f++] = array[i++];
            }
            else if (array[i] != array[i + 1]) {
                res[f++] = array[i++];
            } else {
                i += 2;
            }
        }
        return res;
    }

    /**


     */
    public int[] FindNumsAppearOnce2 (int[] array) {
        int xor = 0;
        // 先将所有的数进行异或运算，得到的结果是 xor = a ^ b
        for (int i = 0; i < array.length; i++) {
            xor ^= array[i];
        }
        // 找到 a 与 b 二进制从右开始第一个值不相同的位置
        int mask = xor - (xor & (xor - 1));
        // 以 mask 将 array 分为两组
        int a = 0, b = 0;
        for (int i = 0; i < array.length; i++) {
            // 将 array 分为两组 (每组做异或运算)
            if ((array[i] & mask) == 0) {
                a ^= array[i];
            } else {
                b ^= array[i];
            }
        }
        return a < b ? new int[]{a, b} : new int[]{b, a};
    }

    public static void main(String[] args) {
        int[] array = new int[]{1,4,1,5};
        BM52 bm = new BM52();
        int[] ints = bm.FindNumsAppearOnce(array);
        System.out.println(Arrays.toString(ints));
    }

}
