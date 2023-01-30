package cn.dmego.leetcode.solution.lc1_lc50;

/**
 * [42] 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 */
public class Solution_42 {
    /**
     核心思路：
     - 我们要求的是在由这些柱子组成的一个二维空间里能接多少雨水，但是如果我们想直接求整体的结果是不行的
     - 先求局部，然后合并求整个整体，这里的局部就是：对于第 `i` 个柱子的位置，能接多少雨水
     - 对于 `i` 位置的柱子，能接多少雨水，取决于他自身的柱高，还有他左右两侧的最高柱子高度中的较小一个(木桶原理，取决于最小的一块木板)
     - 简化公式为：winter[i] = min(max(height[0]~ height[i]), max(height[i] ~ height[n]) - height[i]
     */


    /**
     暴力法：时间复杂度 O(N^2), 空间复杂度 O(N)
     - 循环遍历求每个 height[i] 能接多少雨水
     - 对于每个 i 处柱子
        - 遍历求出左边的最高柱子高度: lMax
        - 遍历求出右边的最高柱子高度: rMax
     - 求出 i 处 柱子能接的雨水量 winter
     - 当 winter 大于 0 时，统计到结果 res 中
     */
    public int trap(int[] height) {
        int lMax = 0, rMax = 0;
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            // 求 lMax 值
            for (int j = 0; j < i; j++) {
                lMax = Math.max(lMax, height[j]);
            }
            // 求 rMax 值
            for (int j = i + 1; j < height.length - 1; j++) {
                rMax = Math.max(rMax, height[j]);
            }
            // 求 i 下标能接多少雨水
            int winter = Math.min(lMax, rMax) - height[i];
            // 汇总 res
            if (winter > 0) res += winter;
            // 重置 lMax, rMax
            lMax = 0; rMax = 0;
        }
        return res;
    }

    /**
     备忘录优化: 时间复杂度 O(N) 空间复杂度 O(N)
     - 在暴力解法中，我们每次都需要遍历求出 `i` 下标处左右两侧的最高柱子高度
     - 定义两个数组 lMax[], rMax[];
        - lMax[i] 表示 `i` 下标左边的最高柱子高度
        - rMax[i] 表示 `i` 下标右边的最高柱子高度
     - 先计算出 lMax[] 和 rMax[] 两个数组的值，然后使用一层遍历求出结果 winter
     - 当 winter 大于 0 时，统计到结果 res 中
     */
    public int trap2(int[] height) {
        int len = height.length;
        int[] lMax = new int[len];
        int[] rMax = new int[len];
        int res = 0;

        // 初始化首尾，(实际上首尾两处下标是不能接住雨水的)
        lMax[0] = height[0];
        rMax[len - 1] = height[len - 1];

        // 从前往后遍历求出 lMax[i]
        for (int i = 1; i < len; i++) {
            // lMax[i - 1] 表示 i-1 左侧的最高柱高，lMax[i] = max(height[i - 1], lMax[i])
            lMax[i] = Math.max(lMax[i - 1], height[i - 1]);
        }

        // 从后往前遍历求出 rMax[i]
        for (int i = len - 2; i >= 0; i--) {
            rMax[i] = Math.max(rMax[i + 1], height[i + 1]);
        }

        // 按照公式，遍历求出 res, 跳过首尾
        for (int i = 1; i < len - 1; i++) {
            int winter = Math.min(lMax[i], rMax[i]) - height[i];
            if (winter > 0) res+= winter;
        }
        return res;
    }


    /**
      双指针解法：时间复杂度 O(N) 空间复杂度 O(1)
      - 回到问题本身：下标 `i` 处能接多少雨水取决于 `i` 左右两侧的最高柱子高度中的较小一个(木桶原理，取决于最小的一块木板)
      - 定义双指针：left = 1, right = len - 2; 跳过首尾
      - 定义 lMax, 和 rMax，与前面解法不同的是：
        - lMax 表示 height[0, left) 区间，也就是下标 left 左边最高柱子高度
        - rMax 表示 height(left, len] 区间，也就是下标 right 右边最高柱子高度
      - 双指针遍历
        - lMax = max(lMax, height[left - 1])
        - rMax = max(rMax, height[right + 1])
        - 当 lMax < rMax 时，可以求出下标 left 处能接多少雨水  winter
            - 因为不管下标 left 右边的最高柱高是不是 rMax, 能接多少雨水只取决于左边 lMax
            - left++; 循环继续移动 left 指针，也就是求出所有以 rMax 作为右边较高柱时，下标 left 处能接的雨水
        - 当 rMax > lMax 时，同样的可以求出下标 right 处能接多少雨水  winter
      - 当 winter 大于 0 时，统计到结果 res 中
     */
    public int trap3(int[] height) {
        int len = height.length;
        int left = 1, right = len - 2;
        int lMax = 0, rMax = 0;
        int res = 0;
        while (left <= right) {
            // lMax 表示上一次也就是下标 left - 1 处左边最高柱子高度
            lMax = Math.max(lMax, height[left - 1]);
            rMax = Math.max(rMax, height[right + 1]);

            int winter = 0;
            if (lMax < rMax) {
                winter = lMax - height[left];
                left++;
            } else {
                winter = rMax - height[right];
                right--;
            }
            if (winter > 0 ) res += winter;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        Solution_42 solution = new Solution_42();
        int res = solution.trap3(height);
        System.out.println(res);
    }


}
