package cn.cunchang.合并两个有序链表_21_中等;

import cn.cunchang.linkedlist.LinkedListUtil;
import cn.cunchang.linkedlist.ListNode;

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode mergeList = new ListNode(-1);
        ListNode mergeNode = mergeList;
        while (list1 != null && list2 != null) {
            if (list1.val >= list2.val) {
                mergeNode.next = list2;
                list2 = list2.next;
            } else {
                mergeNode.next = list1;
                list1 = list1.next;
            }
            mergeNode = mergeNode.next;
        }
        while (list1 != null){
            mergeNode.next = list1;
            mergeNode = mergeNode.next;
            list1 = list1.next;
        }
        while (list2 != null){
            mergeNode.next = list2;
            mergeNode = mergeNode.next;
            list2 = list2.next;
        }
        return mergeList.next;
    }

    public static void main(String[] args) {
        ListNode list1 = LinkedListUtil.createListNode(1, 2, 4);
        ListNode list2 = LinkedListUtil.createListNode(1,  3, 4);
        LinkedListUtil.printListNode(new Solution().mergeTwoLists(list1,list2));
    }
}