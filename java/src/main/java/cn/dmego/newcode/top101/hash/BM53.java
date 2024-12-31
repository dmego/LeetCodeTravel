package cn.dmego.newcode.top101.hash;


/**
 * 缺失的第一个正整数  (参考 Solution_41)
 * @author dmego
 * @date 2022/03/09 11:23
 */
public class BM53 {

    public int minNumberDisappeared (int[] nums) {
        int n = nums.length;
        int[] res = new int[n + 1];
        for (int num : nums) {
            if (num > 0 && num <= n) {
                res[num] = 1;
            }
        }
        for (int i = 1; i < res.length; i++) {
            if (res[i] == 0) return i;
        }
        return n + 1;
    }

}
