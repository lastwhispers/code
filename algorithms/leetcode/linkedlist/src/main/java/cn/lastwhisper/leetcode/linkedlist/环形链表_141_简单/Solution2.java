package cn.lastwhisper.leetcode.linkedlist.环形链表_141_简单;

import cn.lastwhisper.leetcode.linkedlist.ListNode;

import java.util.HashSet;

import static cn.lastwhisper.leetcode.linkedlist.LinkedListUtil.createLoopListNode;

public class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/linked-list-cycle/submissions/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：hash表
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        HashSet<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }

        return false;
    }


    public static void main(String[] args) {
        int pos = 1;
        int[] arr = {3, 2, 0, -4};
        ListNode loopListNode = createLoopListNode(pos, arr);
        System.out.println(new Solution2().hasCycle(loopListNode));
    }
}