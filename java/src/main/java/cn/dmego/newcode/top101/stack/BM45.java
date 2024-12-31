package cn.dmego.newcode.top101.stack;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 滑动窗口最大值
 * @author dmego
 * @date 2022/03/17 08:59
 */
public class BM45 {

    /**
      使用优先队列，大顶堆，保证堆顶元素是当前窗口的最大值
     使用双指针来滑动窗口：每次将窗口前一个加入队列，后一个移出队列
     滑动窗口后，将最大值 queue.peek() 加入到结果集
     */
    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        ArrayList<Integer> result = new ArrayList<>();
        // 窗口 = 0 或者窗口大于数组长度，返回 空
        if (size == 0 || size > num.length) return result;
        // 定义优先队列，大顶堆
        PriorityQueue<Integer> deque = new PriorityQueue<>((o1,o2) -> o2 - o1);
        // 初始化队列
        for (int i = 0; i < size; i++) {
            deque.add(num[i]);
        }
        // 第一个窗口的最大值加入结果集
        result.add(deque.peek());
        int i = 0, j = size;
        // 双指针 滑动窗口，遍历数组
        while (j < num.length) {
            // 窗口前一个加入, 后一个移除，以此来滑动窗口
            deque.add(num[j]);
            deque.remove(num[i]);
            // 滑动后，将最新窗口的最大值加入结果集
            result.add(deque.peek());
            i++;
            j++;
        }
        return result;
    }
}
