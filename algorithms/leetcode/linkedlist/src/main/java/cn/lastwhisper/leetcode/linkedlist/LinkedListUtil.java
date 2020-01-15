package cn.lastwhisper.leetcode.linkedlist;


import java.util.ArrayList;
import java.util.List;

/**
 * 操作链表工具类
 * @author lastwhisper
 * @date 1/7/2020
 */
public class LinkedListUtil {

    /**
     * 创建单链表
     */
    public static ListNode createListNode(int... arr) {
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

    /**
     * 创建环形链表
     *  应用题目：https://leetcode-cn.com/problems/linked-list-cycle/
     * @param pos 成环位置
     * @param arr 链表元素
     */
    public static ListNode createLoopListNode(int pos, int... arr) {
        if (arr == null) {
            return null;
        }
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        // 锚点，记录成环的位置
        ListNode anchor = null;
        if (pos == 0) {
            anchor = head;
        }
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            // 记录成环位置
            if (pos == i) {
                if (pos == arr.length - 1) {
                    anchor = current;
                } else {
                    anchor = current.next;
                }
            }
            current = current.next;
        }
        current.next = anchor;
        return head;
    }

    /**
     * 创建两个相交链表
     *  应用题目：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
     * @param intersectVal 相交点的值
     * @param listA 链表A元素数组
     * @param listB 链表B元素数组
     * @param skipA 链表A相交前结点数
     * @param skipB 链表B相交前结点数
     */
    public static List<ListNode> createIntersectListNode(
            int intersectVal, int[] listA, int[] listB, int skipA, int skipB) {
        List<ListNode> listNodes = new ArrayList<>(2);

        ListNode listNodeA = new ListNode(listA[0]);
        ListNode currentA = listNodeA;
        ListNode listNodeB = new ListNode(listB[0]);
        ListNode currentB = listNodeB;

        for (int i = 1; i < skipA; i++) {
            currentA.next = new ListNode(listA[i]);
            currentA = currentA.next;
        }

        for (int i = 1; i < skipB; i++) {
            currentB.next = new ListNode(listB[i]);
            currentB = currentB.next;
        }

        ListNode intersectListNode = new ListNode(listA[skipA]);
        ListNode currentIntersect = intersectListNode;

        for (int i = skipA + 1; i < listA.length; i++) {
            currentIntersect.next = new ListNode(listA[i]);
            currentIntersect = currentIntersect.next;
        }

        currentA.next = intersectListNode;
        currentB.next = intersectListNode;

        listNodes.add(listNodeA);
        listNodes.add(listNodeB);

        return listNodes;
    }

    private static ListNode createListNode(String arr) {
        if (arr == null) {
            return null;
        }
        ListNode head = new ListNode(arr.charAt(0) - '0');
        ListNode current = head;

        for (int i = 1; i < arr.length(); i++) {
            current.next = new ListNode(arr.charAt(i) - '0');
            current = current.next;
        }
        return head;
    }

    /**
     * 反序创建链表
     */
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

    /**
     * 两个字符串整数相加
     */
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

    /**
     * 反转链表
     */
    public static ListNode reverseListNode(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        ListNode next = null;

        while (current != null) {
            next = current.next;

            current.next = prev;
            prev = current;
            current = next;
        }
        return current;
    }

    public static void main(String[] args) {
        //printListNode(createListNode(1, 2, 3, 4, 5, 6, 7));
        //ListNode headLoop = createLoopListNode(0, 4, 5, 6);
        //ListNode centerLoop = createLoopListNode(1, 4, 5, 6);

        int intersectVal = 8, skipA = 2, skipB = 3;
        int[] listA = {4, 1, 8, 4, 5};
        int[] listB = {5, 0, 1, 8, 4, 5};
        List<ListNode> intersectListNode = createIntersectListNode(intersectVal, listA, listB, skipA, skipB);
        for (ListNode listNode : intersectListNode) {
            printListNode(listNode);
        }

    }
}
