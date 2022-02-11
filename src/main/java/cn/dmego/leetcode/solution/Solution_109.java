package cn.dmego.leetcode.solution;

import cn.dmego.util.ListNode;
import cn.dmego.util.TreeNode;

/**
 * 有序链表转换为二叉搜索树
 * @author dmego
 * @date 2022/02/11 10:25
 */
public class Solution_109 {

    // 找到链表的中间节点
    public ListNode findMiddle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            ListNode temp = slow.next;
            if (fast == null || fast.next == null) {
                slow.next = null;
            }
            slow = temp;
        }
        return slow;
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        // 当只有一个节点时，不能再继续找中间节点了，直接返回 TreeNode
        if (head.next == null) return new TreeNode(head.val);
        // 找中间节点
        ListNode middle = findMiddle(head);
        TreeNode root = new TreeNode(middle.val);
        // head 是链表前半段头结点
        root.left = sortedListToBST(head);
        // middle 是中间节点， middle.next 是链表后半段的头结点
        root.right = sortedListToBST(middle.next);
        return root;
    }

    public static void main(String[] args) {
        Solution_109 s = new Solution_109();
        ListNode node = ListNode.getByArray(new int[]{-10,-3,0,5,9});
        TreeNode treeNode = s.sortedListToBST(node);
        System.out.println(treeNode);
    }
}
