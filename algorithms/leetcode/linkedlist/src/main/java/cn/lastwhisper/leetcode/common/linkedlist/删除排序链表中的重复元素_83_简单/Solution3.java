package cn.lastwhisper.leetcode.common.linkedlist.删除排序链表中的重复元素_83_简单;

import cn.lastwhisper.leetcode.common.linkedlist.ListNode;

import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.createListNode;
import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.printListNode;

class Solution3 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
     * -------------------------------------------------------------------
     * 思考：
     * 数据特征：
     *     输入：链表、**升序**、所有整数
     * -------------------------------------------------------------------
     * 思路：将思路2的遍历改成递归
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * -------------------------------------------------------------------
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode next = head.next;
        /*
         * 外层循环可以根据数据规则添加
         *  比如{1,2,3,4,4,4,5,6,7,8,9,9,9,9}，有规则重复位置
         *  此时可以用if探测是否进入规则重复位置
         */
        //if (next.val == head.val) {
        //    next = next.next;
        while (next != null && next.val == head.val)
            next = next.next;
        //}
        head.next = deleteDuplicates(next);
        return head;
    }

    public static void main(String[] args) {
        //int[] arr = {1, 1, 1};
        //int[] arr = {1, 1, 2, 4};
        int[] arr = {1, 2, 3, 3, 4};

        printListNode(new Solution3().deleteDuplicates(createListNode(arr)));
    }
}