package cn.lastwhisper.jdk5.feature.serializable;

import java.io.*;
/**
 * 静态变量序列化
 */
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;
    public static int staticVar = 5;

    public static void main(String[] args) {
        try {
            //初始时staticVar为5
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("D:\\result.obj"));
            out.writeObject(new Test());
            out.close();
            //序列化后修改为10
            Test.staticVar = 10;
            ObjectInputStream oin = new ObjectInputStream(new FileInputStream(
                    "D:\\result.obj"));
            Test t = (Test) oin.readObject();
            oin.close();
            //再读取，通过t.staticVar打印新的值
            // 打印10说明，序列化并没有保存static字段，如果保存了staticVar应该为5
            System.out.println(t.staticVar);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}