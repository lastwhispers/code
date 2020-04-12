package cn.lastwhisper.leetcode.common.linkedlist.分隔链表_86_中等;

import cn.lastwhisper.leetcode.common.linkedlist.ListNode;

import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.createListNode;
import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.printListNode;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/partition-list/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public ListNode partition(ListNode head, int x) {

        if (head == null) {
            return null;
        }

        ListNode greaterNode = new ListNode(-1);
        ListNode lessNode = new ListNode(0);
        ListNode currentGreater = greaterNode;
        ListNode currentLess = lessNode;

        while (head != null) {
            if (head.val < x) {
                currentLess.next = head;
                currentLess = currentLess.next;
                // 防止出现环形链表
                if (head.next == null) {
                    currentGreater.next = null;
                }
            } else {
                currentGreater.next = head;
                currentGreater = currentGreater.next;
                if (head.next == null) {
                    currentLess.next = null;
                }
            }
            head = head.next;
        }
        currentLess.next = greaterNode.next;

        return lessNode.next;
    }

    public static void main(String[] args) {
        int[] arr = {1,4,3,2,5,2};
        int x = 3;

        printListNode(new Solution1().partition(createListNode(arr), x));
    }
}