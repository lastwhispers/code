package cn.lastwhisper.offer.面试题18_删除链表的节点_简单;

import cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil;
import cn.lastwhisper.leetcode.common.linkedlist.ListNode;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public ListNode deleteNode(ListNode head, int val) {
        ListNode vNode = new ListNode(-1);
        vNode.next = head;

        ListNode prev = vNode;
        while (head != null) {
            if (head.val == val) {
                prev.next = head.next;
                break;
            }
            prev = head;
            head = head.next;
        }

        return vNode.next;
    }

    public static void main(String[] args) {
        int[] head = {4, 5, 1, 9};
        //int val = 4;
        //int val = 1;
        int val = 9;

        ListNode listNode = LinkedListUtil.createListNode(head);
        LinkedListUtil.printListNode(new Solution1().deleteNode(listNode, val));
    }
}