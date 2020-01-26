package cn.lastwhisper.leetcode.stackqueue.二叉树的前序遍历_144_中等;

import cn.lastwhisper.leetcode.common.tree.TreeNode;
import cn.lastwhisper.leetcode.common.tree.TreeUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：前序遍历-迭代
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        // 根-左-右
        while (!stack.isEmpty()) {
            root = stack.pop();
            result.add(root.val);
            if (root.right != null) stack.push(root.right);
            if (root.left != null) stack.push(root.left);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode tree = TreeUtils.createTree();

        new Solution2().preorderTraversal(tree).forEach(node -> {
            System.out.print(node + ",");
        });
    }
}