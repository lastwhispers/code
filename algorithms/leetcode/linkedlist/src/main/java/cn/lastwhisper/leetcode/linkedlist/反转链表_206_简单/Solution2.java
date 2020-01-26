package cn.lastwhisper.leetcode.linkedlist.反转链表_206_简单;

import cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil;
import cn.lastwhisper.leetcode.common.linkedlist.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/reverse-linked-list/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：recursive
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n) - 注意，递归是占用空间的，占用空间的大小和递归深度成正比
     * -------------------------------------------------------------------
     * 执行用时 :1 ms, 在所有 Java 提交中击败了7.79%的用户
     * 内存消耗 :37.4 MB, 在所有 Java 提交中击败了22.92%的用户
     */
    public ListNode reverseList(ListNode head) {
        // 递归终止条件
        if (head == null || head.next == null)
            return head;

        //与head关联
        ListNode rhead = reverseList(head.next);

        // 每次反转链表末尾的两个节点
        // head->next此刻指向head后面的链表的尾节点
        // head->next->next = head把head节点放在了尾部
        head.next.next = head;
        head.next = null;

        return rhead;
    }

    public static void main(String[] args) {
        LinkedListUtil.printListNode(new Solution2().reverseList(LinkedListUtil.createListNode(new int[]{1, 2, 3,})));
    }
}