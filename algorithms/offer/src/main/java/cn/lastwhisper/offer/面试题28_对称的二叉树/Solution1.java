package cn.lastwhisper.offer.面试题28_对称的二叉树;

import cn.lastwhisper.leetcode.common.tree.TreeNode;
import cn.lastwhisper.leetcode.common.tree.TreeUtils;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 对比左右子树是否相等
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.val == root2.val) {
            return isSymmetric(root1.left, root2.right)&&isSymmetric(root1.right, root2.left);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution1().isSymmetric(TreeUtils.createTree(1, 2, 2, 3, 4, 4, 3)));
    }
}