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

    public static void main(String[] args) {
        int[] array = new int[]{1,4,1,5};
        BM52 bm = new BM52();
        int[] ints = bm.FindNumsAppearOnce(array);
        System.out.println(Arrays.toString(ints));
    }

}
