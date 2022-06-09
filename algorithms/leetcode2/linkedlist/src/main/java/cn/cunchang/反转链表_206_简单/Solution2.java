package cn.cunchang.反转链表_206_简单;


import cn.cunchang.linkedlist.ListNode;

class Solution2 {

    public ListNode reverseList(ListNode head) {
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

}
