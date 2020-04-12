package cn.lastwhisper.leetcode.stackqueue.二叉树的中序遍历_94_中等;

import cn.lastwhisper.leetcode.common.tree.TreeNode;
import cn.lastwhisper.leetcode.common.tree.TreeUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：遍历
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                result.add(root.val);
                root = root.right;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode tree = TreeUtil.createTree();
        new Solution2().inorderTraversal(tree).forEach(node -> {
            System.out.print(node + ",");
        });
    }
}