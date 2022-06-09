package cn.cunchang.反转链表_206_简单;

import cn.cunchang.linkedlist.LinkedListUtil;
import cn.cunchang.linkedlist.ListNode;

import java.util.Stack;

class Solution {

    // 递归
//    public ListNode reverseList(ListNode head) {
//        if (head == null || head.next == null) {
//            return head;
//        }
//        // newHead其实就是最后一个节点，唯一的作用就是让函数调用方拿到这个反转后的单链表的头，原先的head节点现在是尾结点了
//        // 通过递归遍历链表，在遍历每一个节点的时候，让当前节点的下一个节点next指向当前节点，当前节点next指向null
//        // 由于栈先进后出，所以其实是从后往前遍历的，所以不需要像正向遍历的时候那样，记录前指针、next指针
//        ListNode newHead = reverseList(head.next);
//        head.next.next = head;
//        head.next = null;
//        return newHead;
//    }

    // 栈
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode newHead;

        Stack<ListNode> stack = new Stack<>();
        // head=>1->2->3->4->5
        // stack=4<-3<-2<-1
        while (head.next != null) {
            stack.push(head);
            head = head.next;
        }
        // 握住尾结点，将来它是首节点；等价递归里面的newHead，也可以不要直接返回head
        newHead = head;
        // stack=4<-3<-2<-1
        while (!stack.isEmpty()) {
            // 把4弹出来，4->5；依次把321都弹出来
            ListNode current = stack.pop();
            // 5->4
            current.next.next = current;
            // 4->null
            current.next = null;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode listNode = LinkedListUtil.createListNode(1, 2, 3, 4, 5);
        LinkedListUtil.printListNode(listNode);
        LinkedListUtil.printListNode(new Solution().reverseList(listNode));
    }

}
