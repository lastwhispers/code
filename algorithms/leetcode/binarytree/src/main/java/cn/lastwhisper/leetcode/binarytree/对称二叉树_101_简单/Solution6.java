package cn.lastwhisper.leetcode.binarytree.对称二叉树_101_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import static cn.lastwhisper.leetcode.common.tree.TreeUtils.createTree;

class Solution6 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/symmetric-tree/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     *  1.将二叉树的左子节点或者右子节点左右翻转
     *  2.比较左右子节点是否相同
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        TreeNode right = invertTree(root.right);
        return isSameTree(root.left, right);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 递归结束条件
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        // 递归体
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        // 翻转当前节点的左右子节点
        TreeNode tempNode = root.left;

        root.left = invertTree(root.right);
        root.right = invertTree(tempNode);
        return root;
    }


    public static void main(String[] args) {
        System.out.println(new Solution6().isSymmetric(createTree()));
    }
}