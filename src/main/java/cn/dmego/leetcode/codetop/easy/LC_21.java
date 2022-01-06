package cn.dmego.leetcode.codetop.easy;

import cn.dmego.util.ListNode;

/**
 * 合并两个有序链表
 * @author dmego
 * @date 2022/01/05 00:03
 */
public class LC_21 {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode root = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                root.next = new ListNode(list1.val);
                list1 = list1.next;
            } else {
                root.next = new ListNode(list2.val);
                list2 = list2.next;
            }
            root = root.next;
        }
        if (list1 != null) {
            root.next = list1;
        }
        if (list2 != null) {
            root.next = list2;
        }
        return dummy.next;
    }
}
