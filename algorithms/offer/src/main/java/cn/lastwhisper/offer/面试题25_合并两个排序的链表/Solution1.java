package cn.lastwhisper.offer.面试题25_合并两个排序的链表;

import cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil;
import cn.lastwhisper.leetcode.common.linkedlist.ListNode;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：合并k个链表？
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode vNode = new ListNode(-1);
        ListNode curr = vNode;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        curr.next = l1 == null ? l2 : l1;

        return vNode.next;
    }

    public static void main(String[] args) {
        ListNode l1 = LinkedListUtil.createListNode(1, 2, 4);
        ListNode l2 = LinkedListUtil.createListNode(1, 3, 4);

        LinkedListUtil.printListNode(new Solution1().mergeTwoLists(l1, l2));

    }
}