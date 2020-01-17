package cn.lastwhisper.leetcode.stackqueue.二叉树的前序遍历_144_中等;

import cn.lastwhisper.leetcode.stackqueue.TreeNode;
import cn.lastwhisper.leetcode.stackqueue.TreeUtils;

import java.util.ArrayList;
import java.util.List;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
     * -------------------------------------------------------------------
     * 时间复杂度：https://www.cnblogs.com/wu8685/archive/2010/12/21/1912347.html
     * 空间复杂度：
     */
    List<Integer> list = new ArrayList<>();

    // 递归
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root != null) {
            list.add(root.val);
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
        return list;
    }

    public static void main(String[] args) {
        TreeNode root = TreeUtils.createTraversalTree();

        new Solution1().preorderTraversal(root).forEach(node -> {
            System.out.print(node + ",");
        });
    }

}