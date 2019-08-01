package cn.lastwhisper.tree.huffmantree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 哈夫曼树
 * @author lastwhisper
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};

        Node root = createHuffmanTree(arr);

        preOrder(root);// 67,29,38,15,7,8,23,10,4,1,3,6,13
    }

    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树！");
        }
    }

    /**
     * 创建哈夫曼树，并返回root结点
     *
     * @param arr
     * @return cn.lastwhisper.tree.huffmantree.Node
     */
    public static Node createHuffmanTree(int[] arr) {

        List<Node> nodes = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            nodes.add(new Node(arr[i]));
        }

        while (nodes.size() > 1) {
            // 1）、将序列升序排序，每一个结点都可以看做是一颗最简单的二叉树
            Collections.sort(nodes);
            // 2）、找到权值最小的二叉树（结点）
            Node leftNode = nodes.get(0);
            // 2）、找到权值次小的二叉树（结点）
            Node rightNode = nodes.get(1);
            // 3）、将两个权值最小的二叉树构成一颗新的二叉树，新二叉树的权值为前面两个二叉树权值之和。
            Node node = new Node(leftNode.value + rightNode.value);
            node.left = leftNode;
            node.right = rightNode;

            // 4）、将新二叉树放入序列中，并移除两个权值最小的二叉树
            nodes.add(node);
            nodes.remove(leftNode);
            nodes.remove(rightNode);

        }
        return nodes.get(0);
    }

}

// 哈夫曼树结点
class Node implements Comparable<Node> {
    int value;// 权值
    Node left;//左子树
    Node right;//右子树

    public Node(int value) {
        this.value = value;
    }

    // 先序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        // 升序
        return this.value - o.value;
    }
}
