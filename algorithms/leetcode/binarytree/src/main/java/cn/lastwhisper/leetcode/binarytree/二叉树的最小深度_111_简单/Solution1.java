package cn.lastwhisper.leetcode.binarytree.二叉树的最小深度_111_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;
import cn.lastwhisper.leetcode.common.tree.TreeUtil;
class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
     * -------------------------------------------------------------------
     * 思考：
     *  Q:何为二叉树的深度？
     *  A:从根节点到叶子节点的路径上的节点数量
     * -------------------------------------------------------------------
     * 思路：先序遍历-递归
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 叶子节点
        if ((root.left == null) && (root.right == null)) {
            return 1;
        }

        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
            min_depth = Math.min(minDepth(root.left), min_depth);
        }
        if (root.right != null) {
            min_depth = Math.min(minDepth(root.right), min_depth);
        }
        return min_depth + 1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution1().minDepth(TreeUtil.createTree()));
    }
}
