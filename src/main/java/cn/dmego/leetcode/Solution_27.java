package cn.dmego.leetcode;

/**
 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。

 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。

 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

 */
public class Solution_27 {

    /**
      解法一：
        这道题和 26 题 其实是差不多的， 26 题说的是删除数组中重复的元素，使每个元素只出现一次
        在这道题看来，重复的元素变成了固定值，且需要删除数组中所有等于该值 val 的元素

        我们同样可以定义两个指针：
            slow 指针指向下一个待加入新数组的元素，开始时 slow = 0。所以新数组的长度就是 slow
            fast 指针用来遍历数组
            当 nums[fast] != val 时, 我们把 nums[fast] 加入到新数组中，也就是 nums[slow] = nums[fast], slow++, fast++
            当 nums[fast] == val 时，说明是重复元素，继续 fast ++ 即可。
            因为新数组最后一个元素是 nums[slow-1], 新数组长度就是 slow
     */
    public static int removeElement(int[] nums, int val) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int fast = 0, slow = 0;
        while (fast < len) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    /**

     解法二：题目说 元素的顺序是可以改变的，并且最后我们只要返回正确的数组长度即可
     所以我们可以每次把每次判断重复的元素和数组的后面的元素进行交换
     也是使用 双指针
        head 指针 指向数组第一个元素，end 指针指向数组最后一个元素
        遍历数组
            当 nums[head] == val 时，说明 nums[head] 是需要删除的元素,此时让 nums[head] == nums[end], end --
            注意这里不让 head++ 是因为 nums[end] 赋值给 nums[head] 后，还需要判断 nums[head] == val
            如果 nums[head] != val, head++ 即可
        循环遍历结束的条件就是 head == end
        因为新数组最后一个元素是nums[head-1] head-1是下标，所以最后新数组的长度就是 head

     */
    public static int removeElement2(int[] nums, int val) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int head = 0, end = len - 1;
        while (head <= end) {
            if (nums[head] == val) {
                nums[head] = nums[end];
                end--;
            } else {
                head++;
            }
        }
        return head;
    }
}
