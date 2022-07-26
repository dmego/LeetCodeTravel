package cn.dmego.leetcode.solution;

import cn.dmego.util.ListNode;
/**
 * 找到链表中环的入口节点
 * 假设从头节点到入口点为 a, 环的长度为 b, fast 节点走过的距离为 f, slow 节点走过的距离为 s, 则有：
 *  f = 2s, 因为 fast 速度是 slow 的两倍
 *  f = s + nb, fast 节点一定比 slow 多的距离一定是环的整数倍 ---> s = nb
 * 我们要想从头节点走到入口节点，需要走 a + kb 长度，因为走到 a 长度就到了入口点，再走一圈还是在原来地方。
 * 现在 s 已经走了 nb 长度了，所以只需要再走 a 长度就能到达入口点。
 *  让 head 从头节点出发，slow 继续前进，当 head 与 slow 相遇时，相遇点就是入口点。因为两个节点走的距离相同
 */
public class Solution_142 {

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == null || fast.next == null) return null;
            // fast 和 slow 相遇之后
            if (fast == slow) {
                while (head != slow) {
                    head = head.next;
                    slow = slow.next;
                }
                return head;
            }
        }
        return null;
    }
}