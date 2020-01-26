package cn.lastwhisper.leetcode.linkedlist.删除链表的倒数第N个节点_19_中等;

import cn.lastwhisper.leetcode.common.linkedlist.ListNode;

import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.createListNode;
import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.printListNode;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
     * -------------------------------------------------------------------
     * 思考：特殊节点，头节点、尾节点
     * -------------------------------------------------------------------
     * 思路：虚拟头节点+快慢指针
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 虚拟头节点解决删除头节点问题
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;

        ListNode fast = dummyNode;
        ListNode slow = dummyNode;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast != null && fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;

        return dummyNode.next;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int n = 2;

        //int[] arr = {1};
        //int n =1;

        printListNode(new Solution1().removeNthFromEnd(createListNode(arr), n));
    }
}