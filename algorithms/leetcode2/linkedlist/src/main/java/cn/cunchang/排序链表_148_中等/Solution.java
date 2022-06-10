package cn.cunchang.排序链表_148_中等;

import cn.cunchang.linkedlist.LinkedListUtil;
import cn.cunchang.linkedlist.ListNode;

class Solution {
    public ListNode sortList(ListNode head) {
        // 递归终止点
        if (head == null || head.next == null) {
            return head;
        }
        // 1. 快慢指针法，遍历链表找到中间节点
        ListNode fast = middleBeforeNode(head);
        // 2. 中间节点切断链表
        ListNode mid = fast.next;//直接用fast当中间节点也可以，但是为了切断链表，用next比较方便
        fast.next = null;
        // 3. 分别用归并排序排左右子链表
        ListNode leftNode = sortList(head);
        ListNode rightNode = sortList(mid);
        // 4. 合并子链表
        return mergeTwoLists(leftNode, rightNode);
    }

    public static ListNode middleBeforeNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

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
        if (list1 != null) {
            mergeNode.next = list1;
        }
        if (list2 != null) {
            mergeNode.next = list2;
        }
        return mergeList.next;
    }

    public static void main(String[] args) {
        LinkedListUtil.printListNode(new Solution().
                sortList(LinkedListUtil.createListNode(9, 8, 7, 6, 5, 4, 3, 2, 1)));
    }

}