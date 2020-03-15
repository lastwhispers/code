import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    //public static void main(String[] args){
    //    Scanner in = new Scanner(System.in);
    //    // 一个while就是一个测试用例
    //    while(in.hasNext()){
    //        int m = in.nextInt(); // 该测试用例后续接收的参数个数
    //        int n = in.nextInt(); // 该测试用例后续接收的参数个数
    //        long[] array = new long[m];
    //        for(int i=0; i<m; i++){
    //            array[i] = in.nextLong();// 取下一个元素转换成long类型
    //        }
    //        System.out.println(array.length+"\t"+n);
    //    }
    //}

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
	    /* nextLine()是扫描器执行当前行，并返回跳过的输入信息，特别需要注意！！！

            如果没有该行，则执行第一个in.nextLine()命令时的返回值是int n = in.nextInt()的值*/
            sc.nextLine();
            HashSet<String> set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                String line =sc.nextLine();
                String[] arr = line.split(" ");
                set.addAll(Arrays.asList(arr));
            }
            System.out.println("sum:" + set.size());
        }
        sc.close();
    }

}