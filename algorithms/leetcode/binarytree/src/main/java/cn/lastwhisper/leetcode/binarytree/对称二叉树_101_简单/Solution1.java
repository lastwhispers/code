package cn.lastwhisper.leetcode.binarytree.对称二叉树_101_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import static cn.lastwhisper.leetcode.common.tree.TreeUtils.createTree;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/symmetric-tree/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：先序遍历-递归
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)，因为我们遍历整个输入树一次，所以总的运行时间为 O(n)，其中 nn 是树中节点的总数。
     * 空间复杂度：递归调用的次数受树的高度限制。在最糟糕情况下，树是线性的，其高度为 O(n)。
     *  因此，在最糟糕的情况下，由栈上的递归调用造成的空间复杂度为 O(n)。
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    public boolean isMirror(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isMirror(p.left, q.right) && isMirror(p.right, q.left);
    }


    public static void main(String[] args) {
        System.out.println(new Solution1().isSymmetric(createTree(1, 2, 2, 3, 4, 4, 3))); // true
        System.out.println(new Solution1().isSymmetric(createTree(1, 2, 2, null, 3, null, 3))); //false
        System.out.println(new Solution1().isSymmetric(createTree(2, 3, 3, 4, 5, 5))); //false
    }
}