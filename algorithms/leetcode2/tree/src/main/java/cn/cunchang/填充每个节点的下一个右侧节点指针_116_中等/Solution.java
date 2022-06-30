package cn.cunchang.填充每个节点的下一个右侧节点指针_116_中等;

/**
 * @author cunchang
 * @date 2022/6/20 6:33 PM
 */
public class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        connect2(root.left, root.right);
        return root;
    }

    public void connect2(Node node1, Node node2) {
        if (node1 == null || node2 == null) {
            return;
        }
        node1.next = node2;

        connect2(node1.left,node1.right);
        connect2(node2.left,node2.right);
        // 跨节点相连
        connect2(node1.right,node2.left);
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
