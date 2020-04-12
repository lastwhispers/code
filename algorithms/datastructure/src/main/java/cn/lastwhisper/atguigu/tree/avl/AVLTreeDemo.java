package cn.lastwhisper.atguigu.tree.avl;

/**
 * 平衡二叉树
 * @author cn.lastwhisper
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        // 测试左旋转
        //int[] arr = {4, 3, 6, 5, 7, 8};
        // 测试右旋转
        //int[] arr = {10, 12, 8, 9, 7, 6};
        // 测试双旋转
        int[] arr = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历");
        avlTree.infixOrder();
        Node avlTreeRoot = avlTree.getRoot();
        System.out.println("当前树的高度：" + avlTreeRoot.height());
        System.out.println("当前树的左子树高度：" + avlTreeRoot.left.height());
        System.out.println("当前树的右子树高度：" + avlTreeRoot.right.height());
        System.out.println("当前根节点：" + avlTreeRoot);
    }
}

/**
 * 二叉排序树
 */
class AVLTree {
    // 根节点
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    // 查找节点
    public Node search(int value) {
        if (root != null) {
            return root.search(value);
        } else {
            return null;
        }
    }

    // 查找父节点
    public Node searchParent(int value) {
        if (root != null) {
            return root.searchParent(value);
        } else {
            return null;
        }
    }

    /**
     * 找到该节点左子树最小值，并删除这个最小值节点
     *
     * @param node
     * @return int 最小值节点
     */
    public int delRightTreeMin(Node node) {
        Node temp = node;
        // 找到右子节点的最小值节点
        while (temp.left != null) {
            temp = temp.left;
        }
        delNode(temp.value);
        return temp.value;
    }

    /**
     * 找到该节点左子树最大值，并删除这个最大值节点
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


    // 根据value删除节点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            // 待删除节点
            Node targetNode = search(value);
            // 没有找到待删除节点
            if (targetNode == null) {
                return;
            }
            // 待删除节点的父节点
            Node parentNode = searchParent(value);
            // 1. 情况一：只有一个根节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            // 2. 情况二：删除的节点是叶子节点（左右节点为空）
            if (targetNode.left == null && targetNode.right == null) {
                // 2.1 确定targetNode是parent的左子节点还是右子节点；
                if (parentNode.left != null && parentNode.left.value == targetNode.value) {
                    // 左子节点
                    parentNode.left = null;
                } else if (parentNode.right != null && parentNode.right.value == targetNode.value) {
                    // 右子节点
                    parentNode.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                // 3. 情况四：删除带有两个子节点的targetNode
                // 找到targetNode右子树的最小值，删除最小值节点，并将最小值赋值给targetNode
                //targetNode.value = delRightTreeMin(targetNode.right);
                targetNode.value = delLeftTreeMax(targetNode.left);
            } else {
                // 4. 情况三：删除只有一个子节点的targetNode
                // 4.1 确定targetNode的子节点是左子节点还是右子节点
                // targetNode的子节点是左子节点
                if (targetNode.left != null) {
                    if (parentNode != null) {
                        // 4.2 确定targetNode在parentNode上是左子节点还是右子节点
                        if (parentNode.left != null && parentNode.left.value == targetNode.value) {
                            // targetNode在parentNode上是左子节点
                            parentNode.left = targetNode.left;
                        } else {
                            // targetNode在parentNode上是右子节点
                            parentNode.right = targetNode.left;
                        }
                    } else {
                        // targetNode为根节点时，parentNode==null
                        // root = targetNode.left;即为删除target节点
                        root = targetNode.left;
                    }
                } else {
                    if (parentNode != null) {
                        //targetNode的子节点是右子节点
                        if (parentNode.left != null && parentNode.left.value == targetNode.value) {
                            // targetNode在parentNode上是左子节点
                            parentNode.left = targetNode.right;
                        } else {
                            // targetNode在parentNode上是右子节点
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

    // 添加节点
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
     * 以当前某节点进行右旋转，降低左子树的高度
     */
    public void rightRotate() {
        // 1. 创建一个新节点，值等于当前节点的值
        Node newNode = new Node(this.value);
        // 2. 将新节点的右子树设置为当前节点的右子树
        newNode.right = this.right;
        // 3. 将新节点的左子树设置为当前节点的左子树的右子树（当前节点左子树最大的节点）
        newNode.left = this.left.right;
        // 4. 将当前节点的值设置为当前节点的左子树（节点）的值
        this.value = this.left.value;
        // 5. 将当前节点的左子树设置为当前节点左子树的左子树
        this.left = this.left.left;
        // 6. 将当期节点的右子树设置为新节点
        this.right = newNode;
    }

    /**
     * 以当前某节点进行左旋转，降低右子树的高度
     */
    public void leftRotate() {
        // 1. 创建一个新节点，值等于当前节点的值
        Node newNode = new Node(this.value);
        // 2. 将新节点的左子树设置为当前节点的左子树
        newNode.left = this.left;
        // 3. 将新节点的右子树设置为当前节点右子树的左子树
        newNode.right = this.right.left;
        // 4. 将当前节点的值设置为当前节点右子树的值
        this.value = this.right.value;
        // 5. 将当前节点的右子树设置为当前节点右子树的右子树
        this.right = this.right.right;
        // 6. 将当前节点左子树设置为新节点
        this.left = newNode;
    }

    /**
     * @return int 当前某节点左子树的高度
     */
    public int leftHeight() {
        if (this.left != null) {
            return this.left.height();
        } else {
            return 0;
        }
    }

    /**
     * @return int 当前某节点右子树的高度
     */
    public int rightHeight() {
        if (this.right != null) {
            return this.right.height();
        } else {
            return 0;
        }
    }

    /**
     * @return int 当前某节点的高度
     */
    public int height() {
        int leftHeight = this.left == null ? 0 : this.left.height();
        int rightHeight = this.right == null ? 0 : this.right.height();
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 查找节点
     *
     * @param value 节点的value
     * @return cn.cn.lastwhisper.tree.binarysorttree.Node
     */
    public Node search(int value) {
        // 1. 判断当前节点是否是查找的节点
        if (this.value == value) {
            return this;
        } else if (this.value > value && this.left != null) {
            // 2.1 当前节点的value大于value，往左边寻找
            return this.left.search(value);
        } else if (this.value < value && this.right != null) {
            // 2.2 当前节点的value小于value，往右边寻找
            return this.right.search(value);
        } else {
            // 3. this.left&&this.right==null，找不到该节点
            return null;
        }
    }

    // 查找节点的父节点
    public Node searchParent(int value) {
        // 1. 判断当前节点是否是查找节点的父节点
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (this.value > value && this.left != null) {
                // 2.1 当前节点的value大于value，往左边寻找
                return this.left.searchParent(value);
            } else if (this.value < value && this.right != null) {
                // 2.2 当前节点的value小于value，往右边寻找
                return this.right.searchParent(value);
            } else {
                // 3. this.left&&this.right==null，找不到该节点的父节点
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
     * 以自身为根节点按规则添加左右子树
     *   规则：对于非叶子节点p，左节点小于p，右节点大于p
     *   满足AVL规则！！！
     * @param node
     * @return void
     */
    public void add(Node node) {
        // 1. 如果待添加节点为空直接返回
        if (node == null) {
            return;
        }
        if (this.value > node.value) {
            // 2.1 如果根节点大于待添加节点，将待添加节点放到左边
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            // 2.2 如果根节点小于待添加节点，将待添加节点放到右边
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }

        // 当添加完节点后，满足(右子树的高度-左子树的高度) > 1 ，进行左旋转
        if (this.rightHeight() - this.leftHeight() > 1) {
            // 如果当前节点的右子树的左子树的高度大于当前节点的右子树的右子树的高度
            if(this.right!=null&&(this.right.leftHeight()>this.right.rightHeight())){
                // 先当前节点的右子树进行右旋转，再左旋转
                this.right.rightRotate();
            }
            leftRotate();
            return;
        }

        // 当添加完节点后，满足(左子树的高度-右子树的高度) > 1 ，进行右旋转
        if (this.leftHeight() - this.rightHeight() > 1) {
            // 如果当前节点的左子树的右子树的高度大于当前节点的左子树的左子树的高度
            if (this.left != null && (this.left.rightHeight() > this.left.leftHeight())) {
                // 先对当前节点的左子树进行左旋转，再右旋转
                this.left.leftRotate();
            }
            rightRotate();
        }

    }


    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

}