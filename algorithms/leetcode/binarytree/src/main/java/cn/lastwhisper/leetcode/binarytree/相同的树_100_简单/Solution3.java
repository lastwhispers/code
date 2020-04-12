package cn.lastwhisper.leetcode.binarytree.相同的树_100_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import java.util.LinkedList;

import static cn.lastwhisper.leetcode.common.tree.TreeUtil.createTree;

class Solution3 {
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
        LinkedList<TreeNode> pStack = new LinkedList<>();
        LinkedList<TreeNode> qStack = new LinkedList<>();
        pStack.push(p);
        qStack.push(q);
        while (!pStack.isEmpty() && !qStack.isEmpty()) {
            p = pStack.pop();
            q = qStack.pop();

            if (p == null && q == null) continue;
            if (p == null || q == null) return false;
            if (p.val != q.val) return false;

            pStack.push(p.left);
            pStack.push(p.right);
            qStack.push(q.left);
            qStack.push(q.right);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution3().isSameTree(createTree(1, 2, 3), createTree(1, 2, 3)));
        System.out.println(new Solution3().isSameTree(createTree(1, 2), createTree(1, null, 2)));
        System.out.println(new Solution3().isSameTree(createTree(1, 2, 1), createTree(1, 1, 2)));
    }
}