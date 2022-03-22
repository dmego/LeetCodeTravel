package cn.dmego.leetcode.solution;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 数据流的中位数
 * @author dmego
 * @date 2022/03/22 11:26
 */
public class Solution_295 {

    static class MedianFinder {

        PriorityQueue<Integer> maxQueue;
        PriorityQueue<Integer> minQueue;

        public MedianFinder() {
            this.maxQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
            this.minQueue = new PriorityQueue<>();
        }

        public void addNum(int num) {
            if (maxQueue.isEmpty() || num < maxQueue.peek()) {
                maxQueue.add(num);
            } else {
                minQueue.add(num);
            }
            if (maxQueue.size() > minQueue.size() + 1) {
                minQueue.add(maxQueue.poll());
            } else if (minQueue.size() > maxQueue.size()) {
                maxQueue.add(minQueue.poll());
            }
        }

        public double findMedian() {
            if (minQueue.size() == maxQueue.size()) {
                return ((double)minQueue.peek() + (double)maxQueue.peek()) / 2;
            } else {
                return (double)maxQueue.peek();
            }
        }

    }

    public static void main(String[] args) {
        MedianFinder mc = new MedianFinder();
        mc.addNum(1);
        mc.addNum(2);
        System.out.println(mc.findMedian());
        mc.addNum(3);
        System.out.println(mc.findMedian());
    }



}
