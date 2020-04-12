package cn.lastwhisper.leetcode.binarytree.二叉树的最小深度_111_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;
import cn.lastwhisper.leetcode.common.tree.TreeUtil;
import javafx.util.Pair;

import java.util.LinkedList;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
     * -------------------------------------------------------------------
     * 思考：
     *  Q:何为二叉树的深度？
     *  A:从根节点到叶子节点的路径上的节点数量
     * -------------------------------------------------------------------
     * 思路：先序遍历-迭代
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：最坏O(n)，可能保存整颗树
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        stack.push(new Pair<>(root, 1));

        int minDepth = Integer.MAX_VALUE;
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> pair = stack.pop();
            root = pair.getKey();
            Integer depth = pair.getValue();
            if (root.left == null && root.right == null) {
                minDepth = Math.min(minDepth, depth);
            }
            if (root.left != null) {
                stack.push(new Pair<>(root.left, depth + 1));
            }
            if (root.right != null) {
                stack.push(new Pair<>(root.right, depth + 1));
            }
        }
        return minDepth;
    }

    public static void main(String[] args) {
        /*
         *     3
         *    / \
         *   9  20
         *     /  \
         *    15   7
         */
        System.out.println(new Solution2().minDepth(TreeUtil.createTree(3,9,20,null,null,15,7)));
    }
}
