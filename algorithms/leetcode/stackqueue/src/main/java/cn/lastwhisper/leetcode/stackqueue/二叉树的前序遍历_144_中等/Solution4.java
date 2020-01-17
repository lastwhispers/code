package cn.lastwhisper.leetcode.stackqueue.二叉树的前序遍历_144_中等;

import cn.lastwhisper.leetcode.stackqueue.TreeNode;
import cn.lastwhisper.leetcode.stackqueue.TreeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution4 {
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
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.empty()) {
            while (root != null) {
                result.add(root.val);
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode tree = TreeUtils.createTree();

        new Solution4().preorderTraversal(tree).forEach(node -> {
            System.out.print(node + ",");
        });
    }
}