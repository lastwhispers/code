package cn.lastwhisper.leetcode.binarytree.求根到叶子节点数字之和_129_中等;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static cn.lastwhisper.leetcode.common.tree.TreeUtils.createTree;
import static cn.lastwhisper.leetcode.common.tree.TreeUtils.printList;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/binary-tree-paths/
     * -------------------------------------------------------------------
     * 思考：二叉树的所有路径_257
     * -------------------------------------------------------------------
     * 思路：先序遍历-递归
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<String> result = new ArrayList<>();
        binaryTreePathsHelper(root, result, String.valueOf(root.val));
        int sum = 0;
        for (String s : result) {
            sum += Integer.parseInt(s);
        }
        return sum;
    }

    public void binaryTreePathsHelper(TreeNode root, List<String> result, String path) {
        if (root.left == null && root.right == null) {
            result.add(path);
        }
        if (root.left != null) {
            binaryTreePathsHelper(root.left, result, path + root.left.val);
        }
        if (root.right != null) {
            binaryTreePathsHelper(root.right, result, path + root.right.val);
        }
    }


    public static void main(String[] args) {
        System.out.println(new Solution1().sumNumbers(createTree(1, 2, 3)));
        System.out.println(new Solution1().sumNumbers(createTree(4,9,0,5,1)));
    }
}