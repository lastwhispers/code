package cn.lastwhisper.leetcode.linkedlist.两两交换链表中的节点_24_中等;

import cn.lastwhisper.leetcode.linkedlist.ListNode;

import static cn.lastwhisper.leetcode.linkedlist.LinkedListUtil.createListNode;
import static cn.lastwhisper.leetcode.linkedlist.LinkedListUtil.printListNode;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/swap-nodes-in-pairs/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public ListNode swapPairs(ListNode head) {
        ListNode lastTail = head;
        ListNode rHead = head.next;

        ListNode current = head;
        ListNode next  ;
        ListNode afterNext  ;

        while (current != null) {
            // 后移
            next = current.next;
            afterNext = current.next.next;

            // 反转
            next.next = current;
            if(afterNext!=null){
                lastTail.next = afterNext.next;
            }
            lastTail = afterNext;

            current = afterNext;
        }

        return rHead;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        printListNode(new Solution1().swapPairs(createListNode(arr)));
    }
}