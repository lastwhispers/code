package cn.lastwhisper.leetcode.linkedlist.删除排序链表中的重复元素_83_简单;

import cn.lastwhisper.leetcode.common.linkedlist.ListNode;

import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.createListNode;
import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.printListNode;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
     * -------------------------------------------------------------------
     * 思考：
     * 数据特征：
     *     输入：链表、**升序**、所有整数
     * -------------------------------------------------------------------
     * 思路：链表数据升序，这个条件很重要，可以使用两个指针，current指向当前节点、next指向下一个节点
     *  判断当前节点和下一个节点是否相同，相同删除，不相同同时后移
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * -------------------------------------------------------------------
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode current = head;
        ListNode next = head.next;
        while (next != null) {
            if (next.val == current.val) {
                // 出现重复删除节点，并后移next节点
                current.next = next.next;
            } else {
                // 无重复同时后移
                current = next;
            }
            next = next.next;
        }
        return head;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1};
        //int[] arr = {1, 1, 2, 4};
        //int[] arr = {1, 2, 3, 3, 4};

        printListNode(new Solution2().deleteDuplicates(createListNode(arr)));
    }
}