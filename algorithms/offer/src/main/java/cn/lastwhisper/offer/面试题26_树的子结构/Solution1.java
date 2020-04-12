package cn.lastwhisper.offer.面试题26_树的子结构;

import cn.lastwhisper.leetcode.common.tree.TreeNode;
import cn.lastwhisper.leetcode.common.tree.TreeUtil;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：写个对比方法
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        // A、B树都为空 true
        if (A == null && B == null) {
            return true;
        }
        // A或B为空 false
        if (A == null || B == null) {
            return false;
        }
        return compare(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    // 负责对比两颗树
    private boolean compare(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        // A不为空，B为空说明前面都对上了
        if (root2 == null) {
            return true;
        }
        // B不为空，A为空说明对个屁
        if (root1 == null) {
            return false;
        }
        // 相等时继续对比
        if (root1.val == root2.val) {
            return compare(root1.left, root2.left) && compare(root1.right, root2.right);
        }
        return false;
    }

    public static void main(String[] args) {
        //TreeNode A = TreeUtils.createTree(1, 2, 3);
        //TreeNode B = TreeUtils.createTree(3, 1);
        TreeNode A = TreeUtil.createTree(3, 4, 5, 1, 2);
        TreeNode B = TreeUtil.createTree(4, 1);
        System.out.println(new Solution1().isSubStructure(A, B));
    }
}