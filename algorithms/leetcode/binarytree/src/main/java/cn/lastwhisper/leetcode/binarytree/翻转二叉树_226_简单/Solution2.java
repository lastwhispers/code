package cn.lastwhisper.leetcode.binarytree.翻转二叉树_226_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import java.util.LinkedList;

import static cn.lastwhisper.leetcode.common.tree.TreeUtils.*;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/invert-binary-tree/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：将先序遍历（递归）利用Stack改成迭代。使用Stack模拟系统递归栈
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public TreeNode invertTree(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        if (root != null) {
            stack.push(root);
        }
        TreeNode current = null;
        while (!stack.isEmpty()) {
            current = stack.poll();
            // 翻转当前节点的左右子节点
            TreeNode tempNode = current.left;
            current.left = current.right;
            current.right = tempNode;
            // 此时left与right已经交换，栈先进先出，所以入栈顺序left->right
            if (current.left != null) stack.push(current.left);
            if (current.right != null) stack.push(current.right);
        }
        return root;
    }

    public static void main(String[] args) {
        printLevelOrder(createTree(4,2,7,1,3,6,9));
        System.out.println("-------------------------------------");
        printLevelOrder(new Solution2().invertTree(createTree(4,2,7,1,3,6,9)));
    }
}