package cn.lastwhisper.leetcode.binarytree.左叶子之和_404_简单;

import cn.lastwhisper.leetcode.common.tree.TreeNode;
import javafx.util.Pair;

import java.util.LinkedList;

import static cn.lastwhisper.leetcode.common.tree.TreeUtils.createTree;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/sum-of-left-leaves/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：将递归改成迭代
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<Pair<TreeNode, Boolean>> stack = new LinkedList<>();
        stack.push(new Pair<>(root, false));

        int sum = 0;
        Boolean flag;
        while (!stack.isEmpty()) {
            Pair<TreeNode, Boolean> pair = stack.pop();
            root = pair.getKey();
            flag = pair.getValue();
            if (flag && root.left == null && root.right == null) {
                sum += root.val;
            }
            if (root.left != null) {
                stack.push(new Pair<>(root.left, true));
            }
            if (root.right != null) {
                stack.push(new Pair<>(root.right, false));
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().sumOfLeftLeaves(createTree(1, 2, 3, 4, 5)));
        //System.out.println(new Solution1().sumOfLeftLeaves(createTree(1, null, 2)));
        //System.out.println(new Solution1().sumOfLeftLeaves(createTree(3, 9, 20, null, null, 15, 7)));
    }
}