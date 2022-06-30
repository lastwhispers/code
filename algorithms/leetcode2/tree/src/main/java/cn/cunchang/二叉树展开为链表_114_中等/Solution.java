package cn.cunchang.二叉树展开为链表_114_中等;

import cn.cunchang.tree.TreeNode;
import cn.cunchang.tree.TreeUtil;

/**
 * @author cunchang
 * @date 2022/6/20 6:41 PM
 */
public class Solution {

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        flatten(root.left);
        flatten(root.right);
        // 原先左子树
        TreeNode left = root.left;
        // 原先右子树
        TreeNode right = root.right;
        // 将左子树作为当前节点的右子树
        root.left = null;
        root.right = left;
        // 将当前右子树(原先的左子树)的末端指向原先的右子树
        TreeNode p = root;//这里为什么是root？left行不行；left也可以，但是left可能为空需要判空
        while (p.right!=null){
            p = p.right;
        }
        //
        p.right = right;
    }

    public static void main(String[] args) {
        TreeNode node = TreeUtil.createTree(1, 2, 5, 3, 4, null, 6);
        new Solution().flatten(node);
        TreeUtil.printLevelOrder(node);
    }
}
