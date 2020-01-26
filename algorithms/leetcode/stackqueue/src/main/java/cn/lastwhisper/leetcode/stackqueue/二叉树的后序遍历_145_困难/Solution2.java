package cn.lastwhisper.leetcode.stackqueue.二叉树的后序遍历_145_困难;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

import static cn.lastwhisper.leetcode.common.tree.TreeUtils.createTree;
import static cn.lastwhisper.leetcode.common.tree.TreeUtils.printList;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：后序遍历-迭代（前序遍历的变种）
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> outStack = new LinkedList<>();
        stack.push(root);
        // 根-右-左顺序访问，然后逆序输出
        while (!stack.isEmpty()) {
            root = stack.pop();
            outStack.push(root.val);

            if (root.left != null) {
                stack.push(root.left);
            }
            if (root.right != null) {
                stack.push(root.right);
            }
        }

        while (!outStack.isEmpty()){
            result.add(outStack.poll());
        }
        return result;
    }

    public static void main(String[] args) {
        printList(new Solution2().postorderTraversal(createTree(1, null, 2, 3)));
    }
}