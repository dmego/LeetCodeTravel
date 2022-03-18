package cn.dmego.leetcode.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 合并区间
 * @author dmego
 * @date 2022/01/13 10:44
 */
public class Solution_56 {

    public int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        int m = intervals.length;
        // 对二维数组按照 每个元素 的 第一个下标的值 进行排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        // 定义当前区间的最小值为第一个区间的最小值
        int min = intervals[0][0];
        // 定义当前区间的最大值为第一个区间的最大值
        int max = intervals[0][1];
        // 从第二个区间开始遍历
        for (int i = 1; i < m; i++) {
            // 如果遍历的区间 intervals[i] 最小值 > 当前区间的最大值，说明 遍历的区间无法和当前区间合并
            if (intervals[i][0] > max) {
                // 将当前区间添加到结果集 result 中
                result.add(new int[]{min, max});
                // 更新当前区间的最小值为 遍历区间 intervals[i] 的最小值
                min = intervals[i][0];
            }
            // 如果 遍历区间的最小值 < 当前区间的最大值，并且 区间都是排好序的遍历区间的最小值 > 当前区间的最小值
            // 此时这两个区间可以合并，合并后区间的最大值则需要取这两个区间中最大的一个
            max = Math.max(max, intervals[i][1]);
        }
        // 遍历结束之后，还需要将最后一个区间添加到结果集中
        result.add(new int[]{min, max});
        return result.toArray(new int[result.size()][]);
    }

    public int[][] merge2(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(o->o[0]));
        int[] inter = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] curr = intervals[i];
            if (curr[0] > inter[1]) {
                result.add(inter);
                inter = curr;
            } else {
                if (curr[1] >= inter[1]) {
                    inter[1] = curr[1];
                }
            }
        }
        result.add(inter);
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1,4},{2,3}};
        Solution_56 s = new Solution_56();
        int[][] merge = s.merge(intervals);
        System.out.println(Arrays.toString(merge));
    }


}
