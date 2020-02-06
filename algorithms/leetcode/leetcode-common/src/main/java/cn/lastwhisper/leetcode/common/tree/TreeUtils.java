package cn.lastwhisper.leetcode.common.tree;

import javafx.util.Pair;

import java.util.*;

import static cn.lastwhisper.leetcode.common.print.PrintUtils.printLists;

/**
 * 树相关工具类
 * @author lastwhisper
 * @date 1/16/2020
 */
public class TreeUtils {

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
     * https://blog.csdn.net/lenfranky/article/details/84444816
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
     * 相同的树
     *
     *
     * 输入:       1         1
     *           / \       / \
     *          2   3     2   3
     *
     *         [1,2,3],   [1,2,3]
     *
     * 输出: true
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(p);
        queue.add(q);
        while (!queue.isEmpty()) {
            p = queue.poll();
            q = queue.poll();

            if (p == null && q == null) continue;
            if (p == null || q == null) return false;
            if (p.val != q.val) return false;

            queue.add(p.left);
            queue.add(q.left);
            queue.add(p.right);
            queue.add(q.right);
        }
        return true;
    }

    /**
     * 完全二叉树的左子树或右子树的高度
     *
     * @param root 根节点的左子树或右子树
     * @return int 高度
     */
    private int countLevel(TreeNode root) {
        int level = 0;
        while (root != null) {
            level++;
            root = root.left;
        }
        return level;
    }

    /**
     * 所有节点之和
     *
     * @param root
     * @return int
     */
    public int sumOfLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return sumOfLeaves(root.left) + sumOfLeaves(root.right) + root.val;
    }

    /**
     * 二叉树根节点至叶子节点的最高高度
     *
     * @param root 根节点
     * @return int 高度
     */
    public int maxDepth(TreeNode root) {
        LinkedList<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        if (root != null) {
            stack.push(new Pair<>(root, 0));
        }
        int maxDepth = 0;
        // stack模拟递归系统栈
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> pair = stack.poll();
            root = pair.getKey();
            Integer height = pair.getValue();
            // dfs过程中更新最大深度
            maxDepth = Math.max(maxDepth, height);
            if (root != null) {
                stack.push(new Pair<>(root.right, height + 1));
                stack.push(new Pair<>(root.left, height + 1));
            }
        }
        return maxDepth;
    }

    /**
     * 二叉树根节点至叶子节点的最小高度
     *
     * @param root 根节点
     * @return int 高度
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, 1));

        // 当前深度
        int currentDepth = 0;
        Pair<TreeNode, Integer> pair = null;
        while (!queue.isEmpty()) {
            pair = queue.poll();
            root = pair.getKey();
            currentDepth = pair.getValue();
            // 找到层中第一个叶子节点
            if (root.left == null && root.right == null) {
                break;
            }
            if (root.left != null) {
                queue.add(new Pair<>(root.left, currentDepth + 1));
            }
            if (root.right != null) {
                queue.add(new Pair<>(root.right, currentDepth + 1));
            }

        }
        return currentDepth;
    }

    /**
     * 反转二叉树
     *
     * @param root 根节点
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        // 翻转当前节点的左右子节点
        TreeNode tempNode = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(tempNode);
        return root;
    }

    /**
     * 输出二叉树层次遍历的结果
     */
    public static void printLevelOrder(TreeNode root) {
        printLists(levelOrder(root));
    }

    public static void main(String[] args) {
        printLevelOrder(createTree(1, null, 2, 3));
        //printLevelOrder(createTree(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1));
        //printLevelOrder(createTree(1,2,3,null,4,5,null));
    }
}
