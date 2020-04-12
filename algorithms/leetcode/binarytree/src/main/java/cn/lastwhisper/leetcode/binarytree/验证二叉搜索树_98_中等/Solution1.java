package cn.lastwhisper.leetcode.binarytree.验证二叉搜索树_98_中等;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import java.util.LinkedList;

import static cn.lastwhisper.leetcode.common.tree.TreeUtil.createTree;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/validate-binary-search-tree/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：bst中序遍历就是从小到大
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public boolean isValidBST(TreeNode root) {
        double inorder = -Double.MAX_VALUE;
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 每一个节点值都应该比前一个值大
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution1().isValidBST(createTree(5, 1, 4, null, null, 3, 6)));
        System.out.println(new Solution1().isValidBST(createTree(1, 1)));
    }
}