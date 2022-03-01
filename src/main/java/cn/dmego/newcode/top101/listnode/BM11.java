package cn.dmego.newcode.top101.listnode;

import cn.dmego.util.ListNode;

/**
 * 链表相加（2）
 * @author dmego
 * @date 2022/03/01 08:53
 */
public class BM11 {

    public ListNode addInList (ListNode head1, ListNode head2) {
        // 先反转两个链表，让低位在左，高位在右，方便相加
        head1 = reverse(head1);
        head2 = reverse(head2);
        // 进位标记
        int flag = 0;
        // 定义结果链表的哑节点
        ListNode dummy = new ListNode(-1);
        ListNode root = dummy;
        // 循环遍历两个链表
        while (head1 != null || head2 != null) {
            int val = flag;
            if (head1 != null) {
                val += head1.val;
                head1 = head1.next;
            }
            if (head2 != null) {
                val += head2.val;
                head2 = head2.next;
            }
            // 相加之后 新节点的值是两个链表(当前节点值 + 进位) % 10 的余数
            root.next = new ListNode(val % 10);
            // 更新进位，两个链表(当前节点值 + 进位) / 10 的结果取整
            flag = val / 10;
            root = root.next;
        }
        // 遍历到最后如果进位不为 0，需要把进位加上
        if (flag != 0) {
            root.next = new ListNode(flag);
        }
        // 最后结果是结果链表的反转(高位在左，低位在右)
        return reverse(dummy.next);
    }

    // 反转链表
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode root = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return root;
    }

}
