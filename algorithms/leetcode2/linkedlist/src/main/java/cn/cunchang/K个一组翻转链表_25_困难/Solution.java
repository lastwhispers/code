package cn.cunchang.K个一组翻转链表_25_困难;

import cn.cunchang.linkedlist.LinkedListUtil;
import cn.cunchang.linkedlist.ListNode;

/**
 * @author cunchang
 * @date 2022/6/20 8:33 PM
 */
public class Solution {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        int step = 0;
        ListNode result = null;  // 返回结果
        ListNode current = head; // 当前起点
        ListNode next = current; // 下一个k起点
        ListNode preTail = null; // 上一次反转后的尾结点
        while (current != null) {
            while (next != null && step < k) {
                next = next.next;
                step++;
            }
            // 剩余链表不够反转，就不反转了
            // next == null不能够代表"剩余链表不够反转"，可能是刚好够反转
            if (step < k && preTail != null) {
                preTail.next = current;
                break;
            }

            step = 0;
            // 反转后返回头结点
            ListNode tail = reverse1(current, k);
            if (preTail != null) {
                preTail.next = tail;
            }
            if (preTail == null) { // 第一次反转的头结点
                result = tail;
            }
            preTail = current;//反转链表的尾结点

            current = next;
        }
        return result;
    }

    // 返回头
    public ListNode reverse1(ListNode head, int k) {
        ListNode pre = null;
        ListNode current = head;
        ListNode next = null;
        while (current != null && k > 0) {
            next = current.next;
            current.next = pre;
            pre = current;
            current = next;
            k--;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new Solution().reverseKGroup(
                LinkedListUtil.createListNode(1,2,3,4), 2);
        LinkedListUtil.printListNode(listNode1);
    }
}
