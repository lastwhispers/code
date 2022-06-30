package cn.cunchang.翻转二叉树_226_简单;

import cn.cunchang.tree.TreeNode;

/**
 * @author cunchang
 * @date 2022/6/20 6:28 PM
 */
class Solution {
    public TreeNode invertTree(TreeNode root) {
        invertTree2(root);
        return root;
    }

    /**
     * 通过遍历解决
     */
    public void invertTree2(TreeNode root) {
        if (root == null) {
            return;
        }
        // 前序交换
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        invertTree2(root.left);
        invertTree2(root.right);
    }

//    public void invertTree2(TreeNode root) {
//        if (root == null) {
//            return;
//        }
//        invertTree2(root.left);
//        invertTree2(root.right);
//      // 后序交换
//        TreeNode tmp = root.left;
//        root.left = root.right;
//        root.right = tmp;
//    }
}