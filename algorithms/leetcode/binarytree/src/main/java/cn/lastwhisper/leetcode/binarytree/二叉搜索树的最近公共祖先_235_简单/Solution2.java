package cn.lastwhisper.leetcode.binarytree.二叉搜索树的最近公共祖先_235_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import static cn.lastwhisper.leetcode.common.tree.TreeUtil.createTree;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：迭代-找到分隔点
     *  二叉树排序树：根大于左子树小于右子树
     *  情况一：pq同时大于root，说明pq的最近公共祖先在右子树
     *  情况一：pq同时小于root，说明pq的最近公共祖先在左子树
     *  情况一：pq不同时大于或小于root，说明root为最近公共祖先
     *          pq分别在左右子树，root为最近公共祖先
     *          p在root上，q在左或右子树，p=root为最近公共祖先
     *          q在root上，p在左或右子树，q=root为最近公共祖先
     * -------------------------------------------------------------------
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root!=null){
            if (p.val > root.val && q.val > root.val) {
                root=root.right;
            } else if (p.val < root.val && q.val < root.val) {
                root=root.left;
            }else {
                return root;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        System.out.println(new Solution2().lowestCommonAncestor(
                createTree(6, 2, 8, 0, 4, 7, 9, null, null, 3, 5), new TreeNode(2), new TreeNode(8)).val);
        System.out.println(new Solution2().lowestCommonAncestor(
                createTree(6, 2, 8, 0, 4, 7, 9, null, null, 3, 5), new TreeNode(2), new TreeNode(4)).val);
    }
}