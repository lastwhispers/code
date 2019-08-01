package cn.lastwhisper.tree;

/**
 * 线索化二叉树
 * @author lastwhisper
 */
public class ThreadingBinaryTreeDemo {
    public static void main(String[] args) {
        // 创建结点
        ThreadingHeroNode root = new ThreadingHeroNode(1, "宋江");
        ThreadingHeroNode heroNode2 = new ThreadingHeroNode(3, "吴用");
        ThreadingHeroNode heroNode3 = new ThreadingHeroNode(6, "卢俊义");
        ThreadingHeroNode heroNode4 = new ThreadingHeroNode(8, "林冲");
        ThreadingHeroNode heroNode5 = new ThreadingHeroNode(10, "关胜");
        ThreadingHeroNode heroNode6 = new ThreadingHeroNode(14, "花荣");

        // 手动创建线索二叉树
        ThreadingBinaryTree ThreadingBinaryTree = new ThreadingBinaryTree();
        root.setLeft(heroNode2);
        root.setRight(heroNode3);
        heroNode2.setLeft(heroNode4);
        heroNode2.setRight(heroNode5);
        heroNode3.setLeft(heroNode6);
        ThreadingBinaryTree.setRoot(root);
        System.out.println("===============先序线索化二叉树================");
        ThreadingBinaryTree.preOrderThreading();
        //System.out.println("===============中序线索化二叉树================");
        //ThreadingBinaryTree.infixOrderThreading();
        System.out.println("===============前驱、后继结点================");
        ThreadingHeroNode heroNode5Left = heroNode5.getLeft();// 3
        ThreadingHeroNode heroNode5Right = heroNode5.getRight(); //
        System.out.println("10号结点的前驱：" + heroNode5Left + "\n10号结点的后继：" + heroNode5Right);
        System.out.println("===============先序遍历线索化二叉树================");
        ThreadingBinaryTree.preOrder();
        //System.out.println("===============中序遍历线索化二叉树================");
        //ThreadingBinaryTree.infixOrder();
    }
}

// 线索化二叉树
class ThreadingBinaryTree {
    private ThreadingHeroNode root;
    private ThreadingHeroNode preNode;


    public ThreadingHeroNode getRoot() {
        return root;
    }

    public void setRoot(ThreadingHeroNode root) {
        this.root = root;
    }

    // 构建先序线索化二叉树
    public void preOrderThreading() {
        this.preOrderThreading(root);
    }

    // 构建中序线索化二叉树
    public void infixOrderThreading() {
        this.infixOrderThreading(root);
    }

    /**
     * 先序遍历先序线索化二叉树
     *
     */
    public void preOrder() {
        ThreadingHeroNode node = root;
        while (node != null) {
            System.out.println(node);
            // 非线索化结点直接输出
            while (node.getLeftType() == 0) {
                node = node.getLeft();
                System.out.println(node);
            }
            // 遍历带有线索化的结点，遍历结束时会输出一个非线索化结点
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            // 记录非线索化结点右子树
            node = node.getRight();
        }
    }

    /**
     * 中序遍历中序线索化二叉树
     *
     */
    public void infixOrder() {
        ThreadingHeroNode node = root;
        while (node != null) {
            // 找到type==1的线索化结点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);
            // 遍历带有线索化的结点，遍历结束时会输出一个非线索化结点
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            // 记录非线索化结点右子树
            node = node.getRight();
        }
    }

    /**
     * 构建先序线索化二叉树
     *
     * @param node 当前需要线索化的结点
     */
    public void preOrderThreading(ThreadingHeroNode node) {
        // 数据校验
        if (node == null) {
            return;
        }
        // 处理当前结点的前驱结点
        // 如果当前结点的左子树为空，说明出现了空指针域，需要构建线索前驱结点的线索
        if (node.getLeft() == null) {
            // 设置当前结点的前驱节点
            node.setLeft(preNode);
            node.setLeftType(1);
        }
        // 处理前一个结点的后继结点
        // 如果前一个结点的左子树为空，说明出现了空指针域，需要构建线索后继结点的线索
        if (preNode != null && preNode.getRight() == null) {
            // 设置前一个结点的后继结点为当前结点
            preNode.setRight(node);
            preNode.setRightType(1);
        }
        // 每处理一个当前结点，就让当前结点是下一个结点的前驱节点
        preNode = node;
        // type==0表示link
        if (node.getLeftType() == 0) {
            // 线索化左子树
            preOrderThreading(node.getLeft());
        }
        if (node.getRightType() == 0) {
            // 线索化右子树
            preOrderThreading(node.getRight());
        }

    }

    /**
     * 构建中序线索化二叉树
     *
     * @param node 当前需要线索化的结点
     */
    public void infixOrderThreading(ThreadingHeroNode node) {
        // 数据校验
        if (node == null) {
            return;
        }

        // 1）、先线索化左子树
        infixOrderThreading(node.getLeft());
        // 2）、处理当前结点的前驱结点
        // 如果当前结点的左子树为空，说明出现了空指针域，需要构建线索前驱结点的线索
        if (node.getLeft() == null) {
            // 设置当前结点的前驱节点
            node.setLeft(preNode);
            node.setLeftType(1);
        }
        // 3）、处理前一个结点的后继结点
        // 如果前一个结点的左子树为空，说明出现了空指针域，需要构建线索后继结点的线索
        if (preNode != null && preNode.getRight() == null) {
            // 设置前一个结点的后继结点为当前结点
            preNode.setRight(node);
            preNode.setRightType(1);
        }
        // 每处理一个当前结点，就让当前结点是下一个结点的前驱节点
        preNode = node;

        // 4）、线索化右子树
        infixOrderThreading(node.getRight());
    }
}

// 树的结点
class ThreadingHeroNode {
    private int no;
    private String name;
    private ThreadingHeroNode left;
    private ThreadingHeroNode right;

    private int leftType; // leftType==0指向左子树，等于1指向前驱结点
    private int rightType;// rightType==0指向右子树，等于1指向后继结点

    public ThreadingHeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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

    public ThreadingHeroNode getLeft() {
        return left;
    }

    public void setLeft(ThreadingHeroNode left) {
        this.left = left;
    }

    public ThreadingHeroNode getRight() {
        return right;
    }

    public void setRight(ThreadingHeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }


}
