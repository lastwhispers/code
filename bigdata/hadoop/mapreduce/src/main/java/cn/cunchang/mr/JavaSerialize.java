package cn.cunchang.mr;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Java中的序列化
 * Created by xuwei
 */
public class JavaSerialize {
    public static void main(String[] args) throws Exception{
        //创建Student对象，并设置id和name属性
        StudentJava studentJava = new StudentJava();
        studentJava.setId(1L);
        studentJava.setName("Hadoop");

        //将Student对象的当前状态写入本地文件中
        FileOutputStream fos = new FileOutputStream("D:\\student_java.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(studentJava);
        oos.close();
        fos.close();
    }
}

class StudentJava implements Serializable{
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}