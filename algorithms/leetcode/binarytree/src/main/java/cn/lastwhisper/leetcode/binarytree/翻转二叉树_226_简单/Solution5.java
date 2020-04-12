package cn.lastwhisper.leetcode.binarytree.翻转二叉树_226_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import static cn.lastwhisper.leetcode.common.tree.TreeUtil.createTree;
import static cn.lastwhisper.leetcode.common.tree.TreeUtil.printLevelOrder;

class Solution5 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/invert-binary-tree/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：后序遍历-递归
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public TreeNode invertTree(TreeNode root) {
        // 后序遍历-- 从下向上交换
        if (root == null) return null;
        TreeNode leftNode = invertTree(root.left);
        TreeNode rightNode = invertTree(root.right);
        root.right = leftNode;
        root.left = rightNode;
        return root;
    }
    
    public static void main(String[] args) {
        printLevelOrder(createTree(4, 2, 7, 1, 3, 6, 9));
        System.out.println("-------------------------------------");
        printLevelOrder(new Solution5().invertTree(createTree(4, 2, 7, 1, 3, 6, 9)));
    }
}