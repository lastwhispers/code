package cn.lastwhisper.leetcode.binarytree.路径总和_112_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;
import javafx.util.Pair;

import java.util.LinkedList;

class Solution4 {
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
        stack.push(new Pair<>(root, 0));

        Pair<TreeNode, Integer> pair;
        Integer parentVal;
        while (!stack.isEmpty()) {
            pair = stack.pop();
            root = pair.getKey();
            parentVal = pair.getValue();
            if (root.left == null && root.right == null) {
                if (root.val + parentVal == sum) {
                    return true;
                }
            }
            if (root.left != null) {
                stack.push(new Pair<>(root.left, root.val + parentVal));
            }
            if (root.right != null) {
                stack.push(new Pair<>(root.right, root.val + parentVal));
            }
        }
        return false;
    }

    public static void main(String[] args) {

    }
}