package cn.dmego.leetcode.solution;

import cn.dmego.util.ListNode;

/**
 * 奇偶链表
 * @author dmego
 * @date 2022/03/02 10:13
 */
public class Solution_328 {

    /**
     当链表没有节点，或者只有一个或两个节点，无需重排，直接返回
     将链表分为两个部分，一个是由奇数下标组成的奇链表；另一个是由偶数下标组成的偶链表
     链表的头结点 head 是奇链表的头结点, 也是最后结果链表的头结点；head.next 是偶链表的头结点
     定义两个链表的头节点，oddHead = head, evenHead = head.next;
     定义两个指针 odd = head, even = evenHead;
     循环：even != null && even.next != null
     odd.next 指向 even.next(偶下标的下一个是奇下标)
     odd = odd.next;
     even.next 指向 odd.next(奇下标的下一个是偶下标)
     even = even.next
     循环结束后，奇链表的最后一个节点指向偶链表头指针，两个链表相连
     */
    public ListNode oddEvenList(ListNode head) {
        // 没有节点，只有一个或两个节点，无需重排
        if (head == null || head.next == null || head.next.next == null) return head;
        ListNode oddHead = head, evenHead = head.next;
        ListNode odd = oddHead, even = evenHead;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        // 奇链表的最后一个节点指向偶链表头指针，两个链表相连
        odd.next = evenHead;
        return oddHead;
    }
}
