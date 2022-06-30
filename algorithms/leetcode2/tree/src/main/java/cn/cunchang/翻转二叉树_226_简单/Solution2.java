package cn.cunchang.翻转二叉树_226_简单;

import cn.cunchang.tree.TreeNode;

/**
 * @author cunchang
 * @date 2022/6/20 6:28 PM
 */
class Solution2 {
    /**
     * 通过分解解决
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}