package cn.cunchang.分发饼干_455_简单;

import org.junit.Assert;

import java.util.Arrays;

public class Solution1 {
    /**
     * 思考：因为饥饿度最小的孩子最容易吃饱，所以我们先考虑这个孩子。为了尽量使得剩下的饼干可
     * 以满足饥饿度更大的孩子，所以我们应该把大于等于这个孩子饥饿度的、且大小最小的饼干给这
     * 个孩子。满足了这个孩子之后，我们采取同样的策略，考虑剩下孩子里饥饿度最小的孩子，直到
     * 没有满足条件的饼干存在。
     * -------------------------------------------------------------------
     * 思路：
     * 1、排序，对孩子的胃口和饼干大小降序排序
     * 2、将最小的饼干s[j]给胃口最小的孩子g[i]，s[j] >= g[i]，说明满足，满足孩子数+1、继续分发下一块饼干；
     * 不满足的话，孩子数不加，继续分发下一块饼干；
     *
     * <p>
     * -------------------------------------------------------------------
     * 时间复杂度：n
     * 空间复杂度：
     */
    public int findContentChildren(int[] childs, int[] cookies) {
        Arrays.sort(childs);
        Arrays.sort(cookies);
        int child = 0, cookie = 0;
        while (child < childs.length && cookie < cookies.length) {
            if (cookies[cookie] >= childs[child]) {
                child++;
            }
            cookie++;
        }
        return child;
    }

    public static void main(String[] args) {
//        int[] g = new int[]{1, 2};
//        int[] s = new int[]{1, 2, 3};
//        Assert.assertEquals(2, new Solution1().findContentChildren(g, s));

        int[] g = new int[]{1, 2, 3};
        int[] s = new int[]{1, 1};
        Assert.assertEquals(1, new Solution1().findContentChildren(g, s));

    }

}