package cn.cunchang.删点成林_1110_简单;

import cn.cunchang.array.ArrayUtil;
import cn.cunchang.tree.TreeNode;
import cn.cunchang.tree.TreeUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    List<TreeNode> result = new ArrayList<>();

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> deleteSet = new HashSet<>();
        for (int deleteNum : to_delete) {
            deleteSet.add(deleteNum);
        }
        // 根节点不需要删除，就加入到返回值，需要删除就不管了
        if (!deleteSet.contains(root.val)) {
            result.add(root);
        }
        delNodes0(root, deleteSet);
        return result;
    }

    public boolean delNodes0(TreeNode root, Set<Integer> deleteSet) {
        if (root == null) {
            return false;
        }

        boolean leftDelete = delNodes0(root.left, deleteSet);
        if (leftDelete) {
            root.left = null;
        }
        boolean rightDelete = delNodes0(root.right, deleteSet);
        if (rightDelete) {
            root.right = null;
        }
        if (deleteSet.contains(root.val)) {
            // 当前节点是需要删除的节点
            // 那么当前节点的左节点，不为空的话，就是一个单独的森林
            if (root.left != null) {
                result.add(root.left);
            }
            // 当前节点的右节点，不为空的话，就是一个单独的森林
            if (root.right != null) {
                result.add(root.right);
            }
            root.left = null;
            root.right = null;
            // 通知上层节点把自己删了
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        List<TreeNode> treeNodeList = new Solution().delNodes(TreeUtil.createTree(1, 2, 3, 4, 5, 6, 7),
                ArrayUtil.createArrays(3));
        for (TreeNode treeNode : treeNodeList) {
            TreeUtil.printLevelOrder(treeNode);
        }
    }
}