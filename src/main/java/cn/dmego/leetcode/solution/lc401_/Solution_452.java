package cn.dmego.leetcode.solution.lc401_;

import java.util.Arrays;

/**
 * [452] 用最少数量的箭引爆气球
 */
public class Solution_452 {


    /**
     同 435 题，但是不同的是，" 满足 xstart≤ x ≤ xend，则该气球会被 引爆"
     也就是说当两个区间想邻时，也算重叠，射一箭，也是一起引爆
     */
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b) -> {
            if (a[1] > b[1]) return 1;
            else if (a[1] < b[1]) return -1;
            return 0;
        });
        int[] curr = points[0];
        // 初始化时，需要射一箭
        int res = 1;
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > curr[1]) {
                curr = points[i];
                // 遇到不重叠的情况才需要射箭
                res++;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[][] points = {{1,2},{3,4},{5,6},{7,8}};
        Solution_452 solution = new Solution_452();
        int res = solution.findMinArrowShots(points);
        System.out.println(res);
    }

}
