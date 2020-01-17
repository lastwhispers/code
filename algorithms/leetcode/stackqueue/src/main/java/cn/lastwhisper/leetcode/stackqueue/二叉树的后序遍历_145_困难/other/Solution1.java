package cn.lastwhisper.leetcode.stackqueue.二叉树的后序遍历_145_困难.other;/// Source : https://leetcode.com/problems/binary-tree-postorder-traversal/description/
/// Author : liuyubobobo
/// Time   : 2018-05-30

import cn.lastwhisper.leetcode.stackqueue.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// Non-Recursive
// Using a tag to record whether the node has been visited
//
// Time Complexity: O(n), n is the node number in the tree
// Space Complexity: O(h), h is the height of the tree
public class Solution1 {

    private class TagNode {
        TreeNode node;
        boolean isFirst;

        TagNode(TreeNode node) {
            this.node = node;
            this.isFirst = false;
        }
    }

    ;

    public List<Integer> postorderTraversal(TreeNode root) {

        ArrayList<Integer> res = new ArrayList<Integer>();
        if (root == null)
            return res;

        Stack<TagNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.empty()) {

            while (cur != null) {
                stack.push(new TagNode(cur));
                cur = cur.left;
            }

            TagNode tagNode = stack.pop();
            cur = tagNode.node;
            if (!tagNode.isFirst) {
                tagNode.isFirst = true;
                stack.push(tagNode);
                cur = cur.right;
            } else {
                res.add(cur.val);
                cur = null;
            }
        }
        return res;
    }
}
