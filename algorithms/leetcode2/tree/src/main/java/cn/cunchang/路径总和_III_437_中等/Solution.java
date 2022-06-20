package cn.cunchang.路径总和_III_437_中等;

import cn.cunchang.tree.TreeNode;
import cn.cunchang.tree.TreeUtil;

class Solution {

    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int res = findPathSum(root, sum);
        res += pathSum(root.left, sum);
        res += pathSum(root.right, sum);
        return res;
    }

    // 从node找到路径和等于sum的数量
    public int findPathSum(TreeNode node, int sum) {
        if (node == null) {
            return 0;
        }
        int res = 0;
        sum -= node.val;
        if (sum == 0) {
            res++;
        }
        res += findPathSum(node.left, sum);
        res += findPathSum(node.right, sum);

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().pathSum(
                TreeUtil.createTree(10, 5, -3, 3, 2, null, 11, 3, -2, null, 1), 8));
    }
}