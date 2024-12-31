package cn.dmego.newcode.top101.stack;

import java.util.PriorityQueue;

/**
 * 数据流中的中位数
 * @author dmego
 * @date 2022/03/17 10:06
 */
public class BM48 {

    // 大顶堆，保存数组排序后的 前一半数据
    PriorityQueue<Integer> maxQueue = new PriorityQueue<>((o1, o2)-> o2 - o1);
    // (默认的)小顶堆，保存数组排序后的 后一半数据
    PriorityQueue<Integer> minQueue = new PriorityQueue<>();

    // 接收数据流的数据
    public void Insert(Integer num) {
        // 如果大顶堆为空，或者大顶堆堆顶元素 > 当前接收元素， 当前元素入大顶堆
        if (maxQueue.isEmpty() || maxQueue.peek() > num) {
            maxQueue.add(num);
        } else {
            // 否则，当前元素入小顶堆
            minQueue.add(num);
        }
        // 保证大顶堆永远和小顶堆元素一样多或者比小顶堆多一个
        // 如果 大顶堆元素 比 小顶堆元素个数多了超过一个，需要将大顶堆堆顶元素移动到小顶堆堆顶
        if (maxQueue.size() > minQueue.size() + 1) {
            minQueue.add(maxQueue.poll());
        } else if (minQueue.size() > maxQueue.size()) {
            // 如果小顶堆元素个数比大顶堆多，将小顶堆堆顶元素移动到大顶堆
            maxQueue.add(minQueue.poll());
        }
    }

    public Double GetMedian() {
        if(maxQueue.isEmpty()) return 0.0;
        if (maxQueue.size() > minQueue.size()) {
            return (double)(maxQueue.peek());
        } else {
            return (double) (maxQueue.peek() + minQueue.peek()) / 2;
        }
    }


    public static void main(String[] args) {
        BM48 bm = new BM48();
        //[5,2,3,4,1,6,7,0,8]
        bm.Insert(5);
        System.out.print(bm.GetMedian()+",");
        bm.Insert(2);
        System.out.print(bm.GetMedian()+",");
        bm.Insert(3);
        System.out.print(bm.GetMedian()+",");
    }
}
