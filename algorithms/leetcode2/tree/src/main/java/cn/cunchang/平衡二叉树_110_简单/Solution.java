package cn.cunchang.平衡二叉树_110_简单;

import cn.cunchang.tree.TreeNode;

class Solution {
    public boolean isBalanced(TreeNode root) {
        return isBalanced0(root) != -1;
    }

    public int isBalanced0(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDep = isBalanced0(root.left);
        int rightDep = isBalanced0(root.right);
        if (leftDep == -1 || rightDep == -1 || Math.abs(leftDep - rightDep) > 1) {
            return -1;
        }
        return Math.max(leftDep, rightDep) + 1;
    }
}