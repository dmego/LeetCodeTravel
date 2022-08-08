package cn.dmego.leetcode.solution;

import cn.dmego.util.ListNode;

/**
 * 对链表进行插入排序
 */
public class Solution_147 {

    public ListNode insertionSortList(ListNode head) {
        if (head.next == null) return head;
        ListNode dummy = new ListNode(-5001);
        dummy.next = head;

        ListNode curr = head.next; // 待排序链表的头结点
        head.next = null;

        while (curr != null) {
            ListNode node = curr;
            curr = curr.next;
            node.next = null;
            ListNode pre = dummy;
            boolean flag = false;
            while (pre.next != null) {
                if (node.val < pre.next.val) {
                    ListNode next = pre.next;
                    pre.next = node;
                    node.next = next;
                    flag = true;
                    break;
                } else {
                    pre = pre.next;
                }
            }
            if (!flag) {
                pre.next = node;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.getByArray(new int[]{-1,5,3,4,0});
        System.out.println(head);
        Solution_147 s = new Solution_147();
        ListNode res = s.insertionSortList(head);
        System.out.println(res.toString());
    }

}
