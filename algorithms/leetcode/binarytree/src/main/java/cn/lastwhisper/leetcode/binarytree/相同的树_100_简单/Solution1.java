package cn.lastwhisper.leetcode.binarytree.相同的树_100_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import static cn.lastwhisper.leetcode.common.tree.TreeUtils.*;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/same-tree/
     * -------------------------------------------------------------------
     * 思考：
     *  Q:递归结束条件？
     *   1.两个节点都为null，返回true
     *   2.两个节点有一个不为null，返回false
     *   3.两个节点值不同，返回false
     * -------------------------------------------------------------------
     * 思路：先序遍历
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 递归结束条件
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        // 递归体
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        System.out.println(new Solution1().isSameTree(createTree(), createTree()));
    }
}