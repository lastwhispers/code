package cn.lastwhisper.leetcode.binarytree.二叉树的最大深度_104_简单;


import cn.lastwhisper.leetcode.common.tree.TreeNode;
import cn.lastwhisper.leetcode.common.tree.TreeUtils;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
     * -------------------------------------------------------------------
     * 思考：
     *  Q:递归结束条件？
     *  A:当前TreeNode为null
     * -------------------------------------------------------------------
     * 思路：先序遍历-递归
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：不平衡O(n)、平衡O(logn)
     */
    public int maxDepth(TreeNode root) {
        // 递归结束条件
        if (root == null) {
            return 0;
        }
        // 递归过程
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution1().maxDepth(TreeUtils.createTree()));
    }

}