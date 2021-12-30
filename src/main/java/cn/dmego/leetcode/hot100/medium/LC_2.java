package cn.dmego.leetcode.hot100.medium;

import cn.dmego.util.ListNode;

/**
 * 两数相加
 * @author dmego
 * @date 2021/12/30 14:04
 */
public class LC_2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode root = dummy;
        // 进位
        int carry = 0;
        while (l1 != null || l2 != null) {
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            root.next = new ListNode(sum % 10);
            root = root.next;
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        // 注意最后要判断 carry 的值
        if (carry != 0) {
            root.next = new ListNode(carry);
        }
        return dummy.next;
    }
}
