package cn.lastwhisper.tree.traversal;

import java.util.Stack;

public class Solution {
    // 二叉树后序遍历的两种易写的非递归写法 - 我要吃栗子的文章 - 知乎
    //https://zhuanlan.zhihu.com/p/80578741
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        root.left = treeNode2;
        root.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;

        Solution solution = new Solution();
        //solution.preOrderTraversal(root);
        //solution.postOrderTraversal1(root);
        solution.postOrderTraversal2(root);

    }

    /**
     * 非递归先序遍历，遍历顺序：根左右
     * 需要使用一个栈
     */
    public void preOrderTraversal(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !s.isEmpty()) {
            while (cur != null) { // 经过并访问所有左节点
                System.out.println(cur.val);
                s.push(cur);
                cur = cur.left;
            }
            while (!s.isEmpty() && cur == null) cur = s.pop().right; // 自底向上找栈中节点的第一个非空右子树
        }
    }

    /**
     * 非递归后序遍历，遍历顺序：左右根
     * 使用两个栈
     * 参考非递归先序遍历的“根左右”顺序，使用栈反过来
     */
    public void postOrderTraversal1(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        Stack<Integer> res = new Stack<>(); // 保存节点序列的栈
        TreeNode cur = root;
        while (cur != null || !s.isEmpty()) {
            while (cur != null) {
                res.push(cur.val);
                s.push(cur);
                cur = cur.right; // 修改处
            }
            if (!s.isEmpty()) {
                cur = s.pop();
                cur = cur.left; // 修改处
            }
        }
        while (!res.isEmpty()) System.out.println(res.pop());// 获取倒序的根右左序列
    }

    /**
     * 非递归后序遍历，遍历顺序：左右根
     * 使用一个栈
     */
    public void postOrderTraversal2(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;  // 用于记录上一次访问的节点
        while(cur!=null || !s.isEmpty()) {
            while(cur!=null) {
                s.push(cur);
                cur = cur.left;
            }
            if(!s.isEmpty()) {
                cur = s.pop();
                if(cur.right==null || pre==cur.right) { // 访问节点的条件
                    System.out.println(cur.val); // 访问
                    pre = cur; // 这一步是记录上一次访问的节点
                    cur = null; // 此处为了跳过下一次循环的访问左子节点的过程，直接进入栈的弹出阶段，因为但凡在栈中的节点，它们的左子节点都肯定被经过且已放入栈中。
                }
                else { // 不访问节点的条件
                    s.push(cur); // 将已弹出的根节点放回栈中
                    cur = cur.right; // 经过右子节点
                }
            }
        }
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}