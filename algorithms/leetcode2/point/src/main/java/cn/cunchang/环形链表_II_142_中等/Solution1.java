package cn.cunchang.环形链表_II_142_中等;

public class Solution1 {
    /**
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * <p>
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public ListNode detectCycle(ListNode head) {
        // 先找到相遇点
        ListNode fast = head, slow = head;
        do {
            // 说明不成环
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        } while (fast != slow);// 说明成环
        // 再找成环点
        ListNode p1 = head, p2 = slow;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}