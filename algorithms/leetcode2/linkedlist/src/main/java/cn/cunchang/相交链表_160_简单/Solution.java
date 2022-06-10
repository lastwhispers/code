package cn.cunchang.相交链表_160_简单;

import cn.cunchang.linkedlist.ListNode;

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 计算ab的长度
        int lenA = 0, lenB = 0;
        ListNode lenHeadA = headA;
        ListNode lenHeadB = headB;
        while (lenHeadA != null) {
            lenA++;
            lenHeadA = lenHeadA.next;
        }
        while (lenHeadB != null) {
            lenB++;
            lenHeadB = lenHeadB.next;
        }
        // 将ab头设置到相同位置
        int diff = Math.abs(lenA - lenB);
        ListNode startHeadA = headA;
        ListNode startHeadB = headB;
        if (lenA > lenB) {
            while (diff > 0) {
                startHeadA = startHeadA.next;
                diff--;
            }
        } else {
            while (diff > 0) {
                startHeadB = startHeadB.next;
                diff--;
            }
        }
        // 比较，有相同说明相交
        while (startHeadA != null && startHeadB != null) {
            if (startHeadA == startHeadB) {
                return startHeadA;
            }
            startHeadA = startHeadA.next;
            startHeadB = startHeadB.next;
        }
        return null;
    }
}