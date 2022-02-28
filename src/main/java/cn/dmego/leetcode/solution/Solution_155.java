package cn.dmego.leetcode.solution;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 最小栈
 * @author dmego
 * @date 2022/02/28 10:07
 */
public class Solution_155 {

    /**
     解法一：使用两个栈实现
     stack 维护正常的栈
     minStack 维护一个每次入栈元素时，当前的最小元素是多少的 栈
     */
    class MinStack {
        Deque<Integer> stack;
        Deque<Integer> minStack;
        public MinStack() {
            //初始化两个栈
            stack = new ArrayDeque<>();
            minStack = new ArrayDeque<>();
            // 最小栈初始化一个最小元素的 int 最大值
            minStack.push(Integer.MAX_VALUE);
        }

        public void push(int val) {
            // 正常栈入栈
            stack.push(val);
            // 最小栈入栈（把当前最小的元素入栈）
            minStack.push(Math.min(minStack.peek(), val));
        }

        public void pop() {
            // 当前栈和最小栈一起出栈
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    /**
     解法二：使用一个栈，但是栈的元素是数组，int[]
     int[0]: 是正常入栈元素
     int[1]: 当前元素入栈时的最小元素
     */
    class MinStack2 {
        Deque<int[]> stack;
        public MinStack2() {
            stack = new ArrayDeque<>();
        }

        public void push(int val) {
            if (stack.peek() != null) {
                stack.push(new int[]{val, Math.min(val, stack.peek()[1])});
            } else {
                stack.push(new int[]{val, val});
            }
        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.peek()[0];
        }

        public int getMin() {
            return stack.peek()[1];
        }
    }


}
