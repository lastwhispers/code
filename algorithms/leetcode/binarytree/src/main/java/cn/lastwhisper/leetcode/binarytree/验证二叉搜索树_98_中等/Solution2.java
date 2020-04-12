package cn.lastwhisper.leetcode.binarytree.验证二叉搜索树_98_中等;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import static cn.lastwhisper.leetcode.common.tree.TreeUtil.createTree;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/validate-binary-search-tree/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) return true;
        // 右子树>root
        if (min != null && root.val <= min.val) return false;
        // 左子树<root
        if (max != null && root.val >= max.val) return false;
        return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().isValidBST(createTree(5, 1, 4, null, null, 3, 6)));
        System.out.println(new Solution2().isValidBST(createTree(1, 1)));
    }
}