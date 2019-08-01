package cn.lastwhisper.tree.binarysorttree;

/**
 * 二叉排序树
 * @author lastwhisper
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历");
        binarySortTree.infixOrder();

        //binarySortTree.delNode(2); // 删除叶子结点
        //binarySortTree.delNode(1); // 删除只有一个子结点
        //binarySortTree.delNode(7); // 删除有两个子结点

        binarySortTree.delNode(2);
        binarySortTree.delNode(5);
        binarySortTree.delNode(9);
        binarySortTree.delNode(12);
        binarySortTree.delNode(7);
        binarySortTree.delNode(3);
        binarySortTree.delNode(1);
        binarySortTree.delNode(10);
        // 会报错
        //binarySortTree.delNode(10);
        //binarySortTree.delNode(1);

        System.out.println("删除结点后，中序遍历");
        binarySortTree.infixOrder();
    }

}

/**
 * 二叉排序树
 */
class BinarySortTree {
    // 根结点
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    // 查找结点
    public Node search(int value) {
        if (root != null) {
            return root.search(value);
        } else {
            return null;
        }
    }

    // 查找父结点
    public Node searchParent(int value) {
        if (root != null) {
            return root.searchParent(value);
        } else {
            return null;
        }
    }

    /**
     * 找到该结点左子树最小值，并删除这个最小值结点
     *
     * @param node
     * @return int 最小值结点
     */
    public int delRightTreeMin(Node node) {
        Node temp = node;
        // 找到右子结点的最小值结点
        while (temp.left != null) {
            temp = temp.left;
        }
        delNode(temp.value);
        return temp.value;
    }

    /**
     * 找到该结点左子树最大值，并删除这个最大值结点
     *
     * @param node
     * @return int 最大值
     */
    public int delLeftTreeMax(Node node) {
        Node temp = node;
        while (temp.right != null) {
            temp = temp.right;
        }
        delNode(temp.value);
        return temp.value;
    }


    // 根据value删除结点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            // 待删除结点
            Node targetNode = search(value);
            // 没有找到待删除结点
            if (targetNode == null) {
                return;
            }
            // 待删除结点的父结点
            Node parentNode = searchParent(value);
            // 1. 情况一：只有一个根结点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            // 2. 情况二：删除的结点是叶子结点（左右结点为空）
            if (targetNode.left == null && targetNode.right == null) {
                // 2.1 确定targetNode是parent的左子结点还是右子结点；
                if (parentNode.left != null && parentNode.left.value == targetNode.value) {
                    // 左子结点
                    parentNode.left = null;
                } else if (parentNode.right != null && parentNode.right.value == targetNode.value) {
                    // 右子结点
                    parentNode.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                // 3. 情况四：删除带有两个子结点的targetNode
                // 找到targetNode右子树的最小值，删除最小值结点，并将最小值赋值给targetNode
                //targetNode.value = delRightTreeMin(targetNode.right);
                targetNode.value = delLeftTreeMax(targetNode.left);
            } else {
                // 4. 情况三：待删除结点只有一个子结点
                // 4.1 确定targetNode的子结点是左子结点还是右子结点
                // targetNode的子结点是左子结点
                if (targetNode.left != null) {
                    if (parentNode != null) {
                        // 4.2 确定targetNode在parentNode上是左子结点还是右子结点
                        if (parentNode.left != null && parentNode.left.value == targetNode.value) {
                            // targetNode在parentNode上是左子结点
                            parentNode.left = targetNode.left;
                        } else {
                            // targetNode在parentNode上是右子结点
                            parentNode.right = targetNode.left;
                        }
                    } else {
                        // targetNode为根结点时，parentNode==null
                        // root = targetNode.left;即为删除target结点
                        root = targetNode.left;
                    }
                } else {
                    if (parentNode != null) {
                        //targetNode的子结点是右子结点
                        if (parentNode.left != null && parentNode.left.value == targetNode.value) {
                            // targetNode在parentNode上是左子结点
                            parentNode.left = targetNode.right;
                        } else {
                            // targetNode在parentNode上是右子结点
                            parentNode.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }

            }


        }

    }

    // 中序
    public void infixOrder() {
        if (root == null) {
            System.out.println("树空");
        } else {
            root.infixOrder();
        }

    }

    // 添加结点
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

}


class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 查找结点
     *
     * @param value 结点的value
     * @return cn.lastwhisper.tree.binarysorttree.Node
     */
    public Node search(int value) {
        // 1. 判断当前结点是否是查找的结点
        if (this.value == value) {
            return this;
        } else if (this.value > value && this.left != null) {
            // 2.1 当前结点的value大于value，往左边寻找
            return this.left.search(value);
        } else if (this.value < value && this.right != null) {
            // 2.2 当前结点的value小于value，往右边寻找
            return this.right.search(value);
        } else {
            // 3. this.left&&this.right==null，找不到该结点
            return null;
        }
    }

    // 查找结点的父结点
    public Node searchParent(int value) {
        // 1. 判断当前结点是否是查找结点的父结点
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (this.value > value && this.left != null) {
                // 2.1 当前结点的value大于value，往左边寻找
                return this.left.searchParent(value);
            } else if (this.value < value && this.right != null) {
                // 2.2 当前结点的value小于value，往右边寻找
                return this.right.searchParent(value);
            } else {
                // 3. this.left&&this.right==null，找不到该结点的父结点
                return null;
            }
        }
    }

    // 中序
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 以自身为根结点按规则添加左右子树
     *   规则：对于非叶子结点p，左结点小于p，右结点大于p
     *
     * @param node
     * @return void
     */
    public void add(Node node) {
        // 1. 如果待添加结点为空直接返回
        if (node == null) {
            return;
        }
        if (this.value > node.value) {
            // 2.1 如果根结点大于待添加结点，将待添加结点放到左边
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            // 2.2 如果根结点小于待添加结点，将待添加结点放到右边
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }


    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

}