package cn.lastwhisper.leetcode.stackqueue.二叉树的中序遍历_94_中等;

import cn.lastwhisper.leetcode.stackqueue.TreeNode;

import java.util.ArrayList;
import java.util.List;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
     * -------------------------------------------------------------------
     * 时间复杂度：https://www.cnblogs.com/wu8685/archive/2010/12/21/1912347.html
     * 空间复杂度：
     */

    List<Integer> list = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            list.add(root.val);
            inorderTraversal(root.right);
        }
        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);

        root.right = node1;
        node1.left = node2;

        new Solution1().inorderTraversal(root).forEach(node -> {
            System.out.print(node + ",");
        });
    }
}