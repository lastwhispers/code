package cn.cunchang.二叉树的前序遍历_144_简单;

import cn.cunchang.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preOrder = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.pop();
            if (treeNode != null) {
                preOrder.add(treeNode.val);
                // 先进后出，保证left先执行
                stack.push(treeNode.right);
                stack.push(treeNode.left);
            }
        }
        return preOrder;
    }

//    public List<Integer> preorderTraversal(TreeNode root) {
//        List<Integer> preOrder = new ArrayList<>();
//        preorderTraversal(root, preOrder);
//        return preOrder;
//    }
//
//    public void preorderTraversal(TreeNode root, List<Integer> preOrder) {
//        if (root == null) {
//            return;
//        }
//        preOrder.add(root.val);
//        preorderTraversal(root.left, preOrder);
//        preorderTraversal(root.right, preOrder);
//    }
}