package cn.lastwhisper.leetcode.common.linkedlist.设计跳表_1206_困难;

public class Skiplist {
    /**
     * 题目地址：https://leetcode-cn.com/problems/design-skiplist/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public Skiplist() {

    }

    public boolean search(int target) {

        return false;
    }

    public void add(int num) {

    }

    public boolean erase(int num) {

        return false;
    }

    /**
     * Your Skiplist object will be instantiated and called as such:
     * Skiplist obj = new Skiplist();
     * boolean param_1 = obj.search(target);
     * obj.add(num);
     * boolean param_3 = obj.erase(num);
     */
    public static void main(String[] args) {
        Skiplist skiplist = new Skiplist();

        skiplist.add(1);
        skiplist.add(2);
        skiplist.add(3);
        System.out.println(skiplist.search(0));// 返回 false
        skiplist.add(4);
        System.out.println(skiplist.search(1));   // 返回 true
        System.out.println(skiplist.erase(0));    // 返回 false，0 不在跳表中
        System.out.println(skiplist.erase(1));    // 返回 true
        System.out.println(skiplist.search(1));   // 返回 false，1 已被擦除
    }
}

