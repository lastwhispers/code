package cn.lastwhisper.leetcode.binarytree.翻转二叉树_226_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import static cn.lastwhisper.leetcode.common.tree.TreeUtils.*;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/invert-binary-tree/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：先序遍历，递归DFS
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：最坏O(n)、最好O(logn)
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        // 翻转当前节点的左右子节点
        TreeNode tempNode = root.left;
        root.left = root.right;
        root.right = tempNode;
        // left与right已经交换，所以right写前面
        invertTree(root.right);
        invertTree(root.left);
        return root;
    }

    public TreeNode invertTree1(TreeNode root) {
        if (root == null)
            return null;
        // 翻转当前节点的左右子节点
        TreeNode tempNode = root.left;
        root.left = invertTree1(root.right);
        root.right = invertTree1(tempNode);
        return root;
    }

    public static void main(String[] args) {
        printLevelOrder(createTree(4,2,7,1,3,6,9));
        System.out.println("-------------------------------------");
        printLevelOrder(new Solution1().invertTree(createTree(4,2,7,1,3,6,9)));
    }
}