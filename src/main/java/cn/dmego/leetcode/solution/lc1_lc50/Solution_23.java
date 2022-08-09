package cn.dmego.leetcode.solution.lc1_lc50;

import cn.dmego.util.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并 K 个升序链表
 * @author dmego
 * @date 2022/02/25 09:20
 */
public class Solution_23 {

    // 合并两个升序链表
    public ListNode mergeTwoList(ListNode root1, ListNode root2) {
        if (root1 == null || root2 == null) {
            return root1 == null ? root2 : root1;
        }
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy, head1 = root1, head2 = root2;
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                head.next = head1;
                head1 = head1.next;
            } else {
                head.next = head2;
                head2 = head2.next;
            }
            head = head.next;
        }
        head.next = head1 != null ? head1 : head2;
        return dummy.next;
    }

    /**
    解法一：暴力解法，按顺序两两合并
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        ListNode result = mergeTwoList(lists[0], lists[1]);
        for (int i = 2; i < lists.length; i++) {
            result = mergeTwoList(lists[i], result);
        }
        return result;
    }


    // 合并 [start, end] 区间的链表
    public ListNode merge(ListNode[] lists, int start, int end) {
        // 如果 start = end 说明区间只有一个链表，直接返回
        if (start == end) {
            return lists[start];
        }
        // start > end ，返回 null (当 lists 长度为 0 时，end = -1)
        if (start > end) {
            return null;
        }
        // 找到 [start, end] 区间的中间位置
        int mid = (start + end) >> 1;
        // 先合并 [start, mid] 和 [mid + 1, end] 两个区间
        // 然后再合并两个链表
        // (当 start = 0, end = 1 时，mid = 0，为了不让下标越界，我们必须将区间分成[start, mid],[mid + 1, end])
        return mergeTwoList(merge(lists, start, mid), merge(lists, mid + 1, end));
    }

    /**
       解法二：分治法
       要合并 [0, k] 区间的链表，我们可以先合并 [0, 2/k] [2/k + 1, k] 这两个区间链表，然后再将这两个区间合并
       同理, 要合并 [0, 2/k] 区间的链表，我们可以继续拆成两个区间来进行合并，利用递归最终完成所有区间合并
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    /**
        解法三: 优先队列
          [
            1->4->5,
            1->3->4,
            2->6
          ]
     对于如上的链表数组，如果按照纵向思维来看，我们合并 K 个链表的逻辑是：取每个链表未合并部分的第一个元素，K 个链表就是 K
     个元素，然后从这 K 个元素中找出最小的一个，加到已经合并的链表后面

     我们可以使用优先队列来辅助找 K 个元素中最小的的一个。
     */
    public ListNode mergeKLists3(ListNode[] lists) {
        if (lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        // 定义优先队列，按照 ListNode 的 val 进行比较
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));
        // 初始化，将 lists 所有链表入队列
        for (ListNode node : lists) {
            // 注意 node != null 才能放进去，否则会报错
            if (node != null) {
                queue.add(node);
            }
        }
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        while (!queue.isEmpty()) {
            // 出队一个元素，该元素一定是所有元素中最小的一个
            ListNode node = queue.poll();
            head.next = node;
            head = head.next;
            // 这里也一样
            if (node.next != null) {
                queue.add(node.next);
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode[] lists = new ListNode[]{
                ListNode.getByArray(new int[]{1,4,5}),
                ListNode.getByArray(new int[]{1,3,4}),
                ListNode.getByArray(new int[]{2,6})};
        Solution_23 s = new Solution_23();
        ListNode node = s.mergeKLists3(lists);
        System.out.println(node);
    }

}
