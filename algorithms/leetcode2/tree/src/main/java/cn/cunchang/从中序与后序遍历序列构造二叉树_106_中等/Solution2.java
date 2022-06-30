package cn.cunchang.从中序与后序遍历序列构造二叉树_106_中等;

import cn.cunchang.array.ArrayUtil;
import cn.cunchang.tree.TreeNode;
import cn.cunchang.tree.TreeUtil;

import java.util.HashMap;

/**
 * @author cunchang
 * @date 2022/6/21 8:42 PM
 */
class Solution2 {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        HashMap<Integer, Integer> inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        return buildTree0(inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1, inorderIndexMap);
    }

    public TreeNode buildTree0(int[] inorder, int inLeft, int inRight,
                               int[] postorder, int postLeft, int postRight, HashMap<Integer, Integer> inorderIndexMap) {
        if (postLeft > postRight) {
            return null;
        }
        // 找到根节点postRight，并构造根节点
        TreeNode treeNode = new TreeNode(postorder[postRight]);
        // 找到根节点在inorder的下标index
        int postOrderIndex = inorderIndexMap.get(postorder[postRight]);
//        for (int i = inLeft; i <= inRight; i++) {
//            if (postorder[postRight] == inorder[i]) {
//                postOrderIndex = i;
//                break;
//            }
//        }
        // 计算根节点右子树的节点数
        int leftCount = inRight - postOrderIndex;
        // 递归构造根节点的右子树
        // 的右子树在在inorder[postOrderIndex+1,inRight]，postorder[postRight-leftCount,postRight-1]
        treeNode.right = buildTree0(inorder, postOrderIndex + 1, inRight,
                postorder, postRight - leftCount, postRight - 1, inorderIndexMap);
        // 递归构造根节点的左子树
        // 左子树在在inorder[inLeft,postOrderIndex-1]，postorder[postLeft,leftCount-1]
        treeNode.left = buildTree0(inorder, inLeft, postOrderIndex - 1,
                postorder, postLeft, postRight - leftCount - 1, inorderIndexMap);
        return treeNode;
    }

    public static void main(String[] args) {
        TreeUtil.printLevelOrder(new Solution2().buildTree(
                ArrayUtil.createArrays(9, 3, 15, 20, 7), ArrayUtil.createArrays(9, 15, 7, 20, 3)));
    }
}