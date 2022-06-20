package cn.cunchang.二叉树的直径_543_简单;

import cn.cunchang.tree.TreeNode;

class Solution {
    int res = 1;

    public int diameterOfBinaryTree(TreeNode root) {
        diameterOfBinaryTree0(root);
        return res - 1;
    }

    public int diameterOfBinaryTree0(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDep = diameterOfBinaryTree0(root.left);
        int rightDep = diameterOfBinaryTree0(root.right);
        res = Math.max(leftDep + rightDep + 1, res);
        return Math.max(leftDep, rightDep) + 1;
    }
}