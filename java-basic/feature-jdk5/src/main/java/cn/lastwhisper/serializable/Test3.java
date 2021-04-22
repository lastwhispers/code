package cn.lastwhisper.serializable;


import java.io.*;

/**
 * @author lastwhisper
 */
public class Test3 implements Serializable{
    private static final long serialVersionUID = 1L;

    private String password = "pass";

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private void writeObject(ObjectOutputStream out) {
        try {
            ObjectOutputStream.PutField putFields = out.putFields();
            System.out.println("原密码:" + password);
            password = "encryption";//模拟加密
            putFields.put("password", password);
            System.out.println("加密后的密码" + password);
            out.writeFields();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readObject(ObjectInputStream in) {
        try {
            ObjectInputStream.GetField readFields = in.readFields();
            Object object = readFields.get("password", "");
            System.out.println("要解密的字符串:" + object.toString());
            password = "pass";//模拟解密,需要获得本地的密钥
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("D:\\result.obj"));
            out.writeObject(new Test3());
            out.close();

            ObjectInputStream oin = new ObjectInputStream(new FileInputStream(
                    "D:\\result.obj"));
            Test3 t = (Test3) oin.readObject();
            System.out.println("解密后的字符串:" + t.getPassword());
            oin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
