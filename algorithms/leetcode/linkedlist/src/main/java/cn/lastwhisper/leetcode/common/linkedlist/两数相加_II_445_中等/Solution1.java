package cn.lastwhisper.leetcode.common.linkedlist.两数相加_II_445_中等;

import cn.lastwhisper.leetcode.common.linkedlist.ListNode;

import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.createListNode;
import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.printListNode;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/add-two-numbers-ii/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：字符串相加
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        StringBuilder numStr1 = new StringBuilder();
        StringBuilder numStr2 = new StringBuilder();
        ListNode current1 = l1, current2 = l2;
        while (current1 != null) {
            numStr1.append(current1.val);
            current1 = current1.next;
        }
        while (current2 != null) {
            numStr2.append(current2.val);
            current2 = current2.next;
        }

        String sum = add(numStr1.toString(), numStr2.toString());

        return createReverseListNode(sum);
    }

    // 字符串相加
    public String add(String a, String b) {
        StringBuilder sb = new StringBuilder();
        // 进位
        int carry = 0;
        int aIndex = a.length() - 1, bIndex = b.length() - 1;

        while (aIndex >= 0 || bIndex >= 0) {
            int x, y, sum;
            x = aIndex >= 0 ? a.charAt(aIndex) - '0' : 0;
            y = bIndex >= 0 ? b.charAt(bIndex) - '0' : 0;

            sum = carry + x + y;
            carry = sum / 10;
            sb.append(sum % 10);

            aIndex--;
            bIndex--;
        }

        if (carry > 0) {
            sb.append(carry);
        }
        return sb.toString();
    }

    // 按字符串创建反序链表
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
        int[] num1 = {7, 2, 4, 3};
        int[] num2 = {5, 6, 4};
        printListNode(new Solution1().addTwoNumbers(createListNode(num1), createListNode(num2)));
    }
}