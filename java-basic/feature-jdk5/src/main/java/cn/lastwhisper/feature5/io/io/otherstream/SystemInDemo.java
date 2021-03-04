package cn.lastwhisper.feature5.io.io.otherstream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * System.in 标准输入流。是从键盘获取数据的
 *
 * 键盘录入数据：
 * 		A:main方法的args接收参数。
 * 			java HelloWorld hello world java
 * 		B:Scanner(JDK5以后的)
 * 			Scanner sc = new Scanner(System.in);
 * 			String s = sc.nextLine();
 * 			int x = sc.nextInt()
 * 		C:通过字符缓冲流包装标准输入流实现
 * 			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 * @author lastwhisper
 * @date 2020/6/14
 */
public class SystemInDemo {

    /**
     * 如何从控制台键盘中获取数据？
     * System.in是字节输入流，需要使用字符输入流包装
     * 再使用 BufferedReader 读取一行数据
     */
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("请输入一个字符串：");
        String line = br.readLine();
        System.out.println("你输入的字符串是：" + line);

        System.out.println("请输入一个整数：");
        // int i = Integer.parseInt(br.readLine());
        line = br.readLine();
        int i = Integer.parseInt(line);
        System.out.println("你输入的整数是：" + i);
    }
    
}
