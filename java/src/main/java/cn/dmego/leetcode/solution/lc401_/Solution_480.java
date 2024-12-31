package cn.dmego.leetcode.solution.lc401_;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 滑动窗口中位数
 *
 * @author dmego
 * @date 2022/9/29 19:14
 */
public class Solution_480 {

    public double[] medianSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        double[] res = new double[len - k + 1];
        /*
            定义对顶堆，由两个优先队列组成
            默认小顶堆：堆顶元素是最小值
            大顶堆：堆顶元素是最大值

            在遍历时，元素先和大顶堆堆顶 big 比较，如果小于 big，则加入到大顶堆，否则加入到小顶堆
            这样，一个窗口的元素就会在对顶堆中形成有序：
                大顶堆保持有序窗口元素的前半段，小顶堆保存有序窗口元素的后半段
                中位数，直接取两个堆顶元素即可，参考：295 题
            注意比较两个 int 值大小时，不要相减，有可能溢出，用 Integer.compare() 比较
         */
        PriorityQueue<Integer> minQueue = new PriorityQueue<>((o1, o2) -> Integer.compare(nums[o1], nums[o2]));
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>((o1, o2) -> Integer.compare(nums[o2], nums[o1]));
        int left = 0;
        for (int right = 0; right < len; right++) {
            if (maxQueue.isEmpty() || nums[right] < nums[maxQueue.peek()]) {
                maxQueue.add(right);
            } else {
                minQueue.add(right);
            }
            adjust(minQueue, maxQueue);

            if (right >= k - 1) {
                if (maxQueue.size() > minQueue.size()) {
                    res[left] = (double) nums[maxQueue.peek()];
                } else if (maxQueue.size() == minQueue.size()) {
                    res[left] = ((double) nums[minQueue.peek()] / 2 + (double) nums[maxQueue.peek()] / 2);
                }
                // 这里要做的操作就是要删除即将要出窗口的元素 nums[left]
                // 如果 小顶堆不为空，且 nums[left] >= 小顶堆的堆顶，说明 nums[left] 在 小顶堆中
                if (!minQueue.isEmpty() && nums[left] >= nums[minQueue.peek()]) {
                    minQueue.remove(left);
                }
                // 如果 大顶堆不为空，且 nums[left] <= 大顶堆的堆顶，说明 nums[left] 在 大顶堆中
                if (!maxQueue.isEmpty() && nums[left] <= nums[maxQueue.peek()]){
                    maxQueue.remove(left);
                }
                adjust(minQueue, maxQueue);
                left++;
            }
        }
        return res;
    }

    public void adjust(PriorityQueue<Integer> minQueue, PriorityQueue<Integer> maxQueue) {
        if (maxQueue.size() > minQueue.size() + 1) {
            minQueue.add(maxQueue.poll());
        } else if (minQueue.size() > maxQueue.size()) {
            maxQueue.add(minQueue.poll());
        }
    }

    public static void main(String[] args) {
        Solution_480 s = new Solution_480();
        int[] nums = {1,2,3,4,2,3,1,4,2};
        int k = 3;
        double[] doubles = s.medianSlidingWindow(nums, k);
        System.out.println(Arrays.toString(doubles));
    }

}
