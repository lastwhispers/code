package cn.lastwhisper.leetcode.binarytree.平衡二叉树_110_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import static cn.lastwhisper.leetcode.common.tree.TreeUtil.createTree;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/balanced-binary-tree/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public boolean isBalanced(TreeNode root) {
        return getTreeDepth(root) != -1;
    }

    private int getTreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = getTreeDepth(root.left);
        if (leftDepth == -1) {
            return -1;
        }
        int rightDepth = getTreeDepth(root.right);
        if (rightDepth == -1) {
            return -1;
        }
        if (Math.abs(leftDepth - rightDepth) > 1) {
            return -1;
        }
        return Math.max(leftDepth, rightDepth) + 1;
    }


    public static void main(String[] args) {
        /*
         *     1
         *      \
         *      2
         *     /
         *    3
         */
        //System.out.println(new Solution2().isBalanced(createTree(1, null, 2, null, 3)));//false
        /*
         *     3
         *    / \
         *   9  20
         *     /  \
         *    15   7
         */
        //System.out.println(new Solution2().isBalanced(createTree(3, 9, 20, null, null, 15, 7)));//true
        /*
         *        1
         *       / \
         *      2   2
         *     /     \
         *    3       3
         *   /         \
         *  4           4
         */
        //System.out.println(new Solution2().isBalanced(createTree(1, 2, 2, 3, null, null, 3, 4, null, null, 4)));//false
        /*
         *        1
         *       / \
         *      2   2
         *     / \
         *    3   3
         *   / \
         *  4   4
         */
        System.out.println(new Solution2().isBalanced(createTree(1, 2, 2, 3, 3, null, null, 4, 4)));//false
    }
}
