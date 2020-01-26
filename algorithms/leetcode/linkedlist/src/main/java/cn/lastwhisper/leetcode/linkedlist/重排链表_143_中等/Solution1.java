package cn.lastwhisper.leetcode.linkedlist.重排链表_143_中等;

import cn.lastwhisper.leetcode.common.linkedlist.ListNode;

import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.createListNode;
import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.printListNode;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/reorder-list/
     * -------------------------------------------------------------------
     * 思考：
     *  奇数与偶数链表情况不同，奇数链表会成环
     * -------------------------------------------------------------------
     * 思路：找到链表中间节点，然后对剩余链表进行reverse，将两个链表合并即可
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public void reorderList(ListNode head) {
        // 过滤空值
        if (head == null) {
            return;
        }
        // 1、找到中间节点的前一个节点（找前一个节点是为了切断两个链表的联系）
        ListNode middleBeforeNode = middleBeforeNode(head);
        // 2、反转以中间节点为首之后的链表
        ListNode secondHead = reverseListNode(middleBeforeNode.next);
        // 切断两个链表的联系，防止链表成环
        middleBeforeNode.next = null;
        // 3、合并链表
        alterNoteMergeOne(head, secondHead);
    }

    public static void alterNoteMergeOne(ListNode firstHead, ListNode secondHead) {
        ListNode firstNext;
        ListNode secondNext;

        while (secondHead != null) {
            // 1.记录两个链表的next节点
            firstNext = firstHead.next;
            secondNext = secondHead.next;
            // 2.将链表1的当前节点指向链表2的当前节点，链表2的当前节点指向链表1的下一个节点
            // 链表1：1->2->3 链表2：5->4
            // 合并后的链表1：1->5->2
            firstHead.next = secondHead;
            secondHead.next = firstNext;
            // 3.将之前保存的next更新为当前节点，继续循环
            firstHead = firstNext;
            secondHead = secondNext;
            // 防止奇数节点链表成环 https://leetcode-cn.com/problems/reorder-list/
            if (secondHead == null) {
                firstHead.next = null;
            }
        }
    }

    /**
     * 找到链表中间的前一个节点
     */
    public static ListNode middleBeforeNode(ListNode head) {
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
    public static ListNode reverseListNode(ListNode head) {
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
        // 偶数没问题
        int[] arr = {1, 2, 3, 4, 5, 6};
        // 奇数有问题
        //int[] arr = {1, 2, 3, 4, 5};
        ListNode head = createListNode(arr);
        printListNode("重排前：\t", head);
        new Solution1().reorderList(head);
        printListNode("重排后：\t", head);
    }
}