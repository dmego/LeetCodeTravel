package cn.dmego.leetcode.solution;

import cn.dmego.util.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 *
 *
 * L0 → L1 → … → Ln - 1 → Ln
 *
 *
 * 请将其重新排列后变为：
 *
 *
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 *
 * @author dmego
 * @date 2022/01/12 10:05
 */
public class Solution_143 {

    /**
     * 先存到list中，然后使用双指针，一个一个重新排列
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        List<ListNode> nodeList = new ArrayList<>();
        ListNode root = head;
        while (root != null) {
            nodeList.add(root);
            root = root.next;
        }
        int i = 0, j = nodeList.size() - 1;
        while (i <= j) {
            if (i == j) break;
            nodeList.get(i).next = nodeList.get(j);
            nodeList.get(j).next = null;
            i++;
            if (i == j) break;
            nodeList.get(j).next = nodeList.get(i);
            nodeList.get(i).next = null;
            j--;
        }
    }

    /**
     先找到链表中间节点，将链表分为两半
     将后一半链表反转
     将两段链表合并(先取前一半链表的节点，再取后一半链表的节点)
     */
    public void reorderList2(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode middleNode = middleNode(head);
        ListNode head2 = reverse(middleNode);
        merge(head, head2);
    }


    // 找到链表的中间节点，并切断
    public ListNode middleNode(ListNode head) {
        // 找到链表的中间节点， 快慢指针
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            ListNode tmp = slow.next;
            // 当fast 到达链表结尾时，tmp 的位置就是中间节点
            if (fast == null || fast.next == null) {
                // 将 链表与 tmp 切断，也就是切断 tmp 的 prev 节点
                slow.next = null;
            }
            slow = tmp;
        }
        return slow;
    }


    // 合并两个链表
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

    // 反转链表
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode node = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return node;
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
        Solution_143 s = new Solution_143();
        s.reorderList2(head);
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }

    }


}
