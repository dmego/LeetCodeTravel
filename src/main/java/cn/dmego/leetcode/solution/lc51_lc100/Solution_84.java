package cn.dmego.leetcode.solution.lc51_lc100;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */
public class Solution_84 {

    /**
     *  矩形的面积 = 高 * 宽，由此想到是否可以通过固定 某一个条件，然后求另外一个条件的满足值
     *  暴力解法1：
     *      固定矩形的 宽，具体来说就是通过两层循环，枚举矩形的左右边界，来固定宽
     *      宽固定之后，找出这个宽范围内最小的柱子高（只有最小的柱子才是矩形最大的面积）, 也就是矩形的 高
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

    /**
     * 解法三：单调栈
     * 顺着解法二的思路，每次枚举一个高度，如何确定矩形的宽度呢？
     * 方法是 向这个柱子的左边看，找出第一个小于该高度的柱子
     *       向这个柱子的右边看，找出第一个小于该高度的柱子
     * 那么我们能不能用什么方法先把左右位置找到呢，如果你之前做过类似 "下一个更大的元素" 这种题目
     * 那么你就不难想到单调栈这种结构：单调栈在使用中，最重要的就是维持栈内的单调性
     *
     * 我们可以先从数组的尾部遍历找出每个高度右边第一个比其小的柱子，并记下下标，可以使用 数组 存储
     * 在从数组的头部遍历，找出每个高度左边第一个比其小的柱子，找到之后就可以计算矩形的面积了
     * 注意这里的宽度计算，左边找到第一个比其小的柱子后，其下标需要 + 1，这才是矩形的左边界
     * 如果左边没有比其小的，则左边届就是 0
     */
    public static int largestRectangleArea3(int[] heights) {
        int len = heights.length;
        int[] right = new int[len];
        Deque<Integer> deque = new LinkedList<>();

        for (int i = len - 1; i >= 0; i--) {
            while(!deque.isEmpty() && heights[deque.peek()] >= heights[i]) {
                deque.pop();
            }
            right[i] = deque.isEmpty() ? len : deque.peek();
            deque.push(i);
        }
        deque.clear();
        int area = 0;
        for (int i = 0; i < len; i++) {
            while (!deque.isEmpty() && heights[deque.peek()] >= heights[i]) {
                deque.pop();
            }
            area = Math.max(area, heights[i] * (right[i] - (deque.isEmpty() ? 0 : deque.peek() + 1)));
            deque.push(i);
        }
        return area;
    }

    /**
     * 解法四：单调栈的优化
     * 上边暴力解法虽然可以解题，但是通过不了。
     * 单调栈的解法三，虽然可以通过，但是确使用了两次 for 循环
     * 我们能不能在一次 for 循环内解决问题，也就是使用一次单调栈，就能把柱子的左右边界都求出来呢？
     *
     * 答案是可以的，我们以 从数组头部遍历 求出 宽度的左边界 为例：
     * 为了求当前高度的左边第一个比其小的，我们必须保持栈内元素的单调递增
     * 也就是说栈顶元素如果比当前元素小，那么栈顶元素就是我们要找的结果（因为栈是单调递增，栈顶永远是栈内最大的元素）
     * 那么如果栈顶元素比当前前元素大呢，一是可以说明栈顶元素不是当前元素的左边界。
     * 但是从另一个角度看，当前元素是不是就是 栈顶元素的右边界。当前元素刚好满足是栈顶元素右边第一个比其小的元素。
     * 那么就能确定栈顶元素的右边界了。
     *
     * 当遍历完之后之后，单调栈中还存在元素，说明 这些元素的右边界没有找到，也就是这些元素的右边没有比他再小的元素
     * 那么栈中这些元素的右边界就是边界的最后，也就是 length
     * 我们只需要把栈内的元素一个个弹出，求出面积即可
     */
    public static int largestRectangleArea4(int[] heights) {
        int len = heights.length;
        Deque<Integer> deque = new LinkedList<>();
        int[] left = new int[len];
        int area = 0;
        for (int i = 0; i < len; i++) {
            while(!deque.isEmpty() && heights[deque.peek()] >= heights[i]) {
                area = Math.max(area, heights[deque.peek()] * (i - left[deque.peek()]));
                deque.pop();
            }
            left[i] = deque.isEmpty() ? 0 : deque.peek() + 1;
            deque.push(i);
        }
        while (!deque.isEmpty()) {
            area = Math.max(area, heights[deque.peek()] * (len - left[deque.peek()]));
            deque.pop();
        }
        return area;
    }


    public static void main(String[] args) {
        int[] heights = {2,1,5,6,2,3};
        int area = largestRectangleArea2(heights);
        System.out.println(area);

    }

}
