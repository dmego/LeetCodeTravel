#!/usr/bin/env python
# -*- coding: UTF-8 -*-
"""
@Date    ：2024/12/31 15:11
@File    ：Solution_1.py
@Author  ：Dmego
"""

from typing import List

class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        if len(nums) < 2: return []
        nums_dict = {}
        for i, num in enumerate(nums):
            other = target - num
            if other in nums_dict:
                return [nums_dict[other], i]
            else:
                nums_dict[num] = i
        return []



def main():
    so = Solution()
    nums = [2,7,11,15]
    target = 9
    result = so.twoSum(nums, target)
    print(result)

if __name__ == '__main__':
    main()