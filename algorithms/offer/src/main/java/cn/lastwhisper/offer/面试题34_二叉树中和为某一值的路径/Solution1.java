package cn.lastwhisper.offer.面试题34_二叉树中和为某一值的路径;

import cn.lastwhisper.leetcode.common.print.PrintUtils;
import cn.lastwhisper.leetcode.common.tree.TreeNode;
import cn.lastwhisper.leetcode.common.tree.TreeUtils;

import java.util.ArrayList;
import java.util.List;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：递归回溯
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：O(n^2)
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        pathSumHelper(root, sum, new ArrayList<>(), result);

        return result;
    }

    public void pathSumHelper(TreeNode root, int sum, List<Integer> list, List<List<Integer>> result) {
        if (root == null) {
            return;
        }

        list.add(root.val);
        sum -= root.val;
        if (sum == 0 && root.left == null && root.right == null) {
            result.add(new ArrayList<>(list));
        } else {
            pathSumHelper(root.left, sum, list, result);
            pathSumHelper(root.right, sum, list, result);
        }
        list.remove(list.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode tree = TreeUtils.createTree(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1);
        int sum = 22;
        PrintUtils.printLists(new Solution1().pathSum(tree, sum));
    }
}