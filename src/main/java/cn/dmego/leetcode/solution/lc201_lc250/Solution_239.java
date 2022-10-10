package cn.dmego.leetcode.solution.lc201_lc250;

import java.util.*;

/**
 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

 返回滑动窗口中的最大值
 */
public class Solution_239 {

    /**
     解法一：暴力求解
     枚举所有的窗口，然后求出窗口内的最大值
     （该解法在 leetCode 内不能通过，超过时间限制）
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int[] res = new int[len - k + 1];
        for (int i = 0; i + k <= len; i++) {
            int max = nums[i];
            for (int j = i; j < i + k; j++) {
                max = Math.max(nums[j], max);
            }
            res[i] = max;
        }
        return res;
    }

    /**
     解法二：采用单调队列(双端队列)
     思路分析：
     1.理解窗口：窗口的大小为 k, 第一个窗口内的元素是{nums[0]~nums[k-1]},
       理解窗口向右滑动：在窗口左边第一个元素将从窗口出去，窗口右边将新进一个新元素。
       如果我们抽象来看，就是一端出去，一端进来---> 双端队列的特性。
       对于 长度为 len 的数组， 大小为 k 的窗口从左到右可以滑动次：len - k + 1;
     2.题目要求：要求每个窗口(窗口每向右滑动一次)内元素的最大值。
       单调队列介绍：在程序运行的过程中，我们需要维持队列内部元素的单调性(单调递增或递减)
       为了降低每滑动一次 寻找窗口内 元素最大值的时间复杂度，我们可以采用单调队列来做：
        2.1 定义一个 Deque 双端队列，
            定义滑动窗口的左边界 left 右边界 right，开始时 left = right = 0
            定义结果数组，res[] = new int[len - k +1]
        2.2 遍历数组，循环条件是 right <= len - k;
            2.2.1 当队列不为空，且 nums[i] > 队尾元素时，循环移除队尾元素，直到新队尾大于nums[i]
                  或者队列为空，将 nums[i] 插入队尾 (维持队列单调递减：队首最大，队尾最小)
            2.2.2 当窗口未形成时，也就是right < k 时，我们每次只进行 right++
                  当窗口形成，也就是 right = k 之后，先执行 2.2.1
                  然后再循环判断队首元素：当队首元素下标 < left，说明队首元素不在窗口内，队首出队
                  继续判断，直到 队首下标 >= left，则队首元素就是窗口内的最大值。存放到 res[] 即可
                  left++
            2.2.3 循环结束后，返回 res[] 数组

     */
    public static int[] maxSlidingWindow2(int[] nums, int k) {
        int len = nums.length;
        // 定义结果数组，大小就是窗口要移动的次数
        int res[] = new int[len - k + 1];
        int left = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        for (int right = 0; right < len; right++) {
            // 队列为空 或者队尾元素大于当前要入队的元素，元素入队尾
            if (deque.isEmpty() || nums[deque.peekLast()] >= nums[right]) {
                deque.addLast(right);
            } else {
                // 队尾元素小于当前要入队的元素时，循环出队尾，指定队尾元素大于等于当前要入队的元素
                while (!deque.isEmpty() && nums[deque.peekLast()] < nums[right]) {
                    deque.pollLast();
                }
                // 将当前要入队的元素入队尾
                deque.addLast(right);
            }
            // 当窗口形成时 (例如 k = 3, right [0, 1, 2], right = 2 时，窗口形成)
            if (right >= k - 1) {
                // 判断单调队列队头的元素下标是否 >= left, 也就是在窗口内，不是则循环出队头
                while (!deque.isEmpty() && deque.peekFirst() < left) {
                    deque.pollFirst();
                }
                // 队头的元素就是当前窗口的最大值，加入到结果集中
                res[left] = nums[deque.peekFirst()];
                // 窗口左边界移动， right++ 时，窗口右边界移动
                left++;
            }
        }
        return res;
    }

    /**
     解法三: 优先队列：大顶堆
     注意：leetCode 不能通过
     */
    public static int[] maxSlidingWindow3(int[] nums, int k) {
        int len = nums.length;
        int res[] = new int[len - k + 1];
        int left = 0;
        // 大顶堆，堆顶是最大值
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2)-> o2 - o1);
        for (int right = 0; right < len; right++) {
            queue.add(nums[right]);
            if (right >= k - 1) {
                if (left > 0) {
                    queue.remove(nums[left - 1]);
                }
                res[left] = queue.peek();
                left++;
            }
        }
        return res;
    }



    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int[] res = maxSlidingWindow3(nums, 3);
        Arrays.stream(res).forEach(System.out::print);
    }
    















}
