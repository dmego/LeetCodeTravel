package cn.dmego.newcode.top101.listnode;

import cn.dmego.util.ListNode;

/**
 * 合并两个排序的链表
 * @author dmego
 * @date 2022/03/24 14:21
 */
public class BM4 {

    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        ListNode dummy = new ListNode(-1);
        ListNode root = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                root.next = list1;
                list1 = list1.next;
            } else {
                root.next = list2;
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
