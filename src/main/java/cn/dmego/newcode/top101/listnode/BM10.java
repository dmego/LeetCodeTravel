package cn.dmego.newcode.top101.listnode;

import cn.dmego.util.ListNode;

/**
 * 两个链表的第一个公共节点
 * @author dmego
 * @date 2022/03/01 08:52
 */
public class BM10 {


    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode head1 = pHead1, head2 = pHead2;
        while (head1 != null || head2 != null) {
            // 如果两个节点相等，返回
            if (head1 == head2) return head1;
            if (head1 == null) head1 = pHead2;
            // 如果 head1 遍历结束，将其接到 pHead2 上
            else head1 = head1.next;
            if (head2 == null) head2 = pHead1;
            // 如果 head2 遍历结束，将其接到 pHead1 上
            else head2 = head2.next;
        }
        return null;
    }

}
