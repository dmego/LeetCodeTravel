package cn.dmego.leetcode.solution.lc101_lc150;

import cn.dmego.util.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 排序链表
 * @author dmego
 * @date 2022/01/19 09:49
 */
public class Solution_148 {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        List<Integer> list = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            list.add(temp.val);
            temp = temp.next;
        }
        Collections.sort(list);
        temp = head;
        for (Integer key : list) {
            temp.val = key;
            temp = temp.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.getByArray(new int[]{4, 19, 14, 5, -3, 1, 8, 5, 11, 15});
        Solution_148 s = new Solution_148();
        ListNode node = s.sortList(head);
        System.out.println(node);
    }

}
