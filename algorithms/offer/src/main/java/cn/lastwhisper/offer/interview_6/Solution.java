package cn.lastwhisper.offer.interview_6;

import java.util.ArrayList;

public class Solution {
    private ArrayList list = new ArrayList();

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (listNode != null) {
            this.printListFromTailToHead(listNode.next);
            list.add(listNode.val);
        }
        return list;
    }
}