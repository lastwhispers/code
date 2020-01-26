package cn.lastwhisper.common.tree.bst;

import cn.lastwhisper.common.TreeNode;

/**
 * 二叉搜索树（Binary Search Tree，简称 BST）：一个二叉树中，
 *  任意节点的值要大于等于左子树所有节点的值，且要小于等于右边子树的所有节点的值。
 * https://leetcode-cn.com/problems/same-tree/solution/xie-shu-suan-fa-de-tao-lu-kuang-jia-by-wei-lai-bu-/
 * @author lastwhisper
 * @date 1/21/2020
 */
public class BSTTree {

    /**
     * 判断 BST 的合法性
     *
     * @param root
     * @return boolean
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) return true;
        if (min != null && root.val <= min.val) return false;
        if (max != null && root.val >= max.val) return false;
        return isValidBST(root.left, min, root)
                && isValidBST(root.right, root, max);
    }

    /**
     * 在 BST 中查找一个数是否存在
     *
     * @param root
     * @param target
     * @return boolean
     */
    public boolean isInBST(TreeNode root, int target) {
        if (root == null) return false;
        if (root.val == target)
            return true;
        if (root.val < target)
            return isInBST(root.right, target);
        if (root.val > target)
            return isInBST(root.left, target);
        return false;
    }

    /**
     * 在 BST 中插入一个数
     *
     * @param root
     * @param val
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // 找到空位置插入新节点
        if (root == null) return new TreeNode(val);
        // if (root.val == val)
        //     BST 中一般不会插入已存在元素
        if (root.val < val)
            root.right = insertIntoBST(root.right, val);
        if (root.val > val)
            root.left = insertIntoBST(root.left, val);
        return root;
    }

    /**
     * 在 BST 中删除一个节点key，删除节点的同时不能破坏 BST 的性质
     *  情况一：key恰好是末端节点，两个子节点都为空，直接删除
     *  情况二：key只有一个非空子节点，那么它要让这个孩子接替自己的位置
     *  情况三：key有两个子节点，找到左子树中最大的那个节点，或者右子树中最小的那个节点来接替自己。
     * @param root
     * @param key
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root.val == key) {
            // 情况一：key恰好是末端节点
            if (root.left == null && root.right == null)
                return null;
            // 情况二：key只有一个非空子节点
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            // 情况三：key有两个子节点
            // root.left != null && root.right != null
            // 找到右子树的最小节点
            TreeNode minNode = getMin(root.right);
            // 把 root 改成 minNode
            root.val = minNode.val;
            // 转而去删除 minNode
            root.right = deleteNode(root.right, minNode.val);
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            // root.val < key
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    /**
     * 找到BST中的最小值
     *
     * @param node
     */
    public TreeNode getMin(TreeNode node) {
        // BST 最左边的就是最小的
        while (node.left != null) node = node.left;
        return node;
    }

    /**
     * 找到BST中的最大值
     *
     * @param node
     */
    public TreeNode getMax(TreeNode node) {
        // BST 最左边的就是最小的
        while (node.right != null) node = node.right;
        return node;
    }
}
