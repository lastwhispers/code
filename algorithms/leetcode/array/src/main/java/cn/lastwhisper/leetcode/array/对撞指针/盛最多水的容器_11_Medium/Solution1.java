package cn.lastwhisper.leetcode.array.对撞指针.盛最多水的容器_11_Medium;

/**
 * @author lastwhisper
 */
public class Solution1 {
    public int maxArea(int[] height) {
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                maxArea = Math.max(maxArea, Math.min(height[i], height[j]) * (j - i));
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] arr = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(new Solution1().maxArea(arr));
    }
}
