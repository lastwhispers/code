package cn.lastwhisper.leetcode.binarytree.二叉树的所有路径_257_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static cn.lastwhisper.leetcode.common.tree.TreeUtils.createTree;
import static cn.lastwhisper.leetcode.common.tree.TreeUtils.printList;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/binary-tree-paths/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：先序遍历-迭代
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<Pair<TreeNode, String>> stack = new LinkedList<>();
        stack.push(new Pair<>(root, String.valueOf(root.val)));

        while (!stack.isEmpty()) {
            Pair<TreeNode, String> pair = stack.pop();
            root = pair.getKey();
            String path = pair.getValue();
            if (root.left == null && root.right == null) {
                result.add(path);
            }
            if (root.left != null) {
                stack.push(new Pair<>(root.left, path + "->" + root.left.val));
            }
            if (root.right != null) {
                stack.push(new Pair<>(root.right, path + "->" + root.right.val));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        printList(new Solution2().binaryTreePaths(createTree(1, 2, 3, null, 5)));
    }
}