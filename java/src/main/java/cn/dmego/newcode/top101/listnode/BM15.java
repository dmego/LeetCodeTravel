package cn.dmego.newcode.top101.listnode;

import cn.dmego.util.ListNode;

/**
 * 奇偶链表
 * @author dmego
 * @date 2022/03/02 09:24
 */
public class BM15 {


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
