package cn.lastwhisper.leetcode.stackqueue;

import java.util.List;

/**
 * 树相关工具类
 * @author lastwhisper
 * @date 1/16/2020
 */
public class TreeUtils {

    public static TreeNode createTraversalTree() {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);

        root.right = node1;
        node1.left = node2;

        return root;
    }

    public static TreeNode create102Tree() {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);

        root.left = node1;
        root.right = node2;

        node2.left = node3;
        node2.right = node4;

        return root;
    }

    public static TreeNode create103Tree() {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        root.left = node2;
        root.right = node3;

        node2.left = node4;
        node3.right = node5;

        return root;
    }

    public static TreeNode create199Tree() {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        root.left = node2;
        root.right = node3;

        node2.right = node5;
        node3.right = node4;

        return root;
    }


    public static void printLists(List<List<Integer>> lists) {
        System.out.println("[");
        for (List<Integer> list : lists) {
            System.out.print("\t[");
            for (Integer i : list) {
                System.out.print(i + ",");
            }
            System.out.print("]\n");
        }
        System.out.println("]");
    }

    //public static TreeNode createTraversalTree(int... arr) {
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
