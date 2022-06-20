package cn.cunchang.linkedlist;


/**
 * 操作链表工具类
 *
 * @author lastwhisper
 * @date 1/7/2020
 */
public class LinkedListUtil {

    /**
     * 创建单链表
     */
    public static ListNode createListNode(int... arr) {
        if (arr == null || arr.length == 0) {
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

    /**
     * 打印单链表
     */
    public static void printListNode(String msg, ListNode head) {
        System.out.println(msg + appendVal(head));
    }

    /**
     * 打印单链表
     */
    public static void printListNode(ListNode head) {
        System.out.println(appendVal(head));
    }

    private static String appendVal(ListNode head) {
        StringBuilder sb = new StringBuilder();
        ListNode current = head;
        while (current != null) {
            sb.append(current.val);
            sb.append("->");
            current = current.next;
        }
        sb.append("NULL");
        return sb.toString();
    }


    public static void main(String[] args) {
        printListNode(createListNode(1, 2, 3, 4, 5, 6, 7));
    }
}
