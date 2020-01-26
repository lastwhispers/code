package cn.lastwhisper.leetcode.binarytree.完全二叉树的节点个数_222_中等;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import java.util.LinkedList;

import static cn.lastwhisper.leetcode.common.tree.TreeUtils.createTree;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/count-complete-tree-nodes/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：遍历时计数-迭代
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：最坏O(n)
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        int count = 0;
        // 根-左-右
        while (!stack.isEmpty()) {
            root = stack.pop();
            count++;
            if (root.right != null) stack.push(root.right);
            if (root.left != null) stack.push(root.left);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().countNodes(createTree(1, 2, 3, 4, 5, 6, null)));
    }
}