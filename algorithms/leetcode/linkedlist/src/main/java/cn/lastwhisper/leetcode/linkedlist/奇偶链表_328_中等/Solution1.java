package cn.lastwhisper.leetcode.linkedlist.奇偶链表_328_中等;

import cn.lastwhisper.leetcode.linkedlist.ListNode;

import static cn.lastwhisper.leetcode.linkedlist.LinkedListUtil.createListNode;
import static cn.lastwhisper.leetcode.linkedlist.LinkedListUtil.printListNode;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/odd-even-linked-list/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：建立两个奇偶虚拟头结点，遍历输入链表，将满足条件的结点，
     *  挂载到奇偶结点上，最后将奇偶链表拼接
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode oddNode = new ListNode(-1);
        ListNode evenNode = new ListNode(0);
        ListNode currentOdd = oddNode;
        ListNode currentEven = evenNode;

        int index = 1;

        while (head != null) {
            if (index % 2 == 0) { //将index改成head.val，就是按值进行奇偶链表
                currentEven.next = head;
                currentEven = currentEven.next;
                /*
                 * 如果最后一个结点是偶数结点，将当前奇数结点的next置为null，
                 * 因为奇数结点的next可能指向偶数结点，但是head遍历完了，这个next无法更新，
                 * 会出现环形链表
                 */
                if (head.next == null) {
                    currentOdd.next = null;
                }
            } else {
                currentOdd.next = head;
                currentOdd = currentOdd.next;
                if (head.next == null) {
                    currentEven.next = null;
                }
            }
            head = head.next;
            index++;
        }
        currentOdd.next = evenNode.next;

        return oddNode.next;
    }

    public static void main(String[] args) {
        printListNode(new Solution1().oddEvenList(createListNode(1, 2, 3, 4, 5)));
    }
}