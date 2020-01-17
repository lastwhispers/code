package cn.lastwhisper.leetcode.stackqueue.二叉树的层次遍历_102_中等;

import cn.lastwhisper.leetcode.stackqueue.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static cn.lastwhisper.leetcode.stackqueue.TreeUtils.create102Tree;

class Solution1 {
    /**
     * 题目地址：
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 遍历每层的数据
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
            result.add(list);
        }

        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new Solution1().levelOrder(create102Tree());
        System.out.println("[");
        for (List<Integer> list : lists) {
            System.out.print("\t[");
            for (Integer i : list) {
                System.out.print(i + ",");
            }
            System.out.print("]\n");
        }
        System.out.println("]");
    }
}