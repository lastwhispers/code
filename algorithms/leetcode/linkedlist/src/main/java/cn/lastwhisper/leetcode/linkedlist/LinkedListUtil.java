package cn.lastwhisper.leetcode.linkedlist;


/**
 * 操作链表工具类
 * @author lastwhisper
 * @date 1/7/2020
 */
public class LinkedListUtil {

    public static ListNode createListNode(int[] arr) {
        if (arr == null) {
            return null;
        }
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;

        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }

    public static void printListNode(ListNode head) {
        StringBuilder sb = new StringBuilder();
        ListNode current = head;
        while (current != null) {
            sb.append(current.val);
            sb.append("->");
            current = current.next;
        }
        sb.append("NULL");
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        printListNode(createListNode(new int[]{1, 2, 3, 4, 5, 6, 7}));
    }
}
