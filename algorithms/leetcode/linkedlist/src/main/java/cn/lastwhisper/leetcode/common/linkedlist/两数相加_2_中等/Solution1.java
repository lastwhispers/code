package cn.lastwhisper.leetcode.common.linkedlist.两数相加_2_中等;

import cn.lastwhisper.leetcode.common.linkedlist.ListNode;


import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.createListNode;
import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.printListNode;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/add-two-numbers/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：将链表转成正常的数字后，相加，然后反转；
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        StringBuilder numStr1 = new StringBuilder();
        StringBuilder numStr2 = new StringBuilder();

        while (l1 != null) {
            numStr1.append(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            numStr2.append(l2.val);
            l2 = l2.next;
        }
        java.math.BigInteger num1 = new java.math.BigInteger(numStr1.reverse().toString());
        java.math.BigInteger num2 = new java.math.BigInteger(numStr2.reverse().toString());

        String sum = String.valueOf(num1.add(num2));

        return createReverseListNode(sum);
    }

    private static ListNode createReverseListNode(String arr) {
        if (arr == null) {
            return null;
        }
        ListNode head = new ListNode(arr.charAt(arr.length() - 1) - '0');
        ListNode current = head;

        for (int i = arr.length() - 2; i >= 0; i--) {
            current.next = new ListNode(arr.charAt(i) - '0');
            current = current.next;
        }
        return head;
    }


    public static void main(String[] args) {
        //int[] l1 = {1, 8};
        //int[] l2 = {0};

        int[] l1 = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
        int[] l2 = {5, 6, 4};
        printListNode(new Solution1().addTwoNumbers(createListNode(l1), createListNode(l2)));
    }

}