package cn.lastwhisper.offer.面试题27_二叉树的镜像;

import cn.lastwhisper.leetcode.common.tree.TreeNode;
import cn.lastwhisper.leetcode.common.tree.TreeUtils;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路： cn.lastwhisper.leetcode.binarytree.翻转二叉树_226_简单
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode temp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(temp);

        return root;
    }

    public static void main(String[] args) {
        TreeNode tree = TreeUtils.createTree(4, 2, 7, 1, 3, 6, 9);
        TreeUtils.printLevelOrder(new Solution1().mirrorTree(tree));
    }
}