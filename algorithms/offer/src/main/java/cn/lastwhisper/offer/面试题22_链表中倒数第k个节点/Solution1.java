package cn.lastwhisper.offer.面试题22_链表中倒数第k个节点;

import cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil;
import cn.lastwhisper.leetcode.common.linkedlist.ListNode;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：双指针、快慢指针
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public ListNode getKthFromEnd(ListNode head, int k) {

        ListNode fastNode = head;
        ListNode slowNode = head;
        while (k > 0) {
            fastNode = fastNode.next;
            k--;
        }

        while (fastNode != null) {
            fastNode = fastNode.next;
            slowNode = slowNode.next;
        }

        return slowNode;
    }

    public static void main(String[] args) {
        ListNode listNode = LinkedListUtil.createListNode(1, 2, 3, 4, 5);
        LinkedListUtil.printListNode(new Solution1().getKthFromEnd(listNode, 2));
    }
}