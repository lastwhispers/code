package cn.cunchang.回文链表_234_简单;

import cn.cunchang.linkedlist.LinkedListUtil;
import cn.cunchang.linkedlist.ListNode;

class Solution {public boolean isPalindrome(ListNode head) {
    if (head == null || head.next == null) {
        return true;
    }
    ListNode fast = head, slow = head;
    while (fast.next != null && fast.next.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    ListNode list1 = reverse(slow);
    ListNode list2 = head;
    while (list1 != null && list2 != null) {
        if (list1.val != list2.val) {
            return false;
        }
        list1 = list1.next;
        list2 = list2.next;
    }

    return true;
}

    public ListNode reverse(ListNode head) {
        ListNode pre = null;//无法找到前一个节点，所以需要记录前一个节点
        ListNode current = head;// 当前节点
        while (current != null) {
            ListNode next = current.next;// next节点，防止current反转后找不到next了
            current.next = pre;
            pre = current;
            current = next;
        }
        return pre;
    }


    public static void main(String[] args) {
//        ListNode listNode = LinkedListUtil.createListNode(1, 2, 2, 1);
//        ListNode listNode = LinkedListUtil.createListNode(1, 2, 3, 2, 1);
        ListNode listNode = LinkedListUtil.createListNode(1, 1);
        LinkedListUtil.printListNode(listNode);
        System.out.println(new Solution().isPalindrome(listNode));
    }
}