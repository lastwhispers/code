package cn.lastwhisper.offer.interview_8;

/**
 * 面试题8：二叉树的下一个结点
 * 思路：
 *      1.若一个结点有右子树，则它下一个结点就是它右子树的最左子结点
 *      2.若一个结点无右子树
 *          2.1若该结点是它父结点的左子结点，则它下一个结点就是它的父结点
 *          2.2若该结点是它父结点的右子结点，则沿着父结点的指针向上遍历，
 *              直到找到一个是它父结点的左子结点的结点，找不到说明没有下一个结点
 * 核心思想：有右子树，找自己右子树的最左子结点
 *          无右子树，
 * @author cn.lastwhisper
 */
public class Solution_8 {
    public static void main(String[] args) {
        Solution_8 solution_8 = new Solution_8();
        TreeNode treeNode = solution_8.construct();
        //TreeNode nextNode = solution_8.GetNext(treeNode);
        TreeNode nextNode = solution_8.GetNext1(treeNode);
        System.out.println(nextNode.val);
    }

    public TreeNode GetNext(TreeNode pNode) {
        if (pNode == null) {
            System.out.print("结点为null ");
            return null;
        }
        if (pNode.right != null) {
            pNode = pNode.right;
            while (pNode.left != null)
                pNode = pNode.left;
            return pNode;
        }
        while (pNode.parent != null) {
            if (pNode == pNode.parent.left)
                return pNode.parent;
            pNode = pNode.parent;
        }
        return null;
    }

    private TreeNode GetNext1(TreeNode pNode) {
        // 数据校验
        if (pNode == null) {
            return null;
        }
        //该结点有右子树
        if (pNode.right != null) {
            //若一个结点有右子树，则它下一个结点就是它右子树的最左子结点
            pNode = pNode.right;
            while (pNode.left != null) {
                pNode = pNode.left;
            }
            return pNode;
        }
        else {
            TreeNode parentNode = pNode.parent;
            if (parentNode.left == pNode) {
                //若该结点是它父结点的左子结点，则它下一个结点就是它的父结点
                return parentNode;
            } else if (parentNode.right == pNode) {
                //若该结点是它父结点的右子结点，则沿着父结点的指针向上遍历，
                //直到找到一个是它父结点的左子结点的结点，找不到说明没有下一个结点
                parentNode = pNode;
                while (parentNode.parent != null) {
                    if (parentNode.parent.left == parentNode) {
                        return parentNode.parent;
                    }
                    parentNode = parentNode.parent;
                }
            }
        }
        return null;
    }

    private TreeNode construct() {
        TreeNode<String> treeNode1 = new TreeNode<String>("a");
        TreeNode<String> treeNode2 = new TreeNode<String>("b");
        TreeNode<String> treeNode3 = new TreeNode<String>("c");
        TreeNode<String> treeNode4 = new TreeNode<String>("d");
        TreeNode<String> treeNode5 = new TreeNode<String>("e");
        TreeNode<String> treeNode6 = new TreeNode<String>("f");
        TreeNode<String> treeNode7 = new TreeNode<String>("g");
        TreeNode<String> treeNode8 = new TreeNode<String>("h");
        TreeNode<String> treeNode9 = new TreeNode<String>("i");
        // a,b,c
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.parent = treeNode1;
        treeNode3.parent = treeNode1;
        // b,d,e
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode4.parent = treeNode2;
        treeNode5.parent = treeNode2;
        // c,f,g
        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;
        treeNode6.parent = treeNode3;
        treeNode7.parent = treeNode3;
        // e,h,i
        treeNode5.left = treeNode8;
        treeNode5.right = treeNode9;
        treeNode8.parent = treeNode5;
        treeNode9.parent = treeNode5;
        return treeNode1;//情景一
        //return treeNode4;//情景二
        //return treeNode9;//情景三
    }
}

class TreeNode<T> {
    T val;
    TreeNode left;
    TreeNode right;
    TreeNode parent;

    public TreeNode(T val) {
        this.val = val;
    }
}
