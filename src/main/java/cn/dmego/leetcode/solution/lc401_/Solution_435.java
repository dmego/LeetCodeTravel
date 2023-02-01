package cn.dmego.leetcode.solution.lc401_;

import java.util.Arrays;
import java.util.Comparator;

/**
 * [435] 无重叠区间
 * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi]。返回
 * 需要移除区间的最小数量，使剩余区间互不重叠。
 */
public class Solution_435 {

    /**
     贪心：
     - 先将区间按照区间 end 进行排序
     - 以 intervals[0] (curr) 为第一个不重叠区间，遍历所有区间进行比较判断：
        - 因为 区间以 end 已经排好序了，只需要看 intervals[i][start] > curr[end]
        - 是则 intervals[i] 与 curr 重叠，intervals[i] 需要移除
        - 否则 intervals[i] 与 curr 不重叠，此时需要更新 curr = intervals[i]
        - 注意：当 intervals[i][start] = curr[end] 时，说明两个区间相邻，而不是重叠
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        // 将区间以 end 排序 (不能直接 (a, b) -> a[1] - b[1], 容易发送数据溢出)
        // 例如：Integer.MIN_VALUE - Integer.MAX_VALUE = 1, 不符合预期
        Arrays.sort(intervals, (a, b) -> {
            if (a[1] > b[1]) return 1;
            else if (a[1] < b[1]) return -1;
            return 0;
        });
        // 当前不重叠的区间
        int[] curr = intervals[0];
        int res = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < curr[1]) {
                res++;
            } else {
                curr = intervals[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        // 模拟溢出
        int i = Integer.MIN_VALUE - Integer.MAX_VALUE;
        Comparator<Integer> comparator = (o1, o2) -> o1 - o2;
        int compare = comparator.compare(Integer.MIN_VALUE, Integer.MAX_VALUE);
        int ic = Integer.compare(Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println(i);
        System.out.println(compare);
        System.out.println(ic);
    }
}
