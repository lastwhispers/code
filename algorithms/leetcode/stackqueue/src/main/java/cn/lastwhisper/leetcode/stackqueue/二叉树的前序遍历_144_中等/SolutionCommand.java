package cn.lastwhisper.leetcode.stackqueue.二叉树的前序遍历_144_中等;

import cn.lastwhisper.leetcode.common.tree.TreeNode;
import cn.lastwhisper.leetcode.common.tree.TreeUtil;

import java.util.LinkedList;
import java.util.List;

class SolutionCommand {
    enum Type {
        go,
        print,
        ;
    }

    static class Command {
        Type type;
        TreeNode node;

        Command(Type type, TreeNode node) {
            this.type = type;
            this.node = node;
        }
    }

    /**
     * 题目地址：https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：先序遍历、迭代，使用Stack模拟系统栈
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }

        LinkedList<Command> stack = new LinkedList<>();
        stack.push(new Command(Type.go, root));
        while (!stack.isEmpty()) {
            Command command = stack.pop();
            if (command.type == Type.print) {
                result.add(command.node.val);
            } else {
                // stack先进先出，入栈顺序right-left-node
                // 迭代顺序node-left-right
                if (command.node.right != null) {
                    stack.push(new Command(Type.go, command.node.right));
                }
                if (command.node.left != null) {
                    stack.push(new Command(Type.go, command.node.left));
                }
                stack.push(new Command(Type.print, command.node));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode tree = TreeUtil.createTree();

        new SolutionCommand().preorderTraversal(tree).forEach(node -> {
            System.out.print(node + ",");
        });
    }
}

