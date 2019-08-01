package cn.lastwhisper.tree;

import cn.lastwhisper.ArrayUtil;

/**
 * @author lastwhisper
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {

        int[] arr = ArrayUtil.generateArrByOrder(7);
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        System.out.println("先序：");
        arrBinaryTree.preOrder(0);//1 2 4 5 3 6 7
        System.out.println();
        System.out.println("中序：");
        arrBinaryTree.infixOrder(0);
        System.out.println();
        System.out.println("后序：");
        arrBinaryTree.postOrder(0);
    }
}

/**
 * 顺序存储二叉树基本思想：
 *  将二叉树顺序存储到数组中，并能按照二叉树的规则遍历（先序、中序、后序）
 */
class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    // 先序遍历
    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空");
            return;
        }
        System.out.print(arr[index] + " ");
        if ((2 * index + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        if ((2 * index + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    // 中序遍历
    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空");
            return;
        }
        if ((2 * index + 1) < arr.length) {
            infixOrder(2 * index + 1);
        }
        System.out.print(arr[index] + " ");
        if ((2 * index + 2) < arr.length) {
            infixOrder(2 * index + 2);
        }
    }

    // 后序遍历
    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空");
            return;
        }
        if ((2 * index + 1) < arr.length) {
            postOrder(2 * index + 1);
        }
        if ((2 * index + 2) < arr.length) {
            postOrder(2 * index + 2);
        }
        System.out.print(arr[index] + " ");
    }

}