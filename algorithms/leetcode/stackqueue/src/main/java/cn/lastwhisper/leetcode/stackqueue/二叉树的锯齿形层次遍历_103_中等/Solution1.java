package cn.lastwhisper.leetcode.stackqueue.二叉树的锯齿形层次遍历_103_中等;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static cn.lastwhisper.leetcode.common.tree.TreeUtil.createTree;
import static cn.lastwhisper.leetcode.common.print.PrintUtil.printLists;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：BFS
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 遍历每层的数据
            int size = queue.size();
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                root = queue.poll();
                // result.size()=0,1,2,3代表层次
                // 根据层次来判断顺序；奇数倒序，偶数正序
                if ((result.size() & 1) == 1) {
                    list.push(root.val);
                } else {
                    list.add(root.val);
                }
                if (root.left != null) {
                    queue.add(root.left);
                }
                if (root.right != null) {
                    queue.add(root.right);
                }
            }
            result.add(list);
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = createTree(3,9,20,null,null,15,7);
        List<List<Integer>> lists = new Solution1().zigzagLevelOrder(root);
        printLists(lists);
    }
}