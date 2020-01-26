package cn.lastwhisper.leetcode.stackqueue.合并K个排序链表_23_困难;

import cn.lastwhisper.leetcode.common.linkedlist.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.createListNode;
import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.printListNode;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/merge-k-sorted-lists
     * -------------------------------------------------------------------
     * 思考：输入的链表都是有序的
     * -------------------------------------------------------------------
     * 思路：
     *  1.将链表头节点都放入最小堆中
     *  2.取出最小堆顶节点minNode，将虚拟头节点next指向minNode
     *  3.如果minNode的next不为空，取出minNode的next放入最小堆中
     *  4.重复1、2、3步骤，直至最小堆中没有节点，返回虚拟头节点的next即可
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }

        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        Queue<ListNode> minHeap = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                // 升序
                return o1.val - o2.val;
            }
        });
        // 链表头部放入最小堆中
        for (ListNode listNode : lists) {
            if (listNode == null) {
                continue;
            }
            minHeap.add(listNode);
        }

        while (!minHeap.isEmpty()) {
            ListNode minNode = minHeap.poll();
            current.next = minNode;
            current = current.next;
            if (minNode.next != null) {
                minHeap.add(minNode.next);
            }
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode[] listNodes = new ListNode[3];
        ListNode node1 = createListNode(1, 4, 5);
        ListNode node2 = createListNode(1, 3, 4);
        ListNode node3 = createListNode(2, 6);
        listNodes[0] = node1;
        listNodes[1] = node2;
        listNodes[2] = node3;

        printListNode(new Solution1().mergeKLists(listNodes));
    }
}