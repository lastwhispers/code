package cn.lastwhisper.leetcode.binarytree.相同的树_100_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import java.util.LinkedList;

import static cn.lastwhisper.leetcode.common.tree.TreeUtils.createTree;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/same-tree/
     * -------------------------------------------------------------------
     * 思考：思路三对条件进行优化
     * -------------------------------------------------------------------
     * 思路：先序遍历-迭代
     *  情况一：q或p为null false
     *  情况二：q与p为null true
     *  情况二：q!=p true
     *  情况三：q左子树或p左子树为null false
     *  情况四：q右子树或p右子树为null false
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }

        LinkedList<TreeNode> pStaCK = new LinkedList<>();
        LinkedList<TreeNode> qStaCK = new LinkedList<>();
        pStaCK.push(p);
        qStaCK.push(q);
        while (!pStaCK.isEmpty() && !qStaCK.isEmpty()) {
            p = pStaCK.pop();
            q = qStaCK.pop();
            if (p.val != q.val) {
                return false;
            }

            //p、q左子树有一个为null
            if ((p.left == null && q.left != null) || (p.left != null && q.left == null)) {
                return false;
            }
            //p、q右子树有一个为null
            if ((p.right == null && q.right != null) || (p.right != null && q.right == null)) {
                return false;
            }

            if (p.left != null) {
                pStaCK.push(p.left);
            }
            if (p.right != null) {
                pStaCK.push(p.right);
            }
            if (q.left != null) {
                qStaCK.push(q.left);
            }
            if (q.right != null) {
                qStaCK.push(q.right);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().isSameTree(createTree(1, 2, 3), createTree(1, 2, 3)));
        System.out.println(new Solution2().isSameTree(createTree(1, 2), createTree(1, null, 2)));
        System.out.println(new Solution2().isSameTree(createTree(1, 2, 1), createTree(1, 1, 2)));
    }
}