package cn.lastwhisper.leetcode.binarytree.路径总和_II_113_中等;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static cn.lastwhisper.leetcode.common.tree.TreeUtils.createTree;
import static cn.lastwhisper.leetcode.common.tree.TreeUtils.printLists;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/path-sum-ii/
     * -------------------------------------------------------------------
     * 思考：路径总和(112)+二叉树的所有路劲(257)
     * -------------------------------------------------------------------
     * 思路：先序遍历-递归
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        pathSumHelper(root, String.valueOf(root.val), root.val, result, sum);
        return result;
    }

    public void pathSumHelper(TreeNode root, String path, int partSum, List<List<Integer>> result, int sum) {
        if (root.left == null && root.right == null && partSum == sum) {
            String[] arr = path.split(",");
            List<Integer> list = new ArrayList<>(arr.length);
            for (String a : arr) {
                list.add(Integer.parseInt(a));
            }
            result.add(list);
        }
        if (root.left != null) {
            pathSumHelper(root.left, path + "," + root.left.val, partSum + root.left.val, result, sum);
        }
        if (root.right != null) {
            pathSumHelper(root.right, path + "," + root.right.val, partSum + root.right.val, result, sum);
        }
    }

    public static void main(String[] args) {
        printLists(new Solution1().pathSum(createTree(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1), 22));
    }
}