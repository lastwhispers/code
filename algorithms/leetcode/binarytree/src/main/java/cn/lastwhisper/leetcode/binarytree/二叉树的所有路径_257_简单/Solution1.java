package cn.lastwhisper.leetcode.binarytree.二叉树的所有路径_257_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static cn.lastwhisper.leetcode.common.tree.TreeUtils.createTree;
import static cn.lastwhisper.leetcode.common.tree.TreeUtils.printList;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/binary-tree-paths/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：先序遍历-递归
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        binaryTreePathsHelper(root, result, String.valueOf(root.val));
        return result;
    }

    public void binaryTreePathsHelper(TreeNode root, List<String> result, String path) {
        if (root.left == null && root.right == null) {
            result.add(path);
        }
        if (root.left != null) {
            binaryTreePathsHelper(root.left, result, path + "->" + root.left.val);
        }
        if (root.right != null) {
            binaryTreePathsHelper(root.right, result, path + "->" + root.right.val);
        }
    }


    public static void main(String[] args) {
        printList(new Solution1().binaryTreePaths(createTree(1, 2, 3, null, 5)));
    }
}