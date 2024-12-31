package cn.dmego.leetcode.solution.lc1_lc50;

/**
 * [11] 盛最多水的容器
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i])。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 */
public class Solution_11 {

    /**
     容器的容量计算公式为：宽 x 高
     暴力法：
     - 使用两层循环，固定容器一边，遍历容器另一边，然后以两边中较小的一方作为高
     双指针解法：
     - 容器的宽最大值是数组的长度，使用双指针，从首尾遍历。避免暴力法中多余计算
     - 宽是确定的：(right - left)，不能确定是高，而高取决于 min(height[left], height[right])
     - 双指针遍历，条件为 left < right (不能为等于，等于时，宽为 0，容量为 0)
        - 当 height[left] < height[right] 时:
            - 容量取决于较小的一侧，也就是高度值取 height[left]
            - left++, 循环继续移动 left 指针，也就是移动较低的一边，只有这样才能找到一个更大的高度。
        - 否则，高度值取 height[right], right++ 继续遍历右侧指针
     - 最后结果 res 取所有容量中的最大值
     */
    public int maxArea(int[] height) {
        int len = height.length;
        int left = 0, right = len - 1;
        int res = 0;
        while (left < right) {
            int area = 0;
            if (height[left] < height[right]) {
                area = (right - left) * height[left];
                left++;
            } else {
                area = (right - left) * height[right];
                right--;
            }
            res = Math.max(res, area);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        Solution_11 solution = new Solution_11();
        int res = solution.maxArea(height);
        System.out.println(res);
    }
}
