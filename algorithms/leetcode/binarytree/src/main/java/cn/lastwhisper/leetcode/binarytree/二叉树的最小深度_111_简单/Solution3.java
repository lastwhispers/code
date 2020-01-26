package cn.lastwhisper.leetcode.binarytree.二叉树的最小深度_111_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;
import cn.lastwhisper.leetcode.common.tree.TreeUtils;
import javafx.util.Pair;

import java.util.LinkedList;

class Solution3 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
     * -------------------------------------------------------------------
     * 思考：从根节点到叶子节点的路径上的节点数量。BFS按照树的层次去迭代，
     *  第一个访问到的叶子就是最小深度的节点，这样就不要遍历所有的节点了。
     * -------------------------------------------------------------------
     * 思路：BFS迭代
     * -------------------------------------------------------------------
     * 时间复杂度：最多访问n/2个节点，O(n)
     * 空间复杂度：最多队列中保存n/2个节点，O(n)
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, 1));

        // 当前深度
        int currentDepth = 0;
        Pair<TreeNode, Integer> pair = null;
        while (!queue.isEmpty()) {
            pair = queue.poll();
            root = pair.getKey();
            currentDepth = pair.getValue();
            // 找到层中第一个叶子节点
            if (root.left == null && root.right == null) {
                break;
            }
            if (root.left != null) {
                queue.add(new Pair<>(root.left, currentDepth + 1));
            }
            if (root.right != null) {
                queue.add(new Pair<>(root.right, currentDepth + 1));
            }

        }
        return currentDepth;
    }

    public static void main(String[] args) {
        /*
         *     3
         *    / \
         *   9  20
         *     /  \
         *    15   7
         */
        System.out.println(new Solution2().minDepth(TreeUtils.createTree(3,9,20,null,null,15,7)));
    }
}
