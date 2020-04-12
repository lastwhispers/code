package cn.lastwhisper.leetcode.common.linkedlist.反转链表_206_简单;

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
public class Solution1 {

    /**
     * 题目地址：https://leetcode-cn.com/problems/reverse-linked-list/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：iteratively
     *  需要三个指针prev、current、next帮助反转
     *      prev指向前一个节点
     *      current指向当前正在反转的节点
     *      next指向下一个待反转的节点
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * -------------------------------------------------------------------
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗 :37.3 MB, 在所有 Java 提交中击败了24.92%的用户
     */
    public ListNode reverseList(ListNode head) {

        ListNode prev = null;
        ListNode current = head;
        ListNode next;

        while (current != null) {
            next = current.next;
            // 反转
            current.next = prev;
            // 后移
            prev = current;
            current = next;
        }

        return prev;
    }

    public static void main(String[] args) {
        LinkedListUtil.printListNode(new Solution1().reverseList(
                LinkedListUtil.createListNode(1, 2, 3, 4, 5, 6)));
    }
}