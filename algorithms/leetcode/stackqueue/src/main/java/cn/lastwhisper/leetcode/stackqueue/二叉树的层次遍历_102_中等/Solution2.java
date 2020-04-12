package cn.lastwhisper.leetcode.stackqueue.二叉树的层次遍历_102_中等;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static cn.lastwhisper.leetcode.common.print.PrintUtil.printLists;
import static cn.lastwhisper.leetcode.common.tree.TreeUtil.createTree;


class Solution2 {
    static class Pair {
        public TreeNode treeNode;
        public Integer level;

        public Pair(TreeNode treeNode, Integer level) {
            this.treeNode = treeNode;
            this.level = level;
        }
    }

    /**
     * 题目地址：https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
     * -------------------------------------------------------------------
     * 思考：迭代遍历
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            TreeNode treeNode = pair.treeNode;
            Integer level = pair.level;
            if (level == result.size()) {
                result.add(new ArrayList<>());
            }
            result.get(level).add(treeNode.val);
            if (treeNode.left != null) {
                queue.add(new Pair(treeNode.left, level + 1));
            }
            if (treeNode.right != null) {
                queue.add(new Pair(treeNode.right, level + 1));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new Solution2().levelOrder(createTree());
        printLists(lists);
    }
}