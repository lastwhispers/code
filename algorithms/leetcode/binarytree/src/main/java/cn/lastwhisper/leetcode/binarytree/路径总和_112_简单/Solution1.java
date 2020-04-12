package cn.lastwhisper.leetcode.binarytree.路径总和_112_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import static cn.lastwhisper.leetcode.common.tree.TreeUtil.createTree;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/path-sum/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：递归先序遍历递归
     *  先序遍历，sum-路径上节点的值，减到叶子节点，返回结果是否等于0
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        // 当前节点不为空
        if (root == null) {
            return false;
        }
        // 根节点到叶子节点中间路径和等于sum不行，叶子节点为终结才行
        if (root.left == null && root.right == null) {
            return sum - root.val == 0;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    public static void main(String[] args) {
        TreeNode tree = createTree(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1);
        //TreeNode tree = createTree(5, null, 8, null, null, 13, 4);
        int sum = 22;
        System.out.println(new Solution1().hasPathSum(tree, sum));
    }
}