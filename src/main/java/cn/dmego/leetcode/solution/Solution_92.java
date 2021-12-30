package cn.dmego.leetcode.solution;

import cn.dmego.leetcode.util.ListNode;

/**
 * 反转链表2
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right  。请你反转从位置 left 到位置 right 的链表节点，返回
 * 反转后的链表 。
 */
public class Solution_92 {

    /**
     解法一：反转中间部分，然后将头与尾接回到反转的链表上
     我们需要记录 left 的前一个节点 preLeft; right 的后一个节点 rightNext
     当 left~right 区间的链表反转完成后(反转后，right 是头节点，left 是尾节点)
     需要把 preLeft.next = right; left.next = rightNext
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 如果 left 就是头节点，可能还需要单独拿出来判断，这里使用一个虚拟节点链接到头节点，最后返回 dummy.next
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;
        int i = 0;
        // 第 1 步，从 dummy 走到 left - 1 也就是 left 节点前一个位置
        /*
           先执行 pre = pre.next 然后 i++, 当 i = left - 1 时，说明 pre 先更新了，此时 pre = leftNode
           要想获取 preLeft 节点，i = left - 2, 所以条件是 i < left - 1
         */
        for (; i < left - 1; i++) {
            pre = pre.next;
        }

        // left 节点的前置节点 (此时 pre 就是 leftNode 的前置节点)
        ListNode preLeft = pre;

        // 第 2 步，再走到 right 节点位置
        /*
           因为 pre 此时指向 left 的前一个节点, i 也必须从 left 的前一位开始 i = left - 1
           最后 pre 要在 rightNode 节点上， 那么 i 应该是 right - 1 的值时，pre.next = rightNode
           i 最后 = right
         */
        for (i = left - 1; i < right; i++) {
            pre = pre.next;
        }

        // 第 3 步，切断 left~right 这段链表，然后反转
        ListNode leftNode = preLeft.next; // left 节点
        preLeft.next = null; // 切断 left 节点的前面
        ListNode rightNext = pre.next; // right节点的后置节点
        pre.next = null; // 切断 right 节点的后面

        ListNode rightNode = reverseNode(leftNode); // rightNode 其实就是 pre

        // 第 4 步，重新拼接链表
        preLeft.next = rightNode;
        leftNode.next = rightNext;

        return dummy.next;
    }

    public static ListNode reverseNode(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = pre;
            pre = head;
            head = tmp;
        }
        return pre;
    }

    /**
    解法二：头插法(头就是 left 位置的节点)
     遍历链表，把 (left, right] 区间的节点一步一步移动到 left 节点位置
         1->2->3->4->5->null
         1->3->2->4->5->null
         1->4->3->2->5->null

     定义关键的 3 个节点：
     pre: left 节点的前一个节点（left 移动后，pre 不动）
     curr:  left 节点，在头插过程中，left 节点会移动
     next:  left 节点的下一个节点，随着 left 节点的移动，next 节点也会跟着变化

     头插的步骤：
     ListNode next = cur.next // 首先保存 curr(left) 节点的 next 节点

     1. curr.next = next.next
     2. next.next = pre.next // 这里 next.next 要指向 pre.next， pre.next 就是当前的头
     3. pre.next = next //  这里就是头插法的体现，next 节点变成当前的头结点

     */
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;
        // 走到 left - 1 的位置，pre 指向 left 的前一个节点
        int i = 0;
        for (; i < left - 1; i++) {
            pre = pre.next;
        }

        ListNode curr = pre.next; // curr 就是 LeftNode
        ListNode next;

        // 从 left 节点开始，把 (left, right] 区间的节点一个一个移动到 left 节点位置
        for (i = left; i < right; i++) {
            next = curr.next;
            curr.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummy.next;
    }


    public static ListNode reverseBetween3(ListNode head, int left, int right) {
        // left - 1; right + 1
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode curr = head;
        ListNode leftPre = dummy, rightNext = dummy;
        ListNode leftH = curr, rigthH = curr;
        ListNode prev = dummy;
        int i = 1;
        while (curr != null) {
            if (i < left) {
                prev = prev.next;
            } else if (i == left) {
                leftPre = prev;
                leftH = curr;
                leftPre.next = null;
            }

            if (i == right) {
                rigthH = curr;
                rightNext = curr.next;
                rigthH.next = null;

                leftPre.next = reverseNode(leftH);
                leftH.next = rightNext;
                break;
            }
            curr = curr.next;
            i++;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode node = reverseBetween3(node1, 2, 4);
        System.out.println(node);

    }


}
