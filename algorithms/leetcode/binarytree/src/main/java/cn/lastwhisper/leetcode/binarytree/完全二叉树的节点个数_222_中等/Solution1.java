package cn.lastwhisper.leetcode.binarytree.完全二叉树的节点个数_222_中等;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import static cn.lastwhisper.leetcode.common.tree.TreeUtils.createTree;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/count-complete-tree-nodes/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：遍历时计数-递归
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：最坏O(n)
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution1().countNodes(createTree(1, 2, 3, 4, 5, 6, null)));
    }
}