package cn.cunchang.最大二叉树_654_中等;

import cn.cunchang.tree.TreeNode;

/**
 * @author cunchang
 * @date 2022/6/20 7:50 PM
 */
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree0(nums, 0, nums.length - 1);
    }

    public TreeNode constructMaximumBinaryTree0(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        // 找到最大值构造根节点
        int max = Integer.MIN_VALUE;
        int index = -1;
        for (int i = left; i <= right; i++) {
            if (max < nums[i]) {
                index = i;
                max = nums[i];
            }

        }
        TreeNode treeNode = new TreeNode(max);
        // 再以最大值下标为界限，构造左右子树
        treeNode.left = constructMaximumBinaryTree0(nums, left, index - 1);
        treeNode.right = constructMaximumBinaryTree0(nums, index + 1, right);
        return treeNode;
    }

}