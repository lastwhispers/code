package cn.lastwhisper.leetcode.stackqueue.二叉树的层次遍历_II_107_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import java.util.*;

import static cn.lastwhisper.leetcode.common.tree.TreeUtils.createTree;
import static cn.lastwhisper.leetcode.common.tree.TreeUtils.printLists;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/submissions/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Stack<List<Integer>> stack = new Stack<>();
        if (root == null)
            return stack;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 取出每层的数据
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while (size > 0) {
                root = queue.poll();
                list.add(root.val);
                if (root.left != null) {
                    queue.add(root.left);
                }
                if (root.right != null) {
                    queue.add(root.right);
                }
                size--;
            }
            stack.push(list);
        }
        return stack;
    }

    public static void main(String[] args) {
        TreeNode root = createTree(3, 9, 20, null, null, 15, 7);
        List<List<Integer>> lists = new Solution2().levelOrderBottom(root);
        printLists(lists);
    }
}