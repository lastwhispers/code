package cn.lastwhisper.offer.面试题06_从尾到头打印链表_简单;


import cn.lastwhisper.leetcode.common.linkedlist.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static cn.lastwhisper.leetcode.common.linkedlist.LinkedListUtil.createListNode;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
     * -------------------------------------------------------------------
     * 思考：
     *  栈和递归的联系
     * -------------------------------------------------------------------
     * 思路：递归
     *  一、栈(能用栈就要思考能不能用递归)
     *  二、递归(能用递归就要思考能不能用栈模拟递归)
     *  三、反序链表，然后输出
     *  四、链表正序遍历，数组倒着插入数据
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int[] reversePrint(ListNode head) {
        List<Integer> result = new ArrayList<>();
        recursion(head, result);
        int[] nums = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            nums[i] = result.get(i);
        }
        return nums;
    }

    private void recursion(ListNode head, List<Integer> result) {
        if (head != null) {
            recursion(head.next, result);
            result.add(head.val);
        }
    }

    public static void main(String[] args) {
        System.err.println(Arrays.toString(new Solution1().reversePrint(createListNode(1, 3, 2))));
    }
}