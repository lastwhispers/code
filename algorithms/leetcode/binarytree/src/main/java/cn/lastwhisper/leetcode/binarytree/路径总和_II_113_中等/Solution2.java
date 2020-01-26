package cn.lastwhisper.leetcode.binarytree.路径总和_II_113_中等;

import cn.lastwhisper.leetcode.common.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static cn.lastwhisper.leetcode.common.tree.TreeUtils.createTree;
import static cn.lastwhisper.leetcode.common.tree.TreeUtils.printLists;

class Solution2 {
    static class DTO {
        public TreeNode treeNode;
        public String path;
        public Integer sum;

        public DTO(TreeNode treeNode, String path, Integer sum) {
            this.treeNode = treeNode;
            this.path = path;
            this.sum = sum;
        }
    }

    /**
     * 题目地址：https://leetcode-cn.com/problems/path-sum-ii/
     * -------------------------------------------------------------------
     * 思考：路径总和(112)+二叉树的所有路劲(257)
     * -------------------------------------------------------------------
     * 思路：先序遍历-迭代
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<DTO> stack = new LinkedList<>();
        stack.push(new DTO(root, String.valueOf(root.val), root.val));

        while (!stack.isEmpty()) {
            DTO dto = stack.pop();
            root = dto.treeNode;
            String path = dto.path;
            Integer partSum = dto.sum;

            if (root.left == null && root.right == null && partSum == sum) {
                String[] arr = path.split(",");
                List<Integer> list = new ArrayList<>(arr.length);
                for (String a : arr) {
                    list.add(Integer.parseInt(a));
                }
                result.add(list);
            }

            if (root.left != null) {
                stack.push(new DTO(root.left, path + "," + root.left.val, partSum + root.left.val));
            }
            if (root.right != null) {
                stack.push(new DTO(root.right, path + "," + root.right.val, partSum + root.right.val));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        printLists(new Solution2().pathSum(createTree(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1), 22));
    }
}