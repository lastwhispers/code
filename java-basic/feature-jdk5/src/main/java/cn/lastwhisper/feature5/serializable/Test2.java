package cn.lastwhisper.feature5.serializable;

import java.io.*;

/**
 * 不使用Transient关键字，使用父子类序列化特性使得字段不被序列化
 * @author lastwhisper
 */
public class Test2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 序列化存储对象
        B b1 = new B("admin", "132465");
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("D:\\result.obj"));
        out.writeObject(b1);
        out.close();
        // 反序列化
        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(
                "D:\\result.obj"));
        B b2 = (B) oin.readObject();
        oin.close();
        System.out.println(b2.username + " " + b2.pwd);
    }
}

class A {
    protected String pwd;

    public A() {
    }
}

class B extends A implements Serializable {
    protected String username;

    public B(String username, String pwd) {
        this.username = username;
        super.pwd = pwd;
    }
}