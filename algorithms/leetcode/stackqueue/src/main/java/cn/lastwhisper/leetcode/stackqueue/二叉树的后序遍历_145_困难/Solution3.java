package cn.lastwhisper.leetcode.stackqueue.二叉树的后序遍历_145_困难;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import java.util.*;

import static cn.lastwhisper.leetcode.common.print.PrintUtil.printList;
import static cn.lastwhisper.leetcode.common.tree.TreeUtil.createTree;

class Solution3 {
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
        LinkedList<TreeNode> stack = new LinkedList<>();
        Set<TreeNode> set = new HashSet<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null && !set.contains(cur)) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            // 回到上一个节点
            //右子树为空或者第二次来到这里
            if (cur.right == null || set.contains(cur)) {
                list.add(cur.val);
                set.add(cur);
                stack.pop();//将当前节点弹出
                if (stack.isEmpty()) {
                    return list;
                }
                //转到右子树，这种情况对应于右子树为空的情况
                cur = stack.peek();
            } else {
                //从左子树过来，加到 set 中，转到右子树
                set.add(cur);
            }
            cur = cur.right;
        }
        return list;
    }

    public static void main(String[] args) {
        printList(new Solution3().postorderTraversal(createTree(1, null, 2, 3)));
    }
}