package cn.lastwhisper.leetcode.binarytree.对称二叉树_101_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

import static cn.lastwhisper.leetcode.common.tree.TreeUtil.createTree;

class Solution3 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/symmetric-tree/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：层次遍历(BFS)
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)，因为我们遍历整个输入树一次，所以总的运行时间为 O(n)，其中 nn 是树中节点的总数。
     * 空间复杂度：递归调用的次数受树的高度限制。在最糟糕情况下，树是线性的，其高度为 O(n)。
     *  因此，在最糟糕的情况下，由栈上的递归调用造成的空间复杂度为 O(n)。
     */
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution3().isSymmetric(createTree(1, 2, 2, 3, 4, 4, 3))); // true
        System.out.println(new Solution3().isSymmetric(createTree(1, 2, 2, null, 3, null, 3))); //false
        System.out.println(new Solution3().isSymmetric(createTree(2, 3, 3, 4, 5, 5))); //false
    }
}