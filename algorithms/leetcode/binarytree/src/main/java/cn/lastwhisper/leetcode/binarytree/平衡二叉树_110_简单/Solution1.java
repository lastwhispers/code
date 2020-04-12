package cn.lastwhisper.leetcode.binarytree.平衡二叉树_110_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import static cn.lastwhisper.leetcode.common.tree.TreeUtil.createTree;

class Solution1 {
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
        //它是一棵空树
        if (root == null) {
            return true;
        }
        //它的左右两个子树的高度差的绝对值不超过1
        int leftDepth = getTreeDepth(root.left);
        int rightDepth = getTreeDepth(root.right);
        if (Math.abs(leftDepth - rightDepth) > 1) {
            return false;
        }
        //左右两个子树都是一棵平衡二叉树
        return isBalanced(root.left) && isBalanced(root.right);

    }

    private int getTreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = getTreeDepth(root.left);
        int rightDepth = getTreeDepth(root.right);
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
        //System.out.println(new Solution1().isBalanced(createTree(1, null, 2, null, 3)));//false
        /*
         *     3
         *    / \
         *   9  20
         *     /  \
         *    15   7
         */
        //System.out.println(new Solution1().isBalanced(createTree(3, 9, 20, null, null, 15, 7)));//true
        /*
         *        1
         *       / \
         *      2   2
         *     /     \
         *    3       3
         *   /         \
         *  4           4
         */
        //System.out.println(new Solution1().isBalanced(createTree(1, 2, 2, 3, null, null, 3, 4, null, null, 4)));//false
        /*
         *        1
         *       / \
         *      2   2
         *     / \
         *    3   3
         *   / \
         *  4   4
         */
        System.out.println(new Solution1().isBalanced(createTree(1, 2, 2, 3, 3, null, null, 4, 4)));//false
    }
}
