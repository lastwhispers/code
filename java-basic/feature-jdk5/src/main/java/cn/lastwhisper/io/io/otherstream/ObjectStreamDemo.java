package cn.lastwhisper.io.io.otherstream;

import org.junit.Test;

import java.io.*;

/**
 * 
 * @author lastwhisper
 * @date 2020/6/14
 */
public class ObjectStreamDemo {

    /*
     * 序列化流：把对象按照流一样的方式存入文本文件或者在网络中传输。对象 -- 流数据(ObjectOutputStream)
     * 反序列化流:把文本文件中的流对象数据或者网络中的流对象数据还原成对象。流数据 -- 对象(ObjectInputStream)
     */
    @Test
    public void testObjectWrite() throws IOException {
        // 创建序列化流对象
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
                "oos.txt"));

        // 创建对象
        Person p = new Person("林青霞", 27);

        oos.writeObject(p);

        // 释放资源
        oos.close();
    }

    @Test
    public void testObjectRead() throws IOException, ClassNotFoundException {
        // 创建反序列化对象
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                "oos.txt"));

        // 还原对象
        Object obj = ois.readObject();

        // 释放资源
        ois.close();

        // 输出对象
        System.out.println(obj);
    }

}
