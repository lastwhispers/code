package cn.lastwhisper.leetcode.binarytree.二叉树的直径_543_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;
import cn.lastwhisper.leetcode.common.tree.TreeUtils;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/diameter-of-binary-tree/
     * -------------------------------------------------------------------
     * 思考：
     *
     * -------------------------------------------------------------------
     * 思路：
     *      1.普通情况，找左子树和右子树最大高度和
     *      2.特殊情况，不一定经过根节点
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int diameterOfBinaryTree(TreeNode root) {
        int max = 0;
        if (root != null) {
            max = Math.max(max, sumHeight(root));
            max = Math.max(max, diameterOfBinaryTree(root.left));
            max = Math.max(max, diameterOfBinaryTree(root.right));
        }
        return max;
    }

    // node结点左右子树最大高度和
    private int sumHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) + height(node.right);
    }

    // node结点最大高度
    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public static void main(String[] args) {
        Integer[] nums = new Integer[]{4, -7, -3, null, null, -9, -3, 9, -7, -4, null, 6, null, -6, -6, null, null, 0, 6, 5, null, 9, null, null, -1, -4, null, null, null, -2};
        TreeNode tree = TreeUtils.createTree(nums);
        System.out.println(new Solution1().diameterOfBinaryTree(tree));
    }
}