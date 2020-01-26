package cn.lastwhisper.leetcode.binarytree.路径总和_III_437_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import static cn.lastwhisper.leetcode.common.tree.TreeUtils.createTree;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/path-sum-iii/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：先序遍历-递归
     *  找每一个节点的pathSum
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n^2)
     */
    // 从root找到路径和等于sum的数量
    public int pathSum(TreeNode root, int sum) {
        if(root==null){
            return 0;
        }
        int res = findPathSum(root, sum);
        res+=pathSum(root.left,sum);
        res+=pathSum(root.right,sum);
        return res;
    }

    // 从node找到路径和等于sum的数量
    public int findPathSum(TreeNode node, int sum) {
        if (node == null) {
            return 0;
        }
        int res = 0;
        if (node.val == sum) {
            res++;
        }
        res += findPathSum(node.left, sum - node.val);
        res += findPathSum(node.right, sum - node.val);

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution1().pathSum(createTree(10, 5, -3, 3, 2, null, 11, 3, -2, null, 1), 8));
    }
}