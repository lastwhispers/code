package cn.cunchang.对称二叉树_101_简单;

import cn.cunchang.tree.TreeNode;

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric0(root.left, root.right);
    }

    public boolean isSymmetric0(TreeNode p1, TreeNode p2) {
        if (p1 == null && p2 == null) {
            return true;
        }
        if (p1 == null || p2 == null) {
            return false;
        }
        if (p1.val != p2.val) {
            return false;
        }
        return isSymmetric0(p1.left, p2.right) && isSymmetric0(p1.right, p2.left);
    }
}