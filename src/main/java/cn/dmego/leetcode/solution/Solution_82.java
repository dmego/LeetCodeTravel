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
            if (head.val != head.next.val) {
                prev = prev.next;
                head = head.next;
            } else {
                ListNode move = head.next;
                while (move != null && head.val == move.val) {
                    move = move.next;
                }
                prev.next = move;
                head = prev.next;
            }
        }
        return dummy.next;
    }

    /**
     递归解法:
     递归函数用来删除以 head 为头结点的链表中的重复元素

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
