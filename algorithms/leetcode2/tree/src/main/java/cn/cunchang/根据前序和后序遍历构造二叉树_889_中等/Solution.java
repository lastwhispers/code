package cn.cunchang.根据前序和后序遍历构造二叉树_889_中等;

import cn.cunchang.tree.TreeNode;

/**
 * @author cunchang
 * @date 2022/6/21 11:59 PM
 */
class Solution {
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        return constructFromPrePost0(preorder, 0, preorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode constructFromPrePost0(int[] preorder, int preLeft, int preRight, int[] postorder, int postLeft, int postRight) {
        if (preLeft > preRight) {
            return null;
        }
        // 构造根节点
        TreeNode root = new TreeNode(preorder[preLeft]);
        // 把先序遍历的第二个值当做左子树


        return null;
    }
}
