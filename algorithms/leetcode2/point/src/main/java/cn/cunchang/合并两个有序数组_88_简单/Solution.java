package cn.cunchang.合并两个有序数组_88_简单;

class Solution {
    /**
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * m为num1指针，n为num2指针，p=m+n+1为复制位置指针
     * num1、num2从后往前，最大复制到num1尾部
     * num1复制完了，把num2剩余复制到num1。num2复制完了，就结束了。
     *
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 记录复制的位置
        int p = m + n - 1;
        m--;
        n--;
        while (m >= 0 && n >= 0) {
            if (nums2[n] > nums1[m]) {
                nums1[p] = nums2[n];
                n--;
            } else {
                nums1[p] = nums1[m];
                m--;
            }
            p--;
        }
        // 复制num2
        if (n >= 0) {
            for (int i = n; i >= 0; i--) {
                nums1[p] = nums2[i];
                p--;
            }
        }
    }

}