package cn.lastwhisper.tree;

/**
 * 二叉树的遍历：先序、中序、后序
 * 二叉树的查找：查找指定节点
 * @author lastwhisper
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        // 创建节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode heroNode2 = new HeroNode(2, "吴用");
        HeroNode heroNode3 = new HeroNode(3, "卢俊义");
        HeroNode heroNode4 = new HeroNode(4, "林冲");
        HeroNode heroNode5 = new HeroNode(5, "关胜");
        HeroNode heroNode6 = new HeroNode(6, "花荣");

        // 手动创建二叉树
        BinaryTree binaryTree = new BinaryTree();
        root.setLeft(heroNode2);
        root.setRight(heroNode3);
        heroNode3.setRight(heroNode4);
        heroNode3.setLeft(heroNode5);
        heroNode4.setRight(heroNode6);
        binaryTree.setRoot(root);
        // 先序遍历
        System.out.println("===============先序遍历================");
        binaryTree.preOrder(root); // 1,2,3,5,4
        // 中序遍历
        System.out.println("===============中序遍历================");
        binaryTree.infixOrder(root); // 2,1,5,3,4
        // 后序遍历
        System.out.println("===============后序遍历================");
        binaryTree.postOrder(root); // 2,5,4,3,1
        // 先序遍历查找
        System.out.println("===============先序遍历查找================");
        System.out.println(binaryTree.preOrderSearch(5));
        System.out.println("===============中序遍历查找================");
        System.out.println(binaryTree.infixOrderSearch(5));
        System.out.println("===============后序遍历查找================");
        System.out.println(binaryTree.postOrderSearch(5));
        //System.out.println("===============清空子树下的no=3节点================");
        //binaryTree.clear(3);
        //binaryTree.preOrder(root);
        System.out.println("===============二叉树的最大高度================");
        System.out.println(binaryTree.height());
        System.out.println("===============二叉树的节点数================");
        System.out.println(binaryTree.size());
    }
}

// 二叉树
class BinaryTree {
    private HeroNode root;


    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 清空子树下的所有节点
    public void clear(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                root.clear(no);
            }
        } else {
            System.out.println("空树无法删除");
        }

    }

    //判断二叉树是否为空
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * 获取二叉树的高度
     *
     * @param
     * @return int
     */
    public int height() {
        return height(root);
    }

    /**
     * 递归遍历直到叶子结点
     *
     * @param node
     * @return int
     */
    public int height(HeroNode node) {
        if (node == null) {
            return 0;
        } else {
            //递归获取左子树高度
            int l = height(node.getLeft());
            //递归获取右子树高度
            int r = height(node.getRight());
            //高度应该算更高的一边，（+1是因为要算上自身这一层）
            return l > r ? l + 1 : r + 1;
        }
    }

    /**
     * 获取二叉树的节点数
     *
     * @param
     * @return int
     */
    public int size() {
        return size(root);
    }

    public int size(HeroNode node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + size(node.getLeft()) + size(node.getRight());
        }

    }
    // 返回某节点的父结点


    // 先序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    // 中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    // 后序遍历查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    /**
     * 先序遍历（递归）
     *  先访问根结点，再先序遍历左子树，再先序遍历右子树
     *
     * @param node
     */
    public void preOrder(HeroNode node) {
        if (node != null) {
            System.out.println(node);
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    /**
     * 中序遍历（递归）
     *  先遍历左子树，再访问根结点，再中序遍历右子树
     * @param node
     */
    public void infixOrder(HeroNode node) {
        if (node != null) {
            infixOrder(node.getLeft());
            System.out.println(node);
            infixOrder(node.getRight());
        }
    }

    /**
     * 后序遍历（递归）
     *  先遍历左子树，再后序遍历右子树，再访问根结点
     * @param node
     */
    public void postOrder(HeroNode node) {
        if (node != null) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            System.out.println(node);
        }
    }
}

// 树的节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 先序遍历查找节点
     *
     * @param no
     * @return cn.lastwhisper.tree.HeroNode
     */
    public HeroNode preOrderSearch(int no) {
        // 1）、判断当前结点是否为查找的结点
        if (this.no == no) {
            return this;
        }
        // 返回值
        HeroNode result = null;
        // 2）、递归左子树
        if (this.left != null) {
            result = this.left.preOrderSearch(no);
        }
        if (result != null) {
            return result;
        }
        // 3）、递归右子树
        if (this.right != null) {
            result = this.right.preOrderSearch(no);
        }
        return result;
    }

    /**
     * 中序遍历查找
     *
     * @param no
     * @return cn.lastwhisper.tree.HeroNode
     */
    public HeroNode infixOrderSearch(int no) {
        HeroNode result = null;
        // 1）、先递归左子树
        if (this.left != null) {
            result = this.left.infixOrderSearch(no);
        }
        if (result != null) {
            return result;
        }
        // 2）、判断当前结点是否为查找的结点
        if (this.no == no) {
            return this;
        }
        // 3）、递归右子树
        if (this.right != null) {
            result = this.right.infixOrderSearch(no);
        }
        return result;
    }

    // 后序遍历查找
    public HeroNode postOrderSearch(int no) {
        // 1）、递归遍历右子树
        HeroNode result = null;
        if (this.right != null) {
            result = this.right.postOrderSearch(no);
        }
        if (result != null) {
            return result;
        }
        // 2）、判断该结点是否是寻找的节点
        if (this.no == no) {
            return this;
        }
        // 3、递归遍历左子树
        if (this.left != null) {
            result = this.left.postOrderSearch(no);
        }
        return result;
    }

    // 递归删除节点
    public void clear(int no) {
        // 1）、判断当前结点的左结点是否满足条件
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        // 2）、判断当前结点的右结点是否满足条件
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        // 3）、当前结点的左右结点不满足条件，递归左结点
        if (this.left != null) {
            this.left.clear(no);
        }
        // 4）、当前结点的左右结点不满足条件，递归右结点
        if (this.right != null) {
            this.right.clear(no);
        }
    }


}
