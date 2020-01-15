package cn.lastwhisper.leetcode.linkedlist.两数相加_2_中等;

import cn.lastwhisper.leetcode.linkedlist.ListNode;

import static cn.lastwhisper.leetcode.linkedlist.LinkedListUtil.createListNode;
import static cn.lastwhisper.leetcode.linkedlist.LinkedListUtil.printListNode;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/add-two-numbers/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：整数逆序存储在链表中，正好可以正序相加
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode vHead = new ListNode(0);
        ListNode current = vHead;
        int carry = 0;
        while (l1 != null || l2 != null) {
            // num1、num2表示当前位的值，sum表示当前位的值+上一组进位值
            int num1, num2, sum;
            num1 = l1 != null ? l1.val : 0;
            num2 = l2 != null ? l2.val : 0;

            sum = carry + num1 + num2;
            carry = sum / 10;//进位
            current.next = new ListNode(sum % 10);//当前位的值

            current = current.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry > 0) {
            current.next = new ListNode(carry);
        }
        return vHead.next;
    }

    public static void main(String[] args) {
        //int[] arr1 = {2, 4, 3};
        //int[] arr2 = {5, 6, 4};

        //int[] arr1 = {1, 8};
        //int[] arr2 = {0};

        int[] arr1 = {1, 8};
        int[] arr2 = {1, 9};
        printListNode(new Solution2().addTwoNumbers(createListNode(arr1), createListNode(arr2)));
    }
}