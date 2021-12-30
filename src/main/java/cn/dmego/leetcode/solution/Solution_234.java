package cn.dmego.leetcode.solution;

import cn.dmego.leetcode.util.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
    给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 */
public class Solution_234 {


    /**
      解法一：使用 list保存链表元素 + 判断回文串
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) return false;
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int i = 0, j = list.size() - 1;
        while (i <= j) {
            if (!list.get(i).equals(list.get(j))) return false;
            i++;
            j--;
        }
        return true;
    }

    /**
      解法二：反转链表后半部分，然后遍历依次比较，最后复原链表
     时间复杂度O(n), 空间复杂度O(1)
     */
    public boolean isPalindrome2(ListNode head) {
        if (head == null) return false;
        // 找到链表的中间节点
        ListNode halfNode = getHalfNode(head);
        // 反转中间节点之后的链表
        ListNode reverseHead = reverse(halfNode.next);
        // 原链表的 head ~ halfNode 前半部分链表与 反转之后的链表进行 val 值比较
        ListNode first = head, second = reverseHead;
        boolean result = true;
        while (result && second != null) {
            if (first.val != second.val) {
                result = false;
            }
            first = first.next;
            second = second.next;
        }
        // 复原链表
        halfNode.next = reverse(reverseHead);

        return result;
    }


    /**
     找到链表的中间节点
     使用快慢指针，slow 每次走一步，fast 每次走两步
     当链表长度为偶数时，fast = null, 此时 slow 就是中间节点(实际是前一半子链表的尾节点)
     当链表长度为奇数是，fast.next = null, 此时 slow 就是中间节点
     */
    public ListNode getHalfNode(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
