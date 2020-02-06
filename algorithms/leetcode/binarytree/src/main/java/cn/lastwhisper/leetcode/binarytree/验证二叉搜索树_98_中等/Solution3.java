package cn.lastwhisper.leetcode.binarytree.验证二叉搜索树_98_中等;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import java.util.LinkedList;

class Solution3 {
    private LinkedList<TreeNode> stack = new LinkedList<>();
    private LinkedList<Integer> uppers = new LinkedList<>();
    private LinkedList<Integer> lowers = new LinkedList<>();

    private void update(TreeNode node, Integer lower, Integer upper) {
        stack.add(node);
        lowers.add(lower);
        uppers.add(upper);
    }

    public boolean isValidBST(TreeNode root) {
        Integer lower = null, upper = null, val;
        update(root, lower, upper);

        while (!stack.isEmpty()) {
            root = stack.poll();
            lower = lowers.poll();
            upper = uppers.poll();

            if (root == null) continue;
            val = root.val;
            if (lower != null && val <= lower) return false;
            if (upper != null && val >= upper) return false;
            update(root.right, val, upper);
            update(root.left, lower, val);
        }
        return true;
    }
}