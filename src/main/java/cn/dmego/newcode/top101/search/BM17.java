package cn.dmego.newcode.top101.search;

/**
 * 二分查找-I
 * @author dmego
 * @date 2022/03/03 10:08
 */
public class BM17 {

    public int search (int[] nums, int target) {
        // write code here
        if (nums.length == 0) return -1;
        int left = 0, right = nums.length;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

}
