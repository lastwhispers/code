package cn.cunchang.mr;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 生成测试数据
 * 1：
 * Created by xuwei
 */
public class GenerateDat {
    public static void main(String[] args) throws Exception{
        generate_140M();
        generate_141M();
    }

    /**
     * 生成141M文件
     * @throws IOException
     */
    private static void generate_141M() throws IOException {
        String fileName = "D:\\s_name_141.dat";
        System.out.println("start: 开始生成141M文件->"+fileName);
        BufferedWriter bfw = new BufferedWriter(new FileWriter(fileName));
        int num = 0;
        while(num<8221592){
            bfw.write("zhangsan beijing");
            bfw.newLine();
            num ++;
            if(num%10000==0){
                bfw.flush();
            }
        }
        System.out.println("end: 141M文件已生成");
    }

    /**
     * 生成140M文件
     * @throws IOException
     */
    private static void generate_140M() throws IOException {
        String fileName = "D:\\s_name_140.dat";
        System.out.println("start: 开始生成140M文件->"+fileName);
        BufferedWriter bfw = new BufferedWriter(new FileWriter(fileName));
        int num = 0;
        while(num<8201592){
            bfw.write("zhangsan beijing");
            bfw.newLine();
            num ++;
            if(num%10000==0){
                bfw.flush();
            }
        }
        System.out.println("end: 140M文件已生成");
    }
}
