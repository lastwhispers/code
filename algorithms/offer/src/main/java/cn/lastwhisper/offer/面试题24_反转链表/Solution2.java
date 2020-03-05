package cn.lastwhisper.offer.面试题24_反转链表;

import cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil;
import cn.lastwhisper.leetcode.common.linkedlist.ListNode;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：递归反转
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public ListNode reverseList(ListNode head) {
        // 找到链表的最后一个结点
        if (head == null || head.next == null)
            return head;

        ListNode rhead = reverseList(head.next);

        // 每次反转链表末尾的两个节点
        // head->next此刻指向head后面的链表的尾节点
        // head->next->next = head把head节点放在了尾部
        head.next.next = head;
        head.next = null;

        return rhead;
    }

    public static void main(String[] args) {
        ListNode listNode = LinkedListUtil.createListNode(1, 2, 3, 4, 5);
        LinkedListUtil.printListNode(new Solution2().reverseList(listNode));
    }
}