package cn.lastwhisper.offer.interview_6;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 面试题6：从头到尾打印链表
 * @author cn.lastwhisper
 */
public class Solution_6 {

    // 牛客提交版本
    private ArrayList list = new ArrayList();

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;

        Solution_6 solution_6 = new Solution_6();

        solution_6.PrintListReversingly_Iteratively(listNode1);
        System.out.println();
        solution_6.PrintListReversingly_Recursion(listNode1);
    }

    /**
     * 栈
     */
    public void PrintListReversingly_Iteratively(ListNode pHead) {
        Stack<ListNode> stack = new Stack<>();
        while (pHead != null) {
            stack.push(pHead);
            pHead = pHead.next;
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop().val + "\t");
        }
    }
    /**
     * 递归
     */
    public void PrintListReversingly_Recursion(ListNode pHead) {
        if (pHead != null) {
            this.PrintListReversingly_Recursion(pHead.next);
            System.out.print(pHead.val + "\t");
        }
    }

}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}


