package cn.cunchang.从前序与中序遍历序列构造二叉树_105_中等;

import cn.cunchang.array.ArrayUtil;
import cn.cunchang.tree.TreeNode;
import cn.cunchang.tree.TreeUtil;

/**
 * @author cunchang
 * @date 2022/6/20 6:41 PM
 */
class Solution {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree0(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    public TreeNode buildTree0(int[] preorder, int preLeft, int preRight,
                               int[] inorder, int inLeft, int inRight) {
        // 递归终止
        if (preLeft > preRight) {
            return null;
        }

        // 1、preLeft是preorder的根节点，对根节点进行构造
        TreeNode root = new TreeNode(preorder[preLeft]);
        // 2、根节点在inorder的下标为index
        int inOrderIndex = 0;
        for (int i = inLeft; i <= inRight; i++) {
            if (inorder[i] == preorder[preLeft]) {
                inOrderIndex = i;
                break;
            }
        }
        // 3、根节点的左子树节点个数leftCount = index-inLeft，根节点左子树在preorder的范围[preLeft+1,preLeft+leftCount],
        //    根节点已经构造过了，所以从preLeft+1开始，至左子树个数preLeft+leftCount结束；
        //  在inorder的范围[inLeft,index-1]
        int leftCount = inOrderIndex - inLeft;
        root.left = buildTree0(preorder, preLeft + 1, preLeft + leftCount,
                inorder, inLeft, inOrderIndex - 1);
        // 4、根节点右子树在preorder的范围[preLeft+leftCount+1,preRight],在inorder的范围[index+1,inRight]
        root.right = buildTree0(preorder, preLeft + leftCount + 1, preRight,
                inorder, inOrderIndex + 1, inRight);
        return root;
    }

    public static void main(String[] args) {
        TreeUtil.printLevelOrder(new Solution().buildTree(
                ArrayUtil.createArrays(3, 9, 20, 15, 7), ArrayUtil.createArrays(9, 3, 15, 20, 7)));
    }
}
