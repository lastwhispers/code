package cn.lastwhisper.leetcode.linkedlist.反转链表_II_92_中等;

import cn.lastwhisper.leetcode.linkedlist.ListNode;

import static cn.lastwhisper.leetcode.linkedlist.LinkedListUtil.createListNode;
import static cn.lastwhisper.leetcode.linkedlist.LinkedListUtil.printListNode;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/reverse-linked-list-ii/
     * -------------------------------------------------------------------
     * 思考：
     *  m大于n怎么办？
     *  m、n为负数怎么办？
     *  m、n超过链表长度怎么办？
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // 参数校验
        if (head==null){
            return null;
        }


        return null;
    }

    public static void main(String[] args) {
        printListNode(new Solution1().reverseBetween(createListNode(new int[]{1, 2, 3, 4, 5}), 2, 4));
    }
}