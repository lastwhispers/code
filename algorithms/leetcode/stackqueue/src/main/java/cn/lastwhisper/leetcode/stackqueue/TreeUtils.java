package cn.lastwhisper.leetcode.stackqueue;

/**
 * 树相关工具类
 * @author lastwhisper
 * @date 1/16/2020
 */
public class TreeUtils {

    public static TreeNode createTree() {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);

        root.right = node1;
        node1.left = node2;

        return root;
    }
    //public static TreeNode createTree(int... arr) {
    //    // 二叉树的层数
    //    int plies = (int) (Math.log(arr.length) / Math.log(2));
    //    TreeNode root = new TreeNode(arr[0]);
    //    for (int i = 2; i < plies; i++) {
    //
    //    }
    //    return null;
    //}

    public static void main(String[] args) {
        System.out.println();

    }
}
