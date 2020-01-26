package cn.lastwhisper.leetcode.linkedlist.删除链表中的节点_237_简单;

import cn.lastwhisper.leetcode.common.linkedlist.ListNode;

import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.printListNode;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
     * -------------------------------------------------------------------
     * 思考：删除节点的两种思路
     * -------------------------------------------------------------------
     * 思路：将下一个节点值给当前节点，删除下一个节点
     * -------------------------------------------------------------------
     * 时间复杂度：O(1)
     * 空间复杂度：O(1)
     */
    public void deleteNode(ListNode node) {
        ListNode next = node.next;
        node.val = next.val;
        node.next = next.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        ListNode listNode1 = new ListNode(5);
        ListNode listNode2 = new ListNode(1);
        ListNode listNode3 = new ListNode(9);
        head.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;

        printListNode("删除前：\t", head);
        new Solution1().deleteNode(listNode1);
        printListNode("删除后：\t", head);

    }
}