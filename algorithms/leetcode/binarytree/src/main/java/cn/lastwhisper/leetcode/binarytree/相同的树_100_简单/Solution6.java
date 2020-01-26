package cn.lastwhisper.leetcode.binarytree.相同的树_100_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import java.util.LinkedList;

import static cn.lastwhisper.leetcode.common.tree.TreeUtils.createTree;

class Solution6 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/same-tree/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：层次遍历-迭代
     *  使用1个队列同时遍历两颗树
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */

    public boolean isSameTree(TreeNode p, TreeNode q) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(p);
        queue.add(q);
        while (!queue.isEmpty()) {
            p = queue.poll();
            q = queue.poll();

            if (p == null && q == null) continue;
            if (p == null || q == null) return false;
            if (p.val != q.val) return false;

            queue.add(p.left);
            queue.add(q.left);
            queue.add(p.right);
            queue.add(q.right);
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(new Solution6().isSameTree(createTree(1, 2, 3), createTree(1, 2, 3)));//true
        System.out.println(new Solution6().isSameTree(createTree(1, 2), createTree(1, null, 2)));//false
        System.out.println(new Solution6().isSameTree(createTree(1, 2, 1), createTree(1, 1, 2)));//false
    }
}