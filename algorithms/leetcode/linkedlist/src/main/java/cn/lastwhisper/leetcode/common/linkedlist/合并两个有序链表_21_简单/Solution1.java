package cn.lastwhisper.leetcode.common.linkedlist.合并两个有序链表_21_简单;

import cn.lastwhisper.leetcode.common.linkedlist.ListNode;

import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.createListNode;
import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.printListNode;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/merge-two-sorted-lists/submissions/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：设置一个虚拟头节点vNode，边遍历两个链表边比较节点大小，
     * vNode指向小的那个节点，遍历完后，返回vNode.next
     * -------------------------------------------------------------------
     * 时间复杂度：O(n+m)，每次迭代都会添加一个链表节点，循环次数为两个链表总长度
     * 空间复杂度：O(1)
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        // 虚拟头节点，这个点不能动
        ListNode vNode = new ListNode(-1);

        // currentv负责穿针引线，l1、l2负责遍历
        ListNode currentv = vNode;

        // 将l1、l2按照升序链接到vNode上
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                currentv.next = l1;
                l1 = l1.next;
            } else {
                currentv.next = l2;
                l2 = l2.next;
            }
            currentv = currentv.next;
        }
        // l1、l2某个不为空，直接链接上
        currentv.next = l1 == null ? l2 : l1;

        return vNode.next;
    }

    public static void main(String[] args) {
        ListNode head1 = createListNode(1, 2, 4);
        ListNode head2 = createListNode(1, 3, 4, 5);
        printListNode(head1);
        printListNode(head2);
        printListNode(new Solution1().mergeTwoLists(head1, head2));
    }
}