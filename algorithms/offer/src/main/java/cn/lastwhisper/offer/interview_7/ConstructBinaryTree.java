package cn.lastwhisper.offer.interview_7;

// 题目：输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输
// 入的前序遍历和中序遍历的结果中都不含重复的数字。例如输入前序遍历序列{1,
// 2, 4, 7, 3, 5, 6, 8}和中序遍历序列{4, 7, 2, 1, 5, 3, 8, 6}，则重建出
// 二叉树并输出它的头结点。
public class ConstructBinaryTree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length == 0 || in.length == 0 || pre.length != in.length) {
            throw new RuntimeException("数组不符合规范！");
        }
        return construct(pre, in, 0, pre.length - 1, 0, in.length - 1);
    }

    /**
     *  根据先序数组的起始位置获得root,
     *  再根据root.val算出root在中序数组中的位置,得到root的左子树和右子树
     *  再把左子树和右子树进行重复操作
     * @param pre    前序遍历数组
     * @param in     中序遍历数组
     * @param pStart 前序遍历起始位置
     * @param pEnd   前序遍历结束位置
     * @param iStart 中序遍历起始位置
     * @param iEnd   中序遍历结束位置
     */
    public TreeNode construct(int[] pre, int[] in, int pStart, int pEnd, int iStart, int iEnd) {
        // 先序遍历数组中的第一个元素：根结点
        TreeNode root = new TreeNode(pre[pStart]);
        // 第一次：pStart=0、pEnd=7;iStart=0、iEnd=7
        // 第二次：pStart=1、pEnd=3;iStart=0、iEnd=2
        // 第三次：pStart=2、pEnd=3;iStart=0、iEnd=1
        // 第四次：pStart=3、pEnd=3;iStart=1、iEnd=1
        // 先序数组和中序数组中只有一个结点
        if (pStart == pEnd && iStart == iEnd) {
            if (pre[pStart] != in[iStart])
                //throw new FileNotFoundException("数组不符合规范！");
                throw new RuntimeException("数组不符合规范！");
            return root;
        }
        int index = iStart; // 用于记录中序遍历序列中根结点的位置
        //第一次：index=3,中序遍历对应root下标
        //第二次：index=2,中序遍历对应root下标
        //第二次：index=0,中序遍历对应root下标
        //根据先序遍历数组得到的根结点,找到该根结点对应中序遍历数组的下标
        while (root.val != in[index] && index <= iEnd) {
            index++;
        }
        if (index > iEnd)
            throw new RuntimeException("数组不符合规范！");
        //第一次：leftLength=3,当前root的左子树有三个结点
        //第二次：leftLength=2,当前root的左子树有两个结点
        //第三次：leftLength=0,当前root的左子树有没有结点
        int leftLength = index - iStart;//中序遍历中当前root的左子树结点个数
        //构建左子树
        if (leftLength > 0) {
            // [pStart + 1...pStart + leftLength]是先序遍历左子树的范围
            // [iStart..index-1]是中序遍历左子树的范围
            root.left = construct(pre, in, pStart + 1, pStart + leftLength, iStart, index - 1);
        }
        // 构建右子树
        if (leftLength < iEnd - iStart) {
            // [pStart + leftLength + 1...pEnd]是先序遍历右子树的范围
            // [index + 1..iEnd]是中序遍历右子树的范围
            root.right = construct(pre, in, pStart + leftLength + 1, pEnd, index + 1, iEnd);
        }
        return root;
    }

    private void preOrderTraverse(TreeNode node) {
        if (node == null)
            return;
        System.out.print(node.val);
        preOrderTraverse(node.left);
        preOrderTraverse(node.right);
    }

    private void inOrderTraverse(TreeNode node) {
        if (node == null)
            return;
        inOrderTraverse(node.left);
        System.out.print(node.val);
        inOrderTraverse(node.right);
    }

    /**
     * 正常二叉树
     */
    public void test1() {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode root = reConstructBinaryTree(pre, in);
        System.out.print("test1:");
        preOrderTraverse(root);
        System.out.print("\t");
        inOrderTraverse(root);
        System.out.println();
    }

    /**
     * 左斜树
     */
    public void test2() {
        int[] pre = {1, 2, 3, 4, 5};
        int[] in = {5, 4, 3, 2, 1};
        TreeNode root = reConstructBinaryTree(pre, in);
        System.out.print("test2:");
        preOrderTraverse(root);
        System.out.print("\t");
        inOrderTraverse(root);
        System.out.println();
    }

    /**
     * 右斜树
     */
    public void test3() {
        int[] pre = {1, 2, 3, 4, 5};
        int[] in = {1, 2, 3, 4, 5};
        TreeNode root = reConstructBinaryTree(pre, in);
        System.out.print("test3:");
        preOrderTraverse(root);
        System.out.print("\t");
        inOrderTraverse(root);
        System.out.println();
    }

    /**
     * 单个结点
     */
    public void test4() {
        int[] pre = {1};
        int[] in = {1};
        TreeNode root = reConstructBinaryTree(pre, in);
        System.out.print("test4:");
        preOrderTraverse(root);
        System.out.print("\t");
        inOrderTraverse(root);
        System.out.println();
    }

    /**
     * 数组为空
     */
    public void test5() {
        int[] pre = {};
        int[] in = {};
        TreeNode root = reConstructBinaryTree(pre, in);
        System.out.print("test5:");
        preOrderTraverse(root);
        System.out.print("\t");
        inOrderTraverse(root);
        System.out.println();
    }

    public static void main(String[] args) {
        ConstructBinaryTree demo = new ConstructBinaryTree();
        demo.test1();
        //demo.test2();
        //demo.test3();
        //demo.test4();
        //demo.test5();
    }
}