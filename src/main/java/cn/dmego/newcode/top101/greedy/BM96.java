package cn.dmego.newcode.top101.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *
 */
public class BM96 {

    /**
     使用优先队列：
     - 首先对数组进行排序：
        - 开始时间相等的，按结束时间从小到大排序
        - 开始时间不相等的，按开始时间从小到大排序
     - 定义一个优先队列，队列里存储每个主持人主持活动的结束时间
     - 遍历数组
        - 如果队列为空，或者当前活动的开始时间在队列里找不到一个空闲的主持人，新增一个主持人到队列
        - 如果当前队列里队头主持人的活动结束时间小于当前活动开始时间，说明队头主持人空闲，更新队头主持人的主持结束时间
            - 队头主持人出队，新增一个主持人进去，主持人活动结束时间为当前活动结束时间
     - 最后队列的大小就是需要的最少主持人数
     */
    public int minmumNumberOfHost (int n, int[][] startEnd) {
        // 先将数组排序，按开始时间排序从小到大排序，如果开始时间相同，则按结束时间排序
        Arrays.sort(startEnd, (a, b) -> {
            if (a[0] > b[0]) return 1;
            else if (a[0] == b[0]) {
                if (a[1] > b[1]) return 1;
                else if (a[1] < b[1]) return -1;
                return 0;
            } else return -1;
        });
        // 优先队列，按照活动的结束时间(end)排序，end 小的排前面
        PriorityQueue<Integer> deque = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            // 如果队列里结束最早的活动还比当前活动开始还要晚，需要新加主持人
            // 否则需要更新最新主持人的活动结束时间
            if (!deque.isEmpty() && deque.peek() <= startEnd[i][0]) {
                deque.poll();
            }
            // 队列中添加主持人(或者是更新主持人的主持结束时间)
            deque.add(startEnd[i][1]);
        }
        return deque.size();
    }


    /**
     贪心：
     - 将活动开始时间写入一个列表 starts，进行排序。
     - 将活动结束时间写入一个列表 ends，进行排序。
     - 每次活动开始时，需要增加一个主持人上场，每次活动结束时候可以释放一个主持人。
     - 所以按照时间先后顺序对starts进行遍历，每次有活动开始count++，每次有活动结束count--
     - 最后 count 就是需要最少的主持人
     */
    public int minmumNumberOfHost3 (int n, int[][] startEnd) {
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = startEnd[i][0];
            end[i] = startEnd[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int j = 0, count = 0;
        for (int i = 0; i < n; i++) {
            count++;
            if (end[j] <= start[i]) {
                j++;
                count--;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        BM96 bm = new BM96();
        int[][] num = {{1,3}, {2,4}, {3,5}};
        int res = bm.minmumNumberOfHost3(3, num);
        System.out.println(res);

    }

}
