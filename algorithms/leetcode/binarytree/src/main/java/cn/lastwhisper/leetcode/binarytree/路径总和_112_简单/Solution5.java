package cn.lastwhisper.leetcode.binarytree.路径总和_112_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;
import javafx.util.Pair;

import java.util.LinkedList;

import static cn.lastwhisper.leetcode.common.tree.TreeUtil.createTree;

class Solution5 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/path-sum/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：先序遍历-迭代；
     *  在根节点到叶子节点的路径总和=sum即可
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        // 节点
        LinkedList<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        stack.push(new Pair<>(root, sum));
        Pair<TreeNode, Integer> pair;
        // 暂存当前位置sum还剩下的值
        Integer parentSum;
        while (!stack.isEmpty()) {
            pair = stack.pop();
            root = pair.getKey();
            parentSum = pair.getValue();
            if (root.left == null && root.right == null) {
                if (parentSum - root.val == 0) {
                    return true;
                }
            }
            if (root.left != null) {
                stack.push(new Pair<>(root.left, parentSum - root.val));
            }
            if (root.right != null) {
                stack.push(new Pair<>(root.right, parentSum - root.val));
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode tree = createTree(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1);
        int sum = 22;
        System.out.println(new Solution5().hasPathSum(tree, sum));
    }
}