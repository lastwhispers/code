package cn.cunchang.二叉树的最大深度_104_简单;


import cn.cunchang.tree.TreeNode;

class Solution2 {
    int res = 0;
    int depth = 0;

    public int maxDepth(TreeNode root) {
        maxDepth2(root);
        return res;
    }

    public void maxDepth2(TreeNode root) {
        if (root == null) {
            return;
        }

        // 进入节点深度增加
        depth++;
        // 当前是最后一个节点了，记录一下最大位置
        if (root.left == null && root.right == null) {
            res = Math.max(res, depth);
        }
        maxDepth(root.left);
        maxDepth(root.right);
        // 离开当前节点，深度减少
        depth--;
    }
}