package cn.lastwhisper.leetcode.binarytree.对称二叉树_101_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import java.util.LinkedList;

import static cn.lastwhisper.leetcode.common.tree.TreeUtil.createTree;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/symmetric-tree/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：先序遍历-迭代
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public boolean isSymmetric(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode p = stack.pop();
            TreeNode q = stack.pop();

            if (p == null && q == null) continue;
            if (p == null || q == null) return false;
            if (p.val != q.val) return false;

            stack.push(p.left);
            stack.push(q.right);
            stack.push(q.left);
            stack.push(p.right);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().isSymmetric(createTree(1, 2, 2, 3, 4, 4, 3))); // true
        System.out.println(new Solution2().isSymmetric(createTree(1, 2, 2, null, 3, null, 3))); //false
        System.out.println(new Solution2().isSymmetric(createTree(2, 3, 3, 4, 5, 5))); //false
    }
}