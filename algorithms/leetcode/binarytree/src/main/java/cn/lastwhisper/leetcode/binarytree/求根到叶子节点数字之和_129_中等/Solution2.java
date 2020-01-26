package cn.lastwhisper.leetcode.binarytree.求根到叶子节点数字之和_129_中等;

import cn.lastwhisper.leetcode.common.tree.TreeNode;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static cn.lastwhisper.leetcode.common.tree.TreeUtils.createTree;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
     * -------------------------------------------------------------------
     * 思考：二叉树的所有路径_257
     * -------------------------------------------------------------------
     * 思路：先序遍历-迭代
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }

        List<String> result = new ArrayList<>();
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
                stack.push(new Pair<>(root.left, path + root.left.val));
            }
            if (root.right != null) {
                stack.push(new Pair<>(root.right, path + root.right.val));
            }
        }

        int sum = 0;
        for (String s : result) {
            sum += Integer.parseInt(s);
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().sumNumbers(createTree(1, 2, 3)));
        System.out.println(new Solution2().sumNumbers(createTree(4,9,0,5,1)));
    }
}