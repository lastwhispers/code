package cn.cunchang.二叉树的最大深度_104_简单;


import cn.cunchang.tree.TreeNode;

class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDep = maxDepth(root.left);
        int rightDep = maxDepth(root.right);

        return Math.max(leftDep, rightDep) + 1;
    }
}