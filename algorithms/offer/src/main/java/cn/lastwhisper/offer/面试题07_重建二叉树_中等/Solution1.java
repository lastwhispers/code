package cn.lastwhisper.offer.面试题07_重建二叉树_中等;

import cn.lastwhisper.leetcode.common.tree.TreeNode;
import cn.lastwhisper.leetcode.common.tree.TreeUtils;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     *  1、找到前序root在中序的位置下标 inRootIndex
     *  2、根据(1)算出前序root需要跳过多少左子树结点到下一个root leftLength
     *  3、根据inRootIndex和leftLength可以找到左右子树
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || preorder.length != inorder.length) {
            return null;
        }
        return construct(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode construct(int[] pre, int[] in, int pStart, int pEnd, int iStart, int iEnd) {
        // 先序遍历数组中的第一个元素：根节点
        TreeNode root = new TreeNode(pre[pStart]);
        // 找到前序root在中序的位置
        int inRootIndex = iStart;
        while (root.val != in[inRootIndex]) {
            inRootIndex++;
        }
        // 前序的下一个root结点在哪里？跳过当前左子树结点
        // 需要跳过左子树的多少颗结点？
        int leftLength = inRootIndex - iStart;

        // 有左子树
        if (leftLength > 0) {
            root.left = construct(pre, in, pStart + 1, pStart + leftLength, iStart, inRootIndex - 1);
        }
        // 前序root在中序的位置下标不超过iEnd
        if (inRootIndex < iEnd) {
            root.right = construct(pre, in, pStart + leftLength + 1, pEnd, inRootIndex + 1, iEnd);
        }

        return root;
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7}, inorder = {9, 3, 15, 20, 7};
        TreeUtils.printLevelOrder(new Solution1().buildTree(preorder, inorder));
    }
}