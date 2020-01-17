package cn.lastwhisper.leetcode.stackqueue.二叉树的前序遍历_144_中等;

import cn.lastwhisper.leetcode.stackqueue.TreeNode;
import cn.lastwhisper.leetcode.stackqueue.TreeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class SolutionCommand {
    static class Command {
        String s;
        TreeNode node;

        Command(String s, TreeNode node) {
            this.s = s;
            this.node = node;
        }
    }

    /**
     * 题目地址：https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：遍历
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root==null){
            return result;
        }

        Stack<Command> stack = new Stack<>();
        stack.push(new Command("go", root));
        while (!stack.empty()) {
            Command command = stack.pop();
            if (command.s.equals("print")) {
                result.add(command.node.val);
            } else {
                if (command.node.right != null) {
                    stack.push(new Command("go", command.node.right));
                }
                if (command.node.left != null) {
                    stack.push(new Command("go", command.node.left));
                }
                stack.push(new Command("print", command.node));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode tree = TreeUtils.createTraversalTree();

        new SolutionCommand().preorderTraversal(tree).forEach(node -> {
            System.out.print(node + ",");
        });
    }
}

