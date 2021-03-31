package cn.dmego.leetcode;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */
public class Solution_88 {

    /**
     *  矩形的面积 = 高 * 宽，由此想到是否可以通过固定 某一个条件，然后求另外一个条件的满足值
     *  暴力解法1：
     *      固定矩形的 宽，具体来说就是通过两层循环，枚举矩形的左右边界，来固定宽
     *      宽固定之后，找出这个宽范围内最小的柱子高（只有最小的柱子才觉得矩形最大的面积）, 也就是矩形的 高
     *      最终求出 矩形面积 = 高 * 宽
     */
    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int len = heights.length;
        int area = 0;
        // 利用两层循环来枚举矩形的所有宽度的情况
        for (int left = 0; left < len; left++) {
            // minHeight  表示以 left 为起点的 最小柱子高度
            int minHeight = Integer.MAX_VALUE;
            for (int right = left + 1; right <= len; right++) {
                // minHeight 表示到目前为止， left 到 right前一个节点之间 的柱子的最小高度
                // heights[right] 表示right 当前节点的柱子高度
                minHeight = Math.min(minHeight, heights[right-1]);
                // (right - left) 就是矩形的宽度
                area = Math.max(area, (right - left) * minHeight);
            }
        }
        return area;
    }

    /**
     * 暴力解法2：
     *  通过枚举先固定高，然后找出以此为高的矩形的宽(也就是左右边界)
     *  先通过一次遍历固定高 h
     *  然后先向当前遍历到的柱子的 左边看，
     *      通过一次遍历，找出左边第一个高度开始小于当前柱子的柱子，记下该柱子的右边界值（w1）
     *      如果遍历到了头，则该矩形左边界值就是 w1 = 0
     *  再向当前遍历到的柱子的 右边看，
     *      通过一次遍历，找出右边第一个高度开始小于当前柱子的柱子，记下该柱子的左边界值(w2)
     *      如果遍历到了头，则该矩形的右边界值就是 w2 = len
     *  矩形面积 = h * (w2 - w1)
     */
    public static int largestRectangleArea2(int[] heights) {
        if(heights == null || heights.length == 0) {
            return 0;
        }
        int len = heights.length;
        int area = 0;
        // 遍历 高度，求以每个高度为 高的 矩形最大面积
        for (int i = 0; i < len; i++) {
            // l 表示以当前柱子为高的左边界值
            // r 表示以当前柱子为高的右边界值
            int l = i, r = i + 1;
            // 向 i 的左边看，找出第一个高度开始小于 heights[i] 的 l
            // (当l = 0 时，表示第一个柱子，不必进入循环)
            /*
                这里为什么是 heights[l-1], 因为 heights[l] 就是当前柱子，
                heights[l-1] 表示当前柱子的左边柱子
             */
            while (l > 0 && heights[l-1] >= heights[i]) {
                l--;
            }

            // 向 i 右看，找出第一个高度开始小于 heights[i] 的 r
            // (当 r = len 时，表示是最后一个柱子，不必进入循环)
            /*
                heights[r]表示当前柱子的右边柱子
             */
            while (r < len && heights[r] >= heights[i]) {
                r++;
            }
            area = Math.max(area, heights[i] * (r - l));
        }
        return area;
    }





    public static void main(String[] args) {
        int[] heights = {2,1,5,6,2,3};
        int area = largestRectangleArea2(heights);
        System.out.println(area);

    }

}
