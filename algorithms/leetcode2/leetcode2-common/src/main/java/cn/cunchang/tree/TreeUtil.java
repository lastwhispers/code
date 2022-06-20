package cn.cunchang.tree;

import cn.cunchang.print.PrintUtil;
import javafx.util.Pair;

import java.util.*;


/**
 * 树相关工具类
 * @author lastwhisper
 * @date 1/16/2020
 */
public class TreeUtil {

    /**
     * 根据数组创建二叉树
     *  一、[5,4,8,11,null,13,4,7,2,null,null,null,1]
     *               5
     *              / \
     *             4   8
     *            /   / \
     *           11  13  4
     *          /  \      \
     *         7    2      1
     * 二、[1,null,2,3]
     *            1
     *             \
     *              2
     *             /
     *            3
     * 参考 https://blog.csdn.net/lenfranky/article/details/84444816
     * @param array 数组
     */
    public static TreeNode createTree(Integer... array) {
        if (array.length == 0) return new TreeNode(0);
        Deque<TreeNode> nodeQueue = new LinkedList<>();
        // 创建一个根节点
        TreeNode root = new TreeNode(array[0]);
        nodeQueue.offer(root);
        TreeNode current;
        // 记录当前行节点的数量（注意不一定是2的幂，而是上一行中非空节点的数量乘2）
        int lineNodeNum = 2;
        // 记录当前行中数字在数组中的开始位置
        int startIndex = 1;
        // 记录数组中剩余的元素的数量
        int restLength = array.length - 1;

        while (restLength > 0) {
            // 只有最后一行可以不满，其余行必须是满的
//            // 若输入的数组的数量是错误的，直接跳出程序
//            if (restLength < lineNodeNum) {
//                System.out.println("Wrong Input!");
//                return new TreeNode(0);
//            }
            for (int i = startIndex; i < startIndex + lineNodeNum; i = i + 2) {
                // 说明已经将nums中的数字用完，此时应停止遍历，并可以直接返回root
                if (i == array.length) return root;
                current = nodeQueue.poll();
                if (array[i] != null) {
                    current.left = new TreeNode(array[i]);
                    nodeQueue.offer(current.left);
                }
                // 同上，说明已经将nums中的数字用完，此时应停止遍历，并可以直接返回root
                if (i + 1 == array.length) return root;
                if (array[i + 1] != null) {
                    current.right = new TreeNode(array[i + 1]);
                    nodeQueue.offer(current.right);
                }
            }
            startIndex += lineNodeNum;
            restLength -= lineNodeNum;
            lineNodeNum = nodeQueue.size() * 2;
        }

        return root;
    }

    /**
     * 二叉树层次遍历
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 遍历每层的数据
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while (size > 0) {
                root = queue.poll();
                list.add(root.val);
                if (root.left != null) {
                    queue.add(root.left);
                }
                if (root.right != null) {
                    queue.add(root.right);
                }
                size--;
            }
            result.add(list);
        }
        return result;
    }

    /**
     * 输出二叉树层次遍历的结果
     */
    public static void printLevelOrder(TreeNode root) {
        PrintUtil.printLists(levelOrder(root));
    }

    public static void main(String[] args) {
//        printLevelOrder(createTree(1, null, 2, 3));
        printLevelOrder(createTree(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1));
        //printLevelOrder(createTree(1,2,3,null,4,5,null));
    }
}
