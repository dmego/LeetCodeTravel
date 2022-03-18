package cn.dmego.newcode.top101.doublepoint;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 合并区间
 * @author dmego
 * @date 2022/03/18 09:10
 */
public class BM89 {

    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    /**
     区间是一个 Interval 对象，有 start 和 end 两个属性
     我们可以先对原始的区间集合的每个区间的 start 属性进行排序
     选取第一个区间 inter 为当前要加入结果集合的区间
     从原始集合的第二个区间开始遍历：
     如果当前遍历区间 curr[0] > inter[1], curr 和 inter 没有重叠，直接将 inter 加入结果集，更新inter 为 curr
     如果当前遍历区间 curr[1] >= inter[1] 说明 curr 和 inter 是有重合的， 更新 inter 区间的 end 为 curr 的 end; 相当于合并区间
     因为还不确定下一个区间和 curr 是否有重合，所以不必将 inter 加入到结果集

     遍历结束后，将最后一个待加入的区间 inter 加入到结果集
     */
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        ArrayList<Interval> result = new ArrayList<>();
        if (intervals.isEmpty()) return result;
        intervals.sort(Comparator.comparingInt(o -> o.start));
        Interval inter = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
            if (curr.start > inter.end) {
                result.add(inter);
                inter = curr;
            } else {
                if (curr.end >= inter.end) {
                    inter = new Interval(inter.start, curr.end);
                }
            }
        }
        result.add(inter);
        return result;
    }

}
