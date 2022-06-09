package cn.cunchang.两两交接链表中的节点_24_中等;

import cn.cunchang.linkedlist.LinkedListUtil;
import cn.cunchang.linkedlist.ListNode;

class Solution {

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode holder = head.next;
        ListNode pre = null;
        ListNode headTemp = head;
        while (headTemp != null && headTemp.next != null) {
            ListNode next = headTemp.next.next;
            // 2的next执行1
            // 4的next执行3
            headTemp.next.next = headTemp;
            if (pre != null) {
                // 4的next执行3时，1指向4，而不是1指向3
                pre.next = headTemp.next;
            }
            // 当前节点next指向下下个节点；1指向3
            headTemp.next = next;
            pre = headTemp;
            headTemp = next;
        }
        return holder;
    }

//    public ListNode swapPairs(ListNode head) {
//        ListNode holder = new ListNode(-1);
//        holder.next = head;
//        ListNode virtual = holder;
//        while (virtual.next != null && virtual.next.next != null) {
//            ListNode start = virtual.next;
//            ListNode end = virtual.next.next;
//            // 虚拟节点的next指向2
//            virtual.next = end;
//            // 1的next指向2的next，就是3，1.next=3
//            start.next = end.next;
//            // 2的next指向1，2.next = 1
//            end.next = start;
//            // 目前就是 -1,2,1,3,4，start=1
//            virtual = start;
//        }
//        return holder.next;
//    }

    public static void main(String[] args) {
        LinkedListUtil.printListNode(new Solution().swapPairs(LinkedListUtil.createListNode(1, 2, 3, 4)));
        LinkedListUtil.printListNode(new Solution().swapPairs(LinkedListUtil.createListNode(1, 2, 3, 4, 5, 6)));
        LinkedListUtil.printListNode(new Solution().swapPairs(LinkedListUtil.createListNode(1, 2, 3, 4, 5)));
        LinkedListUtil.printListNode(new Solution().swapPairs(LinkedListUtil.createListNode(1)));
        LinkedListUtil.printListNode(new Solution().swapPairs(LinkedListUtil.createListNode()));
    }
}