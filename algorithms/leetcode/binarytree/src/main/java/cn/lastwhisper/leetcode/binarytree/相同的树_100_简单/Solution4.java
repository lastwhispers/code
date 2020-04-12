package cn.lastwhisper.leetcode.binarytree.相同的树_100_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import java.util.LinkedList;

import static cn.lastwhisper.leetcode.common.tree.TreeUtil.createTree;

class Solution4 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/same-tree/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：先序遍历-迭代
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(p);
        stack.push(q);
        while (!stack.isEmpty()) {
            p = stack.pop();
            q = stack.pop();

            if (p == null && q == null) continue;
            if (p == null || q == null) return false;
            if (p.val != q.val) return false;

            stack.push(p.left);
            stack.push(q.left);
            stack.push(p.right);
            stack.push(q.right);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution4().isSameTree(createTree(1, 2, 3), createTree(1, 2, 3)));
        System.out.println(new Solution4().isSameTree(createTree(1, 2), createTree(1, null, 2)));
        System.out.println(new Solution4().isSameTree(createTree(1, 2, 1), createTree(1, 1, 2)));
    }
}