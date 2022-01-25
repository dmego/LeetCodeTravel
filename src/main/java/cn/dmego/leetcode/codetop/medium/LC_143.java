package cn.dmego.leetcode.codetop.medium;

import cn.dmego.util.ListNode;

/**
 * @author dmego
 * @date 2022/01/12 10:05
 */
public class LC_143 {

    // 找到中间节点，反转，然后合并
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode middle = findMiddle(head);
        ListNode head2 = reverse(middle);
        merge(head, head2);
    }
    // 找中间节点
    public ListNode findMiddle(ListNode head) {
        // 快慢指针
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            // tmp 记录 slow 的上一个节点
            ListNode tmp = slow.next;
            // 当 fast 到达链表结尾时
            if (fast == null || fast.next == null) {
                // 截断链表
                slow.next = null;
            }
            slow = tmp;
        }
        return slow;
    }

    // 反转
    public ListNode reverse(ListNode head) {
        ListNode tmp = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = tmp;
            tmp = head;
            head = next;
        }
        return tmp;
    }

    // 合并
    public void merge(ListNode head1, ListNode head2) {
        ListNode next;
        while (head1 != null) {
            next = head1.next;
            head1.next = head2;
            head1 = next;
            if (head1 != null) {
                next = head2.next;
                head2.next = head1;
                head2 = next;
            }
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        LC_143 lc = new LC_143();
        lc.reorderList(head);
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }

    }

}
