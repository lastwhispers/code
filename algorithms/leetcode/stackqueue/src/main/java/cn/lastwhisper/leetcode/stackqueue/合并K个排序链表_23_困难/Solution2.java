package cn.lastwhisper.leetcode.stackqueue.合并K个排序链表_23_困难;


import cn.lastwhisper.leetcode.common.linkedlist.ListNode;

import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.createListNode;
import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.printListNode;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/merge-k-sorted-lists
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：将n个链表以中间为对称，合并
     *  1.合并数组中第k个链表到第1个链表，合并数组中第k-1个链表到第2个链表，依次这样操作
     *  2.一轮合并之后，带合并链表分布在数组的 第1 到 第(k+1)/2个链表中，继续1这样的合并直到新链表只在数组第一个位置
     *  3.返回数组第一个元素，即合并之后的链表
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        int length = lists.length;
        // 将n个链表以中间为对称，合并
        while (length > 1) {
            for (int i = 0; i < length / 2; i++) {
                lists[i] = mergeLists(lists[i], lists[length - i - 1]);
            }
            // 三个链表，合并两次
            length = (length + 1) / 2;
        }

        return lists[0];
    }

    /**
     * 合并两个有序链表
     */
    public ListNode mergeLists(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(0);
        ListNode current = dummyNode;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        current.next = l1 == null ? l2 : l1;
        return dummyNode.next;
    }


    public static void main(String[] args) {
        ListNode[] listNodes = new ListNode[3];
        ListNode node1 = createListNode(1, 4, 5);
        ListNode node2 = createListNode(1, 3, 4);
        ListNode node3 = createListNode(2, 6);
        listNodes[0] = node1;
        listNodes[1] = node2;
        listNodes[2] = node3;

        printListNode(new Solution2().mergeKLists(listNodes));
    }
}