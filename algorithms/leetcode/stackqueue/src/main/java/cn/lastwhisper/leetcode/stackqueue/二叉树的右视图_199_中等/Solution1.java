package cn.lastwhisper.leetcode.stackqueue.二叉树的右视图_199_中等;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static cn.lastwhisper.leetcode.common.tree.TreeUtils.createTree;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/binary-tree-right-side-view/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：每一层次最后的一个值
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 遍历每层的数据
            int size = queue.size();
            Integer val = null;
            for (int i = 0; i < size; i++) {
                root = queue.poll();
                // 只取最后一个值
                if (i == size - 1) {
                    val = root.val;
                }
                if (root.left != null) {
                    queue.add(root.left);
                }
                if (root.right != null) {
                    queue.add(root.right);
                }
            }
            result.add(val);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode tree = createTree(1, 2, 3, null, 5, null, 4);
        new Solution1().rightSideView(tree).forEach(System.out::println);
    }
}