package cn.lastwhisper.leetcode.linkedlist.删除排序链表中的重复元素_II_82_中等;

import cn.lastwhisper.leetcode.linkedlist.ListNode;

import static cn.lastwhisper.leetcode.linkedlist.LinkedListUtil.createListNode;
import static cn.lastwhisper.leetcode.linkedlist.LinkedListUtil.printListNode;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
     * -------------------------------------------------------------------
     * 思考：
     * 数据特征：
     *     输入：链表、**升序**、所有整数
     * -------------------------------------------------------------------
     * 思路：分为三种情况
     *      （1）1,2,2,3
     *      （2）1,1,1,1
     *      （3）1,1,2,3
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev = null;
        ListNode current = head;
        ListNode next = head.next;

        while (next != null) {
            if (current.val == next.val) {
                // 删除第2个重复结点
                current.next = next.next;
                next = next.next;
                // 删除第2+个重复结点
                while (next != null && current.val == next.val) {
                    current.next = next.next;
                    next = next.next;
                }
                // prev!=null说明[m..n]出现重复
                if (prev != null) {
                    // 删除第1个重复结点
                    prev.next = current.next;
                } else {
                    // prev==null说明从[1..m]都是重复的，[m..n]还不好说
                    head = current.next;
                }
            } else {
                // 无重复时prev后移
                prev = current;
            }
            // 1,1,1这种情况，next=null
            if (next == null) {
                break;
            }
            // 不管是否重复current、next都必须后移
            current = next;
            next = next.next;
        }
        return head;
    }

    public static void main(String[] args) {

        //int[] arr = {1, 2, 3, 3, 4, 4, 5};
        //int[] arr = {1, 1, 1};
        int[] arr = {1, 1, 1, 2, 3};

        printListNode(new Solution1().deleteDuplicates(createListNode(arr)));
    }
}