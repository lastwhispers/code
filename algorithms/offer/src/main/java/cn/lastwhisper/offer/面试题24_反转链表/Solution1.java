package cn.lastwhisper.offer.面试题24_反转链表;

import cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil;
import cn.lastwhisper.leetcode.common.linkedlist.ListNode;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：循环反转
     *  三个指针prev、curr、next，注意next要在while里面先记录curr.next
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null, curr = head, next;

        while (curr != null) {
            next = curr.next;

            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode listNode = LinkedListUtil.createListNode(1, 2, 3, 4, 5);
        LinkedListUtil.printListNode(new Solution1().reverseList(listNode));
    }
}