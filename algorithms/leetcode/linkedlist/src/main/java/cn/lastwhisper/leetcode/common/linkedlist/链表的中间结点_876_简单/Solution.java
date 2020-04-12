package cn.lastwhisper.leetcode.common.linkedlist.链表的中间节点_876_简单;

import cn.lastwhisper.leetcode.common.linkedlist.ListNode;

class Solution {
    /**
     * 题目地址：https://leetcode-cn.com/problems/middle-of-the-linked-list/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}