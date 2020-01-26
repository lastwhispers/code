package cn.lastwhisper.leetcode.binarytree.完全二叉树的节点个数_222_中等;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import static cn.lastwhisper.leetcode.common.tree.TreeUtils.createTree;

class Solution3 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/count-complete-tree-nodes/
     * -------------------------------------------------------------------
     * 思考：完全二叉树，左右子树肯定存在一颗满二叉树
     * -------------------------------------------------------------------
     * 思路：利用二叉树的特点
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：最坏O(n)
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        if (left == right) {
            /*
             * 左子树肯定是满二叉树，左子树节点总数=2^left-1，加上root，正好是2^left
             * 再对右子树进行递归统计。
             */
            return (1 << left) + countNodes(root.right);//2^left
        } else {
            /*
             * 右子树肯定是满二叉树，右子树节点总数=2^right-1，加上root，正好是2^right
             * 再对左子树进行递归统计。
             */
            return (1 << right) + countNodes(root.left);
        }
    }

    private int maxDepth(TreeNode root) {
        int level = 0;
        while (root != null) {
            level++;
            root = root.left;
        }
        return level;
    }

    public static void main(String[] args) {
        System.out.println(new Solution3().countNodes(createTree(1, 2, 3, 4, 5, 6, null)));
    }
}