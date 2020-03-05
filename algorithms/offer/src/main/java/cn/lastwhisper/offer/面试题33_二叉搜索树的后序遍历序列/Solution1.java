package cn.lastwhisper.offer.面试题33_二叉搜索树的后序遍历序列;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null || postorder.length == 0) {
            return true;
        }
        return process(postorder, 0, postorder.length - 1);
    }

    public boolean process(int[] postorder, int left, int right) {
        if (left >= right) {
            return true;
        }
        int i = right - 1;
        int root = postorder[right];
        while (i >= left && postorder[i] > root) {
            i--;
        }
        for (int j = left; j <= i; j++) {
            if (postorder[j] > root) {
                return false;
            }
        }
        return process(postorder, left, i) && process(postorder, i + 1, right - 1);
    }

    public static void main(String[] args) {
        //int[] nums = new int[]{1, 6, 3, 2, 5};
        int[] nums = new int[]{1, 3, 2, 6, 5};
        System.out.println(new Solution1().verifyPostorder(nums));
    }
}