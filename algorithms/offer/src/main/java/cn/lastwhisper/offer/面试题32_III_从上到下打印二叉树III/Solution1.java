package cn.lastwhisper.offer.面试题32_III_从上到下打印二叉树III;

import cn.lastwhisper.leetcode.common.print.PrintUtil;
import cn.lastwhisper.leetcode.common.tree.TreeNode;
import cn.lastwhisper.leetcode.common.tree.TreeUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：z字层次遍历
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        // 特判
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();//记录每层的元素个数
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                root = queue.poll();
                // result代表层级
                if (result.size() % 2 == 0) {//奇数倒序
                    list.add(root.val);
                } else {
                    list.push(root.val);
                }
                if (root.left != null) queue.add(root.left);
                if (root.right != null) queue.add(root.right);
            }
            result.add(list);
        }
        return result;
    }

    public static void main(String[] args) {
        PrintUtil.printLists(new Solution1().levelOrder(TreeUtil.createTree(3, 9, 20, null, null, 15, 7)));
    }
}