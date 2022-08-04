package cn.dmego.leetcode.solution;

import cn.dmego.util.ListNode;

/**
 * 删除链表的的重复元素
 * @author dmego
 * @date 2022/01/18 21:28
 */
public class Solution_82 {


    /**
     迭代解法：
     定义一个哑节点 dummy ，因为有可能会删除头结点，或者什么将节点都删除完，最后结果返回 dummy.next
     定义一个前置节点 prev = dummy
     遍历链表：
        1. 当 head != head.next 时, head 和 prev 都往前移动一位
        2. 当 head == head.next 时，说明 head 和 head.next 是重复元素，都需要被删除
        为了找到更多的重复元素，我们继续遍历链表，直到找到一个节点(move).val != head.val 为止
        我们通过 prev.next = move 来删除 prev ~ move 中间重复的元素
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1, head);
        ListNode prev = dummy;
        while (head != null && head.next != null) {
            // 如果 head.val != head.next.val, 说明 head 不是重复元素
            if (head.val != head.next.val) {
                // prev 和 head 节点都往前移动
                prev = prev.next;
                head = head.next;
            } else {
                // 如果 head.val == head.next.val 说明 head 与 head.next 都是重复元素，需要删除
                ListNode move = head.next;
                // 因为链表是排序的，为了找到更多与 head.val 重复元素，继续遍历链表
                while (move != null && head.val == move.val) {
                    move = move.next;
                }
                // 结束循环时，move 是和 head 不相同的节点
                // [head, move) 之间的元素是重复的，prev.next = move 是将重复元素删除
                prev.next = move;
                // 将 head 执行 move, 也就是不重复的元素
                head = move;
            }
        }
        return dummy.next;
    }

    /**
     递归解法:
     递归函数：删除以 head 为头结点的链表中的重复元素
     递归结束条件: head == null || head.next == null, 链表中不包含重复元素，return head;
     如果 head.val != head.next.val, 说明 head 一定不是重复元素， head 需要保留
        head.next 指向剩下的不重复元素，也就是 head.next = deleteDuplicates(head.next);
        返回 head 也就是不重复元素链表的头结点
     如果 head.val == head.next.val，说明 head 与 head.next 是重复元素，
        我们还需要遍历找到更多与 head.val 相同的元素, 直到找到一个与 head.val 不同的元素 move
        最后返回 deleteDuplicates(move), 其结果就是不重复元素的头节点
     */
     public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        // 如果 head.val != head.next.val 说明 head 节点不是重复节点
        // deleteDuplicates(head.next) 递归调用删除以 head.next 为头结点的链表中的重复元素
        // head.next = deleteDuplicates(head.next) 将不重复的链表节点连起来
        if (head.val != head.next.val) {
            head.next = deleteDuplicates(head.next);
            // 返回不重复的链表头结点
            return head;
        } else {
            // 如果 head.val == head.next.val， 则需要想迭代那样，把重复的元素一次性都找出来
            ListNode move = head.next;
            while (move != null && head.val == move.val) {
                move = move.next;
            }
            // 此时 move 节点是和 head 节点值不相同的元素，head 节点需要被删除，所有直接返回 deleteDuplicates(move) 就行
            return deleteDuplicates(move);
        }
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node41 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node41;
        node41.next = node5;
        Solution_82 s = new Solution_82();
        s.deleteDuplicates(head);
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
    }
}
