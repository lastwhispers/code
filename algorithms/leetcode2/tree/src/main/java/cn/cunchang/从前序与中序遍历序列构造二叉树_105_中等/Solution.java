package cn.cunchang.从前序与中序遍历序列构造二叉树_105_中等;

import cn.cunchang.array.ArrayUtil;
import cn.cunchang.tree.TreeNode;
import cn.cunchang.tree.TreeUtil;

import java.util.HashMap;
import java.util.Map;

class Solution {
    private Map<Integer, Integer> inorderIndexMap;

    public TreeNode myBuildTree(int[] preorder, int preorderLeft, int preorderRight, int inorderLeft) {
        if (preorderLeft > preorderRight) {
            return null;
        }
        // 第一个节点是根节点
        TreeNode root = new TreeNode(preorder[preorderLeft]);
        // 在中序遍历中定位根节点
        Integer inorderRootIndex = inorderIndexMap.get(preorder[preorderLeft]);
        // 得到左子树中的节点数目
        int leftSize = inorderRootIndex - inorderLeft;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 leftSize」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, preorderLeft + 1,
                preorderLeft + leftSize, inorderLeft);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+左子树节点数目+1 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, preorderLeft + leftSize + 1,
                preorderRight, inorderRootIndex + 1);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        inorderIndexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, 0, n - 1, 0);
    }

    public static void main(String[] args) {
        TreeUtil.printLevelOrder(new Solution().buildTree(
                ArrayUtil.createArrays(3, 9, 20, 15, 7), ArrayUtil.createArrays(9, 3, 15, 20, 7)));
    }
}
