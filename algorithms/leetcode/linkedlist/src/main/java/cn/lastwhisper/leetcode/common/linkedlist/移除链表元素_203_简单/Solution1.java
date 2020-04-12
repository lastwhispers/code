package cn.lastwhisper.leetcode.common.linkedlist.移除链表元素_203_简单;

import cn.lastwhisper.leetcode.common.linkedlist.ListNode;

import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.createListNode;
import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.printListNode;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/remove-linked-list-elements/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：设置虚拟头节点
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return null;

        //虚拟头节点
        ListNode vNode = new ListNode(-1);
        vNode.next = head;

        // 前节点
        ListNode prev = vNode;
        // 当前节点
        ListNode current = head;

        while (current != null) {
            if (current.val == val) {
                prev.next = current.next;
            } else {
                prev = prev.next;
            }
            current = current.next;
        }

        return vNode.next;
    }

    public static void main(String[] args) {
        // example
        int[] arr = {1, 2, 6, 3, 4, 5, 6};
        int x = 6;

        // error example
        //int[] arr = {1};
        //int x = 1;

        //int[] arr = {1};
        //int x = 2;

        //int[] arr = {1, 1};
        //int x = 1;

        printListNode(new Solution1().removeElements(createListNode(arr), x));
    }
}