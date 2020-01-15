package cn.lastwhisper.leetcode.linkedlist.回文链表_234_简单;

import cn.lastwhisper.leetcode.linkedlist.ListNode;

import static cn.lastwhisper.leetcode.linkedlist.LinkedListUtil.createListNode;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/palindrome-linked-list/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：使用快慢指针遍历到链表的中间结点，反转头结点到中间结点这部分链表，
     *  从中间结点开始遍历，比较反转链表
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        // 找到链表中间结点
        ListNode slow = endOfFirstHalf(head);
        // 反转链表
        ListNode reverseHead = reverseListNode(slow.next);

        ListNode firstHead = head;

        while (reverseHead != null && firstHead != null) {
            if (reverseHead.val != firstHead.val) {
                return false;
            }
            reverseHead = reverseHead.next;
            firstHead = firstHead.next;
        }

        // 包含单个结点的情况
        return true;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 反转链表
     */
    private static ListNode reverseListNode(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        ListNode next = null;

        while (current != null) {
            next = current.next;

            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        //int[] arr = {1, 2, 3, 2, 1};
        //int[] arr = {1, 2, 2, 1};
        //int[] arr = {1, 2};
        //int[] arr = {1};
        int[] arr = {1, 0, 1};
        System.out.println(new Solution1().isPalindrome(createListNode(arr)));
    }
}