package cn.lastwhisper.leetcode.linkedlist.移除链表元素_203_简单;

import cn.lastwhisper.leetcode.common.linkedlist.ListNode;

import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.createListNode;
import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.printListNode;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/remove-linked-list-elements/
     * -------------------------------------------------------------------
     * 思考：不设置虚拟头节点
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return null;
        // 前节点
        ListNode prev = null;
        // 当前节点
        ListNode current = head;
        while (current != null) {
            if (current.val == val) {
                // 找到目标值之后分为两种情况
                if (prev != null) {
                    prev.next = current.next;
                } else {
                    head = current.next;
                }
            } else {
                prev = current;
            }
            current = current.next;
        }

        return head;
    }

    public static void main(String[] args) {
        // example
        //int[] arr = {1, 2, 6, 3, 4, 5, 6};
        //int x = 6;

        // error example
        //int[] arr = {1};
        //int x = 1;

        //int[] arr = {1};
        //int x = 2;

        int[] arr = {1, 1};
        int x = 1;

        printListNode(new Solution2().removeElements(createListNode(arr), x));
    }
}