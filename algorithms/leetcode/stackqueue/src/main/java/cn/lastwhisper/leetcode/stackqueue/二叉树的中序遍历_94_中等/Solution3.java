package cn.lastwhisper.leetcode.stackqueue.二叉树的中序遍历_94_中等;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static cn.lastwhisper.leetcode.common.tree.TreeUtils.createTree;
import static cn.lastwhisper.leetcode.common.tree.TreeUtils.printList;

class Solution3 {
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
            //节点不为空一直压栈
            while (root != null) {
                stack.push(root);
                root = root.left; //考虑左子树
            }
            //节点为空，就出栈
            root = stack.pop();
            //当前值加入
            result.add(root.val);
            root = root.right;//考虑右子树
        }
        return result;
    }

    public static void main(String[] args) {
        printList(new Solution3().inorderTraversal(createTree(1, null, 2, 3)));
    }
}