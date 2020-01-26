package cn.lastwhisper.leetcode.stackqueue.二叉树的后序遍历_145_困难;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static cn.lastwhisper.leetcode.common.tree.TreeUtils.createTree;
import static cn.lastwhisper.leetcode.common.tree.TreeUtils.printList;

class Solution4 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：后序遍历-迭代
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode last = null;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode temp = stack.peek();
            //是否变到右子树
            if (temp.right != null && temp.right != last) {
                cur = temp.right;
            } else {
                list.add(temp.val);
                last = temp;
                stack.pop();
            }

        }
        return list;
    }

    public static void main(String[] args) {
        printList(new Solution4().postorderTraversal(createTree(1, null, 2, 3)));
    }
}