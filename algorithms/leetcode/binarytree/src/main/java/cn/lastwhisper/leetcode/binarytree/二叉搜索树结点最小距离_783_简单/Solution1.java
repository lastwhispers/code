package cn.lastwhisper.leetcode.binarytree.二叉搜索树结点最小距离_783_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static cn.lastwhisper.leetcode.common.tree.TreeUtils.createTree;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：bst中序遍历就是从小到大
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int minDiffInBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        middleDfs(root, list);

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.size() - 1; i++) {
            min = Math.min(min, list.get(i + 1) - list.get(i));
        }
        return min;
    }

    public void middleDfs(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }
    }

    //public void middleDfs(TreeNode root, List<Integer> list) {
    //    if (root != null) {
    //        middleDfs(root.left, list);
    //        list.add(root.val);
    //        middleDfs(root.right, list);
    //    }
    //}

    public static void main(String[] args) {
        System.out.println(new Solution1().minDiffInBST(createTree(4, 2, 6, 1, 3, null, null)));
    }
}