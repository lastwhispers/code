package cn.lastwhisper.leetcode.common.linkedlist.删除排序链表中的重复元素_83_简单;

import cn.lastwhisper.leetcode.common.linkedlist.ListNode;

import java.util.HashSet;
import java.util.Set;

import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.createListNode;
import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.printListNode;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
     * -------------------------------------------------------------------
     * 思考：
     * 数据特征：
     *     输入：链表、**升序**、所有整数
     * -------------------------------------------------------------------
     * 思路：遍历链表并使用Hash表判断是否重复，对重复节点使用链表删除法
     *  prev指针移动规则：
     *    出现重复时，删除重复节点，不后移
     *    不重复时，向后移一位即可(current永远是prev的后一位)
     *  current指针移动规则：
     *     每步都要后移一位
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * -------------------------------------------------------------------
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;

        Set<Integer> set = new HashSet<>();
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            if (set.contains(current.val)) {
                // 出现重复值时删除current节点，current节点没有指向即为删除
                prev.next = current.next;
            } else {
                set.add(current.val);
                // 无重复，prev指针后移
                prev = current;
            }
            current = current.next;
        }
        return head;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1};
        //int[] arr = {1, 1, 2, 4};

        printListNode(new Solution1().deleteDuplicates(createListNode(arr)));
    }
}