package cn.lastwhisper.io.io.otherstream;

import java.io.*;
import java.util.Enumeration;
import java.util.Vector;

/**
 *
 * @author lastwhisper
 * @date 2020/6/14
 */
public class SequenceInputStreamDemo {

    public static void main(String[] args) throws IOException {
        // 需求：把下面的三个文件的内容复制到Copy.java中
        // ByteArrayStreamDemo.java,CopyFileDemo.java,DataStreamDemo.java

        // SequenceInputStream(Enumeration e)
        // 通过简单的回顾我们知道了Enumeration是Vector中的一个方法的返回值类型。
        // Enumeration<E> elements()
        Vector<InputStream> v = new Vector<InputStream>();
        InputStream s1 = new FileInputStream("a.txt");
        InputStream s2 = new FileInputStream("b.txt");
        InputStream s3 = new FileInputStream("c.txt");
        v.add(s1);
        v.add(s2);
        v.add(s3);
        Enumeration<InputStream> en = v.elements();
        SequenceInputStream sis = new SequenceInputStream(en);
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream("d.txt"));

        // 如何写读写呢，其实很简单，你就按照以前怎么读写，现在还是怎么读写
        byte[] bytes = new byte[1024];
        int len;
        while ((len = sis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }

        bos.close();
        sis.close();
    }

}
