package cn.lastwhisper.leetcode.linkedlist.环形链表_141_简单;

import cn.lastwhisper.leetcode.common.linkedlist.ListNode;

import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.createLoopListNode;

public class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/linked-list-cycle/submissions/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：快慢指针(龟兔赛跑)
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public boolean hasCycle(ListNode head) {
        if (head == null||head.next == null) {
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }

        return false;
    }


    public static void main(String[] args) {
        int pos = 1;
        int[] arr = {3, 2, 0, -4};
        ListNode loopListNode = createLoopListNode(pos, arr);
        System.out.println(new Solution1().hasCycle(loopListNode));
    }
}