package cn.lastwhisper.offer.面试题14_I_剪绳子_中等;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/jian-sheng-zi-lcof/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：贪心算法
     *  n>=5时 2(n-2)>n、3(n-3)>n ===》 3(n-3)>2(n-2)
     * -------------------------------------------------------------------
     * 时间复杂度：O(1)
     * 空间复杂度：O(1)
     */
    public int cuttingRope(int n) {
        if (n <= 3) return n - 1;// 2 3
        int time0f3 = n / 3, b = n % 3;
        if (b == 0) return (int) Math.pow(3, time0f3);//6 9
        if (b == 1) return (int) Math.pow(3, time0f3 - 1) * 4;// 4 7 10 13
        return (int) Math.pow(3, time0f3) * 2;// 5 8 11
    }
    // logn
    //public int cuttingRope(int n) {
    //    // 2=>1*1=1
    //    // 3=>1*2=2
    //    if (n < 4) {
    //        return n-1;
    //    }
    //
    //    int sum = 1;
    //    while (n > 4) {
    //        sum *= 3;
    //        n -= 3;
    //    }
    //    return sum * n;
    //}

    public static void main(String[] args) {
        System.err.println(new Solution2().cuttingRope(10));
    }
}