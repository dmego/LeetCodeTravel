#!/usr/bin/env python
# -*- coding: UTF-8 -*-
"""
@Date    ：2024/12/31 15:11
@File    ：Solution_2.py
@Author  ：Dmego
"""
from typing import Optional


# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def addTwoNumbers(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
        nums1 = []
        nums2 = []
        nums3 = []
        while l1 is not None:
            nums1.append(l1.val)
            l1 = l1.next
        while l2 is not None:
            nums2.append(l2.val)
            l2 = l2.next
        i = 0
        j = 0
        t = 0
        while i < len(nums1) or j < len(nums2):
            n1 = 0
            n2 = 0
            if i < len(nums1):
                n1 = nums1[i]
                i += 1
            if j < len(nums2):
                n2 = nums2[j]
                j += 1
            if (n1 + n2 + t) // 10 > 0:
                nums3.append((n1 + n2 + t) % 10)
                t = 1
            else:
                nums3.append(n1 + n2 + t)
                t = 0

        if t == 1:
            nums3.append(t)

        head = ListNode(nums3[0], None)
        cur = head
        for i in range(1, len(nums3)):
            node = ListNode(nums3[i], None)
            cur.next = node
            cur = cur.next

        return head

def main():
    so = Solution()
    l1 = ListNode(2, ListNode(4, ListNode(9, None)))
    l2 = ListNode(5, ListNode(6, ListNode(4, ListNode(9, None))))
    l3 = so.addTwoNumbers(l1, l2)
    while l3 is not None:
        print(l3.val, end=' ')
        l3 = l3.next

    print(942 + 9465)

if __name__ == '__main__':
    main()