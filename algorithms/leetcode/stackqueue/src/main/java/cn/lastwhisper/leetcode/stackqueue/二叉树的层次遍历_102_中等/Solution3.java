package cn.lastwhisper.leetcode.stackqueue.二叉树的层次遍历_102_中等;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static cn.lastwhisper.leetcode.common.print.PrintUtil.printLists;
import static cn.lastwhisper.leetcode.common.tree.TreeUtil.*;

class Solution3 {

    /**
     * 题目地址：https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
     * -------------------------------------------------------------------
     * 思考：将2改成递归
     * -------------------------------------------------------------------
     * 思路：递归遍历，与思路2迭代遍历相同
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        traversal(root, result, 0);
        return result;
    }

    /**
     * 递归遍历
     */
    private void traversal(TreeNode root, List<List<Integer>> result, int level) {
        if (root == null) {
            return;
        }

        if (result.size() == level) {
            result.add(new ArrayList<>());
        }

        result.get(level).add(root.val);
        // 递归左右子树
        traversal(root.left, result, level + 1);
        traversal(root.right, result, level + 1);
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new Solution3().levelOrder(createTree());
        printLists(lists);
    }
}