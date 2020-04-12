package cn.lastwhisper.leetcode.binarytree.左叶子之和_404_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import static cn.lastwhisper.leetcode.common.tree.TreeUtil.createTree;

class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        return helper(root);
    }

    public int helper(TreeNode node) {
        int sum = 0;
        if (node == null) {
            return 0;
        }
        if (node.left != null && (node.left.left == null && node.left.right == null)) {
            sum += node.left.val;
        }
        sum += helper(node.left) + helper(node.right);
        return sum;
    }

    public static void main(String[] args) {
        //System.out.println(new Solution().sumOfLeftLeaves(createTree(1, 2, 3, 4, 5)));
        //System.out.println(new Solution().sumOfLeftLeaves(createTree(1, null, 2)));
        System.out.println(new Solution().sumOfLeftLeaves(createTree(3, 9, 20, null, null, 15, 7)));
    }
}