package cn.lastwhisper.leetcode.binarytree.左叶子之和_404_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import static cn.lastwhisper.leetcode.common.tree.TreeUtils.createTree;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/sum-of-left-leaves/
     * -------------------------------------------------------------------
     * 思考：左叶子之和
     *  注意：叶子之和
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int sumOfLeftLeaves(TreeNode root) {
        return sumOfLeftLeavesHelper(root, false);
    }

    // 先序遍历求所有左叶子节点值之和
    public int sumOfLeftLeavesHelper(TreeNode root, boolean flag) {
        if (root == null) {
            return 0;
        }
        int leave = 0;
        // 左叶子节点
        if (flag && root.left == null && root.right == null) {
            leave = root.val;
        }
        int left = sumOfLeftLeavesHelper(root.left, true);
        int right = sumOfLeftLeavesHelper(root.right, false);
        return left + right + leave;
    }

    // 先序遍历求所有叶子节点值之和
    public int sumOfLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leave = 0;
        // 叶子节点
        if (root.left == null && root.right == null) {
            leave = root.val;
        }
        int left = sumOfLeaves(root.left);
        int right = sumOfLeaves(root.right);
        return left + right + leave;
    }

    // 先序遍历求所有节点值之和
    public int sumOfTrees(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leave = root.val;
        int left = sumOfTrees(root.left);
        int right = sumOfTrees(root.right);
        return left + right + leave;
    }

    public static void main(String[] args) {
        System.out.println(new Solution1().sumOfLeftLeaves(createTree(1, 2, 3, 4, 5)));
        //System.out.println(new Solution1().sumOfLeftLeaves(createTree(1, null, 2)));
        //System.out.println(new Solution1().sumOfLeftLeaves(createTree(3, 9, 20, null, null, 15, 7)));
    }
}