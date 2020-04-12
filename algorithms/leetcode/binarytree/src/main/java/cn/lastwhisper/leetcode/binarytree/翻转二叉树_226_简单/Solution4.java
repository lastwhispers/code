package cn.lastwhisper.leetcode.binarytree.翻转二叉树_226_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import static cn.lastwhisper.leetcode.common.tree.TreeUtil.createTree;
import static cn.lastwhisper.leetcode.common.tree.TreeUtil.printLevelOrder;

class Solution4 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/invert-binary-tree/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：中序遍历-递归
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        invertTree(root.left); // 递归找到左节点
        TreeNode rightNode = root.right; // 保存右节点
        root.right = root.left;
        root.left = rightNode;
        // 递归找到右节点 继续交换 : 因为此时左右节点已经交换了,所以此时的右节点为root.left
        invertTree(root.left);
        return root;
    }

    public static void main(String[] args) {
        printLevelOrder(createTree(4, 2, 7, 1, 3, 6, 9));
        System.out.println("-------------------------------------");
        printLevelOrder(new Solution4().invertTree(createTree(4, 2, 7, 1, 3, 6, 9)));
    }
}