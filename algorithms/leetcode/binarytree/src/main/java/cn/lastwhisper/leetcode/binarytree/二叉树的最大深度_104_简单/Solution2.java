package cn.lastwhisper.leetcode.binarytree.二叉树的最大深度_104_简单;


import cn.lastwhisper.leetcode.common.tree.TreeNode;
import cn.lastwhisper.leetcode.common.tree.TreeUtil;
import javafx.util.Pair;

import java.util.LinkedList;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：先序遍历-迭代
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：不平衡O(n)、平衡O(logn)
     */
    public int maxDepth(TreeNode root) {
        LinkedList<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        if (root != null) {
            stack.push(new Pair<>(root, 0));
        }
        int maxDepth = 0;
        // stack模拟递归系统栈
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> pair = stack.poll();
            root = pair.getKey();
            Integer height = pair.getValue();
            // dfs过程中更新最大深度
            maxDepth = Math.max(maxDepth, height);
            if (root != null) {
                stack.push(new Pair<>(root.right, height + 1));
                stack.push(new Pair<>(root.left, height + 1));
            }
        }

        return maxDepth;
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().maxDepth(TreeUtil.createTree()));
    }

}