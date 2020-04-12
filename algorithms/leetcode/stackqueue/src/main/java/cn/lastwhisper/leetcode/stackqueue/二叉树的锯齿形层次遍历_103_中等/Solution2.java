package cn.lastwhisper.leetcode.stackqueue.二叉树的锯齿形层次遍历_103_中等;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static cn.lastwhisper.leetcode.common.tree.TreeUtil.createTree;
import static cn.lastwhisper.leetcode.common.print.PrintUtil.printLists;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：DFS
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        traversal(root, res, 0);
        return res;
    }

    private void traversal(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) {
            return;
        }

        if (res.size() == level) {
            res.add(new ArrayList<>());
        }

        if ((level & 1) == 1) {
            res.get(level).add(0, root.val);
        } else {
            res.get(level).add(root.val);
        }

        traversal(root.left, res, level + 1);
        traversal(root.right, res, level + 1);
    }

    public static void main(String[] args) {
        TreeNode root = createTree(3,9,20,null,null,15,7);
        List<List<Integer>> lists = new Solution2().zigzagLevelOrder(root);
        printLists(lists);
    }
}