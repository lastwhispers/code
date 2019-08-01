package cn.lastwhisper.recursion;

/**
 * 递归解决八皇后问题
 * @author lastwhisper
 */
public class Queue8 {

    static int count = 0;
    static int judgeCount = 0;
    // 定义一个max表示有多少个皇后
    private int max = 8;
    // 定义一个数组保存皇后解法 arr[8] = { 0, 4, 7, 5, 2, 6, 1, 3}
    // arr的意义：（index：下标、val：数组取某下标时数组对应的值）
    //  （1）arr的index+1表示第几个皇后（一行一个皇后）。
    //  （2）arr的index对应数组的val表示第index+1个皇后放在第val+1列。
    //  示例：arr的index=1时表示第2个皇后，arr[index]=val=4表示第2个皇后放在第5列
    //  一句话：row=下标+1；column=arr[下标]+1
    int[] arr = new int[max];

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        // 测试judge方法是否正确
        //queue8.arr[0] = 0; //第一皇后在 0,0
        //queue8.arr[1] = 1; //第二个皇后在 1,1
        //System.out.println(queue8.judge(1));

        // 测试8皇后是否正确 ；
        queue8.check(0);
        System.out.printf("一共有%d解法\n", count);
        System.out.printf("一共判断冲突的次数%d次", judgeCount); // 1.5w
    }

    // 放置第index个皇后
    public void check(int index) {
        // index从0开始
        if (index == max) {
            print();
            return;
        }
        // 每次一check的递归都会重新循环
        for (int i = 0; i < max; i++) {
            // 将第index个皇后，放置第i+1列。
            arr[index] = i;
            // 判断当前放置的位置与之前的皇后是否冲突
            if (judge(index)) {
                this.check(index + 1);
            }
        }
    }

    // 放置第index(n=index+1)个皇后之后，检测是否和前面已经摆放的皇后冲突
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            // 同一列：arr[i] == arr[n]；column=arr[下标]+1
            // 同一斜线：Math.abs(n - i) == Math.abs(arr[n] - arr[i])，判断第n个皇后是否和第i个皇后在同一斜线
            //   详解：i=0 arr[i]=0，第1个皇后放在第1列；n=1 arr[n]=1，第2个皇后放在第2列
            //      Math.abs(1 - 0)==Math.abs(arr[1] - arr[0]))
            //      下标之差等于对应数组数值之差说明在同一对角线上（下标对应row、数组数值对应column）
            // 同一行：由arr数组的意义来看不可能存在同一行
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }

    //写一个方法，可以将皇后摆放的位置输出
    private void print() {
        count++;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
