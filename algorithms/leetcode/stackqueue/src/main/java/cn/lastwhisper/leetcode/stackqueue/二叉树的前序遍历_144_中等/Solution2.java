package cn.lastwhisper.leetcode.stackqueue.二叉树的前序遍历_144_中等;

import cn.lastwhisper.leetcode.stackqueue.TreeNode;
import cn.lastwhisper.leetcode.stackqueue.TreeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：遍历
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode treeNode = stack.pop();
            result.add(treeNode.val);
            if (treeNode.right != null) stack.push(treeNode.right);
            if (treeNode.left != null) stack.push(treeNode.left);
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode tree = TreeUtils.createTraversalTree();

        new Solution2().preorderTraversal(tree).forEach(node -> {
            System.out.print(node + ",");
        });
    }
}