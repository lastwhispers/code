package cn.lastwhisper.leetcode.binarytree.翻转二叉树_226_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

import static cn.lastwhisper.leetcode.common.tree.TreeUtils.*;

class Solution3 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/invert-binary-tree/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：迭代BFS，层级遍历翻转(二叉树的层次遍历_102)
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public TreeNode invertTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        TreeNode current = null;
        while (!queue.isEmpty()) {
            //int size = queue.size();
            //for (int i = 0; i < size; i++) {
            current = queue.poll();
            // 翻转当前节点的左右子节点
            TreeNode tempNode = current.left;
            current.left = current.right;
            current.right = tempNode;

            if (current.right != null) queue.add(current.right);
            if (current.left != null) queue.add(current.left);
            //}
        }
        return root;
    }

    public static void main(String[] args) {
        printLevelOrder(createTree(4,2,7,1,3,6,9));
        System.out.println("-------------------------------------");
        printLevelOrder(new Solution3().invertTree(createTree(4,2,7,1,3,6,9)));
    }
}