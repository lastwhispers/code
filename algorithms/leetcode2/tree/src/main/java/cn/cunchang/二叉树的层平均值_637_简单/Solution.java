package cn.cunchang.二叉树的层平均值_637_简单;

import cn.cunchang.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Double> resultList = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            double result = 0.0;
            int levelCount = queue.size();
            for (int i = 0; i < levelCount; i++) {
                TreeNode treeNode = queue.poll();
                result += treeNode.val;
                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
            }
            resultList.add(result/levelCount);
        }
        return resultList;
    }

}