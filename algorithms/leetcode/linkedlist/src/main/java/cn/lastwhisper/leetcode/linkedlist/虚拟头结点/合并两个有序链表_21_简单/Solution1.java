package cn.lastwhisper.leetcode.linkedlist.虚拟头结点.合并两个有序链表_21_简单;

import cn.lastwhisper.leetcode.linkedlist.ListNode;

import static cn.lastwhisper.leetcode.linkedlist.LinkedListUtil.createListNode;
import static cn.lastwhisper.leetcode.linkedlist.LinkedListUtil.printListNode;

class Solution1 {
    /**
     * 题目地址：
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        return l1;
    }

    public static void main(String[] args) {
        ListNode head1 = createListNode(1, 2, 4);
        ListNode head2 = createListNode(1, 3, 4);
        printListNode(head1);
        printListNode(head2);
        printListNode(new Solution1().mergeTwoLists(head1, head2));
    }
}